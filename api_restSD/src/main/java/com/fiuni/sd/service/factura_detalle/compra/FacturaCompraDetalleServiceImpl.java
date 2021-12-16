package com.fiuni.sd.service.factura_detalle.compra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IFacturaCompraDao;
import com.fiuni.sd.dao.IFacturaCompraDetalleDao;
import com.fiuni.sd.dao.IProductoDao;
import com.fiuni.sd.domain.factura_detalle.compra.FacturaCompraDetalleDomain;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class FacturaCompraDetalleServiceImpl
		extends BaseServiceImpl<FacturaCompraDetalleDTO, FacturaCompraDetalleDomain, FacturaCompraDetalleResult>
		implements IFacturaCompraDetalleService {

	@Autowired
	private IFacturaCompraDetalleDao facturaCompraDetalleRepository; // repository

	@Autowired
	private IFacturaCompraDao facturaCompraRepository; // repository

	@Autowired
	private IProductoDao productoRepository; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaCompraDetalle_' + #result.getId()")
	public FacturaCompraDetalleDTO save(final FacturaCompraDetalleDTO dto) {
		dto.setSubtotal(productoRepository.getById(dto.getProducto_id()).getCosto() * dto.getCantidad());
		return convertDomainToDto(facturaCompraDetalleRepository.save(convertDtoToDomain(dto)));
	}
	
	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaCompraDetalle_' + count")
	public Integer count() {
		Integer countCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaCompraDetalle_count", Integer.class);
		if (countCacheado != null) {
			return countCacheado;
		}
		return (int) facturaCompraDetalleRepository.count();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaCompraDetalle_' + #id")
	public FacturaCompraDetalleDTO getById(final Integer id) {
		FacturaCompraDetalleDTO detalleCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaCompraDetalle_" + id, FacturaCompraDetalleDTO.class);
		if (detalleCacheado != null) {
			return detalleCacheado;
		}
		return facturaCompraDetalleRepository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaCompraDetalle", "id", id));
	}

	@Override
	public FacturaCompraDetalleResult getAllByFactura(final Integer idFactura, final Pageable pageable) {
		final List<FacturaCompraDetalleDTO> list = new ArrayList<>();
		final FacturaCompraDetalleResult result = new FacturaCompraDetalleResult();
		Page<FacturaCompraDetalleDomain> pages = facturaCompraDetalleRepository//
				.findAllByFactura(facturaCompraRepository.getById(idFactura), pageable);
		pages.forEach(detalle -> {
			FacturaCompraDetalleDTO dto = convertDomainToDto(detalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaCompraDetalle_" + dto.getId(), dto);
		});
		result.setFacturasCompraDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		result.setTotal((int) facturaCompraDetalleRepository.count());
		result.set_hasPrev(pages.hasPrevious());
		result.set_hasNext(pages.hasNext());
		return result;
	}

	@Override
	public FacturaCompraDetalleResult getAllByProducto(final Integer idProducto, final Pageable pageable) {
		final List<FacturaCompraDetalleDTO> list = new ArrayList<>();
		final FacturaCompraDetalleResult result = new FacturaCompraDetalleResult();
		Page<FacturaCompraDetalleDomain> pages = facturaCompraDetalleRepository//
				.findAllByProducto(productoRepository.getById(idProducto), pageable);
		pages.forEach(detalle -> {
			FacturaCompraDetalleDTO dto = convertDomainToDto(detalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaCompraDetalle_" + dto.getId(), dto);
		});
		result.setFacturasCompraDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaCompraDetalleResult getAll(final Pageable pageable) {
		final List<FacturaCompraDetalleDTO> list = new ArrayList<>();
		final FacturaCompraDetalleResult result = new FacturaCompraDetalleResult();
		Page<FacturaCompraDetalleDomain> pages = facturaCompraDetalleRepository.findAll(pageable);
		pages.forEach(detalle -> {
			FacturaCompraDetalleDTO dto = convertDomainToDto(detalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaCompraDetalle_" + dto.getId(), dto);
		});
		result.setFacturasCompraDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		result.setTotal((int) facturaCompraDetalleRepository.count());
		result.set_hasPrev(pages.hasPrevious());
		result.set_hasNext(pages.hasNext());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_facturaCompraDetalle_' + #id")
	public FacturaCompraDetalleDTO deleteById(final Integer id) {
		if (!facturaCompraDetalleRepository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaCompraDetalle", "id", id);
		}
		FacturaCompraDetalleDTO detalle = convertDomainToDto(facturaCompraDetalleRepository.getById(id));
		facturaCompraDetalleRepository.deleteById(id);
		return detalle;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaCompraDetalle_' + #id")
	public FacturaCompraDetalleDTO update(final Integer id, final FacturaCompraDetalleDTO dto) {
		if (!facturaCompraDetalleRepository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaCompraDetalle", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaCompraDetalle", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_facturaCompraDetalle_" + id);
		return convertDomainToDto(facturaCompraDetalleRepository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaCompraDetalleDTO convertDomainToDto(final FacturaCompraDetalleDomain domain) {
		final FacturaCompraDetalleDTO dto = new FacturaCompraDetalleDTO();
		dto.setId(domain.getId());
		dto.setCantidad(domain.getCantidad());
		dto.setSubtotal(domain.getSubtotal());
		dto.setFactura_id(domain.getFactura().getId());
		dto.setProducto_id(domain.getProducto().getId());
		return dto;
	}

	@Override
	protected FacturaCompraDetalleDomain convertDtoToDomain(final FacturaCompraDetalleDTO dto) {
		final FacturaCompraDetalleDomain domain = new FacturaCompraDetalleDomain();
		domain.setId(dto.getId());
		domain.setCantidad(dto.getCantidad());
		domain.setSubtotal(dto.getSubtotal());
		domain.setFactura(facturaCompraRepository.getById(dto.getFactura_id()));
		domain.setProducto(productoRepository.getById(dto.getProducto_id()));
		return domain;
	}

}

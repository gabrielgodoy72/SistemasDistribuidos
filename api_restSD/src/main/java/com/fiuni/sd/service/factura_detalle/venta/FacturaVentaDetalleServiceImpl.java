package com.fiuni.sd.service.factura_detalle.venta;

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

import com.fiuni.sd.dao.IFacturaVentaDao;
import com.fiuni.sd.dao.IFacturaVentaDetalleDao;
import com.fiuni.sd.dao.IServicioDao;
import com.fiuni.sd.domain.factura_detalle.venta.FacturaVentaDetalleDomain;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class FacturaVentaDetalleServiceImpl
		extends BaseServiceImpl<FacturaVentaDetalleDTO, FacturaVentaDetalleDomain, FacturaVentaDetalleResult>
		implements IFacturaVentaDetalleService {

	@Autowired
	private IFacturaVentaDetalleDao repository; // repository

	@Autowired
	private IFacturaVentaDao facturaRepository; // repository

	@Autowired
	private IServicioDao servicioRepository; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaVentaDetalle_' + #result.getId()")
	public FacturaVentaDetalleDTO save(final FacturaVentaDetalleDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}
	
	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaVentaDetalle_' + count")
	public Integer count() {
		Integer countCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaVentaDetalle_count", Integer.class);
		if (countCacheado != null) {
			return countCacheado;
		}
		return (int) repository.count();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaVentaDetalle_' + #id")
	public FacturaVentaDetalleDTO getById(final Integer id) {
		FacturaVentaDetalleDTO facturaVentaDetalleCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaVentaDetalle_" + id, FacturaVentaDetalleDTO.class);
		if (facturaVentaDetalleCacheado != null) {
			return facturaVentaDetalleCacheado;
		}
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	public FacturaVentaDetalleResult getAllByFactura(final Integer idFactura, final Pageable pageable) {
		final List<FacturaVentaDetalleDTO> list = new ArrayList<>();
		final FacturaVentaDetalleResult result = new FacturaVentaDetalleResult();
		Page<FacturaVentaDetalleDomain> pages = repository.findAllByFactura(facturaRepository.getById(idFactura),
				pageable);
		pages.forEach(facturaVentaDetalle -> {
			FacturaVentaDetalleDTO dto = convertDomainToDto(facturaVentaDetalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaVentaDetalle_" + dto.getId(), dto);
		});
		result.setFacturasVentaDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaVentaDetalleResult getAllByServicio(final Integer idServicio, final Pageable pageable) {
		final List<FacturaVentaDetalleDTO> list = new ArrayList<>();
		final FacturaVentaDetalleResult result = new FacturaVentaDetalleResult();
		Page<FacturaVentaDetalleDomain> pages = repository.findAllByServicio(servicioRepository.getById(idServicio),
				pageable);
		pages.forEach(facturaVentaDetalle -> {
			FacturaVentaDetalleDTO dto = convertDomainToDto(facturaVentaDetalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaVentaDetalle_" + dto.getId(), dto);
		});
		result.setFacturasVentaDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;

	}

	@Override
	public FacturaVentaDetalleResult getAll(final Pageable pageable) {
		final List<FacturaVentaDetalleDTO> list = new ArrayList<>();
		final FacturaVentaDetalleResult result = new FacturaVentaDetalleResult();
		Page<FacturaVentaDetalleDomain> pages = repository.findAll(pageable);
		pages.forEach(facturaVentaDetalle -> {
			FacturaVentaDetalleDTO dto = convertDomainToDto(facturaVentaDetalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaVentaDetalle_" + dto.getId(), dto);
		});
		result.setFacturasVentaDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		result.setTotal((int) repository.count());
		result.set_hasPrev(pages.hasPrevious());
		result.set_hasNext(pages.hasNext());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_facturaVentaDetalle_' + #id")
	public FacturaVentaDetalleDTO deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVentaDetalle", "id", id);
		}
		FacturaVentaDetalleDTO detalle = convertDomainToDto(repository.getById(id));
		repository.deleteById(id);
		return detalle;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaVentaDetalle_' + #id")
	public FacturaVentaDetalleDTO update(final Integer id, final FacturaVentaDetalleDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVentaDetalle", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaVentaDetalle", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_facturaVentaDetalle_" + id);
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaVentaDetalleDTO convertDomainToDto(final FacturaVentaDetalleDomain domain) {
		final FacturaVentaDetalleDTO dto = new FacturaVentaDetalleDTO();
		dto.setId(domain.getId());
		dto.setCantidad(domain.getCantidad());
		dto.setSubtotal(domain.getSubtotal());
		dto.setFactura_id(domain.getFactura().getId());
		dto.setServicio_id(domain.getServicio().getId());
		return dto;
	}

	@Override
	protected FacturaVentaDetalleDomain convertDtoToDomain(final FacturaVentaDetalleDTO dto) {
		final FacturaVentaDetalleDomain domain = new FacturaVentaDetalleDomain();
		domain.setId(dto.getId());
		domain.setCantidad(dto.getCantidad());
		domain.setSubtotal(dto.getSubtotal());
		domain.setFactura(facturaRepository.getById(dto.getFactura_id()));
		domain.setServicio(servicioRepository.getById(dto.getServicio_id()));
		return domain;
	}

}

package com.fiuni.sd.service.factura.compra;

import java.util.ArrayList;
import java.util.Date;
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
import com.fiuni.sd.dao.IProveedorDao;
import com.fiuni.sd.domain.factura.compra.FacturaCompraDomain;
import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class FacturaCompraServiceImpl extends
		BaseServiceImpl<FacturaCompraDTO, FacturaCompraDomain, FacturaCompraResult> implements IFacturaCompraService {

	@Autowired
	private IFacturaCompraDao repository; // repository

	@Autowired
	private IProveedorDao proveedorRepository; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaCompra_' + #result.getId()")
	public FacturaCompraDTO save(FacturaCompraDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaCompra_' + #id")
	public FacturaCompraDTO getById(Integer id) {
		FacturaCompraDTO facturaCacheada = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaCompra_" + id, FacturaCompraDTO.class);
		if (facturaCacheada != null) {
			return facturaCacheada;
		}
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaCompra", "id", id));
	}

	@Override
	public FacturaCompraResult getAllByFecha(Date fecha, Pageable pageable) {
		final List<FacturaCompraDTO> list = new ArrayList<>();
		final FacturaCompraResult result = new FacturaCompraResult();
		Page<FacturaCompraDomain> pages = repository.findAllByFecha(fecha, pageable);
		pages.forEach(factura -> {
			FacturaCompraDTO dto = convertDomainToDto(factura);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaCompra_" + dto.getId(), dto);
		});
		result.setFacturasCompra(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaCompra_' + #id")
	public FacturaCompraDTO getByNumero(String numero) {
		FacturaCompraDTO facturaCacheada = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaCompra_" + numero, FacturaCompraDTO.class);
		if (facturaCacheada != null) {
			return facturaCacheada;
		}
		return repository.findByNumero(numero)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaCompra", "numero", numero));
	}

	@Override
	public FacturaCompraResult getAllByProveedor(Integer idProveedor, Pageable pageable) {
		final List<FacturaCompraDTO> list = new ArrayList<>();
		final FacturaCompraResult result = new FacturaCompraResult();
		Page<FacturaCompraDomain> pages = repository.findAllByProveedor(proveedorRepository.getById(idProveedor),
				pageable);
		pages.forEach(factura -> {
			FacturaCompraDTO dto = convertDomainToDto(factura);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaCompra_" + dto.getId(), dto);
		});
		result.setFacturasCompra(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaCompraResult getAll(Pageable pageable) {
		final List<FacturaCompraDTO> list = new ArrayList<>();
		final FacturaCompraResult result = new FacturaCompraResult();
		Page<FacturaCompraDomain> pages = repository.findAll(pageable);
		pages.forEach(factura -> {
			FacturaCompraDTO dto = convertDomainToDto(factura);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaCompra_" + dto.getId(), dto);
		});
		result.setFacturasCompra(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_facturaCompra_' + #id")
	public FacturaCompraDTO deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaCompra", "id", id);
		}
		FacturaCompraDTO factura = convertDomainToDto(repository.getById(id));
		repository.deleteById(id);
		return factura;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaCompra_' + #id")
	public FacturaCompraDTO update(final Integer id, final FacturaCompraDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaCompra", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaCompra", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_facturaCompra_" + id);
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaCompraDTO convertDomainToDto(final FacturaCompraDomain domain) {
		final FacturaCompraDTO dto = new FacturaCompraDTO();
		dto.setId(domain.getId());
		dto.setNumero(domain.getNumero());
		dto.setFecha(domain.getFecha());
		dto.setTotal(domain.getTotal());
		dto.setProveedor_id(domain.getProveedor().getId());
		return dto;
	}

	@Override
	protected FacturaCompraDomain convertDtoToDomain(final FacturaCompraDTO dto) {
		final FacturaCompraDomain domain = new FacturaCompraDomain();
		domain.setId(dto.getId());
		domain.setNumero(dto.getNumero());
		domain.setFecha(dto.getFecha());
		domain.setTotal(dto.getTotal());
		domain.setProveedor(proveedorRepository.getById(dto.getProveedor_id()));
		return domain;
	}

}

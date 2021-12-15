package com.fiuni.sd.service.factura.venta;

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

import com.fiuni.sd.dao.IClienteDao;
import com.fiuni.sd.dao.IFacturaVentaDao;
import com.fiuni.sd.domain.factura.venta.FacturaVentaDomain;
import com.fiuni.sd.dto.factura.venta.FacturaVentaDTO;
import com.fiuni.sd.dto.factura.venta.FacturaVentaResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class FacturaVentaServiceImpl extends BaseServiceImpl<FacturaVentaDTO, FacturaVentaDomain, FacturaVentaResult>
		implements IFacturaVentaService {

	@Autowired
	private IFacturaVentaDao repository; // repository

	@Autowired
	private IClienteDao clienteRepository; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaVenta_' + #result.getId()")
	public FacturaVentaDTO save(FacturaVentaDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaVenta_' + #id")
	public FacturaVentaDTO getById(Integer id) {
		FacturaVentaDTO facturaVentaCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaVenta_" + id, FacturaVentaDTO.class);
		if (facturaVentaCacheado != null) {
			return facturaVentaCacheado;
		}
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	public FacturaVentaResult getAllByFecha(Date fecha, Pageable pageable) {
		final List<FacturaVentaDTO> list = new ArrayList<>();
		final FacturaVentaResult result = new FacturaVentaResult();
		Page<FacturaVentaDomain> pages = repository.findAllByFecha(fecha, pageable);
		pages.forEach(facturaVenta -> {
			FacturaVentaDTO dto = convertDomainToDto(facturaVenta);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaVenta_" + dto.getId(), dto);
		});
		result.setFacturasVenta(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_facturaVenta_' + #numero")
	public FacturaVentaDTO getByNumero(String numero) {
		FacturaVentaDTO facturaVentaCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_facturaVenta_" + numero, FacturaVentaDTO.class);
		if (facturaVentaCacheado != null) {
			return facturaVentaCacheado;
		}
		return repository.findByNumero(numero)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	public FacturaVentaResult getAllByCliente(Integer idCliente, Pageable pageable) {
		final List<FacturaVentaDTO> list = new ArrayList<>();
		final FacturaVentaResult result = new FacturaVentaResult();
		Page<FacturaVentaDomain> pages = repository.findAllByCliente(clienteRepository.getById(idCliente), pageable);
		pages.forEach(facturaVenta -> {
			FacturaVentaDTO dto = convertDomainToDto(facturaVenta);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaVenta_" + dto.getId(), dto);
		});
		result.setFacturasVenta(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaVentaResult getAll(Pageable pageable) {
		final List<FacturaVentaDTO> list = new ArrayList<>();
		final FacturaVentaResult result = new FacturaVentaResult();
		Page<FacturaVentaDomain> pages = repository.findAll(pageable);
		pages.forEach(facturaVenta -> {
			FacturaVentaDTO dto = convertDomainToDto(facturaVenta);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_facturaVenta_" + dto.getId(), dto);
		});
		result.setFacturasVenta(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_facturaVenta_' + #id")
	public FacturaVentaDTO deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVenta", "id", id);
		}
		FacturaVentaDTO factura = convertDomainToDto(repository.getById(id));
		repository.deleteById(id);
		return factura;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_facturaVenta_' + #id")
	public FacturaVentaDTO update(final Integer id, final FacturaVentaDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVenta", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaVenta", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_facturaVenta_" + id);
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaVentaDTO convertDomainToDto(final FacturaVentaDomain domain) {
		final FacturaVentaDTO dto = new FacturaVentaDTO();
		dto.setId(domain.getId());
		dto.setNumero(domain.getNumero());
		dto.setFecha(domain.getFecha());
		dto.setTotal(domain.getTotal());
		dto.setCliente_id(domain.getCliente().getId());
		return dto;
	}

	@Override
	protected FacturaVentaDomain convertDtoToDomain(final FacturaVentaDTO dto) {
		final FacturaVentaDomain domain = new FacturaVentaDomain();
		domain.setId(dto.getId());
		domain.setNumero(dto.getNumero());
		domain.setFecha(dto.getFecha());
		domain.setTotal(dto.getTotal());
		domain.setCliente(clienteRepository.getById(dto.getCliente_id()));
		return domain;
	}

}

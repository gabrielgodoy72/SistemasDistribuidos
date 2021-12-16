package com.fiuni.sd.service.pedido_detalle;

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

import com.fiuni.sd.dao.IPedidoDao;
import com.fiuni.sd.dao.IPedidoDetalleDao;
import com.fiuni.sd.dao.IServicioDao;
import com.fiuni.sd.domain.pedido_detalle.PedidoDetalleDomain;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleDTO;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class PedidoDetalleServiceImpl extends
		BaseServiceImpl<PedidoDetalleDTO, PedidoDetalleDomain, PedidoDetalleResult> implements IPedidoDetalleService {

	@Autowired
	private IPedidoDetalleDao repository; // repository

	@Autowired
	private IPedidoDao pedidoRepository; // repository

	@Autowired
	private IServicioDao servicioRepository; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_pedidoDetalle_' + #result.getId()")
	public PedidoDetalleDTO save(final PedidoDetalleDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_pedidoDetalle_' + count")
	public Integer count() {
		Integer countCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_pedidoDetalle_count", Integer.class);
		if (countCacheado != null) {
			return countCacheado;
		}
		return (int) repository.count();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_pedidoDetalle_' + #id")
	public PedidoDetalleDTO getById(final Integer id) {
		PedidoDetalleDTO pedidoDetalleCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_pedidoDetalle_" + id, PedidoDetalleDTO.class);
		if (pedidoDetalleCacheado != null) {
			return pedidoDetalleCacheado;
		}
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	public PedidoDetalleResult getAllByPedido(final Integer idPedido, final Pageable pageable) {
		final List<PedidoDetalleDTO> list = new ArrayList<>();
		final PedidoDetalleResult result = new PedidoDetalleResult();
		Page<PedidoDetalleDomain> pages = repository.findAllByPedido(pedidoRepository.getById(idPedido), pageable);
		pages.forEach(pedidoDetalle -> {
			PedidoDetalleDTO dto = convertDomainToDto(pedidoDetalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_pedidoDetalle_" + dto.getId(), dto);
		});
		result.setPedidosDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public PedidoDetalleResult getAllByServicio(final Integer idServicio, final Pageable pageable) {
		final List<PedidoDetalleDTO> list = new ArrayList<>();
		final PedidoDetalleResult result = new PedidoDetalleResult();
		Page<PedidoDetalleDomain> pages = repository.findAllByServicio(servicioRepository.getById(idServicio),
				pageable);
		pages.forEach(pedidoDetalle -> {
			PedidoDetalleDTO dto = convertDomainToDto(pedidoDetalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_pedidoDetalle_" + dto.getId(), dto);
		});
		result.setPedidosDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public PedidoDetalleResult getAllByFecha(final Date fecha, final Pageable pageable) {
		final List<PedidoDetalleDTO> list = new ArrayList<>();
		final PedidoDetalleResult result = new PedidoDetalleResult();
		Page<PedidoDetalleDomain> pages = repository.findAllByFecha(fecha, pageable);
		pages.forEach(pedidoDetalle -> {
			PedidoDetalleDTO dto = convertDomainToDto(pedidoDetalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_pedidoDetalle_" + dto.getId(), dto);
		});
		result.setPedidosDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public PedidoDetalleResult getAll(final Pageable pageable) {
		final List<PedidoDetalleDTO> list = new ArrayList<>();
		final PedidoDetalleResult result = new PedidoDetalleResult();
		Page<PedidoDetalleDomain> pages = repository.findAll(pageable);
		pages.forEach(pedidoDetalle -> {
			PedidoDetalleDTO dto = convertDomainToDto(pedidoDetalle);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_pedidoDetalle_" + dto.getId(), dto);
		});
		result.setPedidosDetalle(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		result.setTotal((int) repository.count());
		result.set_hasPrev(pages.hasPrevious());
		result.set_hasNext(pages.hasNext());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_pedidoDetalle_' + #id")
	public PedidoDetalleDTO deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("PedidoDetalle", "id", id);
		}
		PedidoDetalleDTO detalle = convertDomainToDto(repository.getById(id));
		repository.deleteById(id);
		return detalle;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_pedidoDetalle_' + #id")
	public PedidoDetalleDTO update(final Integer id, final PedidoDetalleDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("PedidoDetalle", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("PedidoDetalle", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_pedidoDetalle_" + id);
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected PedidoDetalleDTO convertDomainToDto(final PedidoDetalleDomain domain) {
		final PedidoDetalleDTO dto = new PedidoDetalleDTO();
		dto.setId(domain.getId());
		dto.setFecha(domain.getFecha());
		dto.setPedido_id(domain.getPedido().getId());
		dto.setServicio_id(domain.getServicio().getId());
		return dto;
	}

	@Override
	protected PedidoDetalleDomain convertDtoToDomain(final PedidoDetalleDTO dto) {
		final PedidoDetalleDomain domain = new PedidoDetalleDomain();
		domain.setId(dto.getId());
		domain.setFecha(dto.getFecha());
		domain.setPedido(pedidoRepository.getById(dto.getPedido_id()));
		domain.setServicio(servicioRepository.getById(dto.getServicio_id()));
		return domain;
	}

}

package com.fiuni.sd.service.pedido;

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

import com.fiuni.sd.dao.IClienteDao;
import com.fiuni.sd.dao.IPedidoDao;
import com.fiuni.sd.domain.pedido.PedidoDomain;
import com.fiuni.sd.dto.pedido.PedidoDTO;
import com.fiuni.sd.dto.pedido.PedidoResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<PedidoDTO, PedidoDomain, PedidoResult>
		implements IPedidoService {

	@Autowired
	private IPedidoDao pedidoRepository; // repository

	@Autowired
	private IClienteDao clienteRepository; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_pedido_' + #result.getId()")
	public PedidoDTO save(final PedidoDTO dto) {
		return convertDomainToDto(pedidoRepository.save(convertDtoToDomain(dto)));
	}
	
	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_pedido_' + count")
	public Integer count() {
		Integer countCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_pedido_count", Integer.class);
		if (countCacheado != null) {
			return countCacheado;
		}
		return (int) pedidoRepository.count();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_pedido_' + #id")
	public PedidoDTO getById(final Integer id) {
		PedidoDTO pedidoCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_pedido_" + id, PedidoDTO.class);
		if (pedidoCacheado != null) {
			return pedidoCacheado;
		}
		return pedidoRepository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Pedido", "id", id));
	}

	@Override
	public PedidoResult getAll(final Pageable pageable) {
		final List<PedidoDTO> list = new ArrayList<>();
		final PedidoResult result = new PedidoResult();
		Page<PedidoDomain> pages = pedidoRepository.findAll(pageable);
		pages.forEach(pedido -> {
			PedidoDTO dto = convertDomainToDto(pedido);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_pedido_" + dto.getId(), dto);
		});
		result.setPedidos(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		result.setTotal((int) pedidoRepository.count());
		result.set_hasPrev(pages.hasPrevious());
		result.set_hasNext(pages.hasNext());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_pedido_' + #id")
	public PedidoDTO deleteById(final Integer id) {
		if (!pedidoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Pedido", "id", id);
		}
		PedidoDTO pedido = convertDomainToDto(pedidoRepository.getById(id));
		pedidoRepository.deleteById(id);
		return pedido;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_pedido_' + #id")
	public PedidoDTO update(final Integer id, final PedidoDTO dto) {
		if (!pedidoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Pedido", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Pedido", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_pedido_" + id);
		return convertDomainToDto(pedidoRepository.save(convertDtoToDomain(dto)));
	}

	@Override
	public PedidoResult getAllByCliente(final Integer idCliente, final Pageable pageable) {
		final List<PedidoDTO> list = new ArrayList<>();
		final PedidoResult result = new PedidoResult();
		Page<PedidoDomain> pages = pedidoRepository.findAllByCliente(clienteRepository.getById(idCliente), pageable);
		pages.forEach(pedido -> {
			PedidoDTO dto = convertDomainToDto(pedido);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_pedido_" + dto.getId(), dto);
		});
		result.setPedidos(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	protected PedidoDTO convertDomainToDto(final PedidoDomain domain) {
		final PedidoDTO dto = new PedidoDTO();
		dto.setId(domain.getId());
		dto.setEstado(domain.getEstado());
		dto.setId_cliente(domain.getCliente().getId());
		return dto;
	}

	@Override
	protected PedidoDomain convertDtoToDomain(final PedidoDTO dto) {
		final PedidoDomain domain = new PedidoDomain();
		domain.setId(dto.getId());
		domain.setEstado(dto.getEstado());
		domain.setCliente(clienteRepository.getById(dto.getId_cliente()));
		return domain;
	}

}

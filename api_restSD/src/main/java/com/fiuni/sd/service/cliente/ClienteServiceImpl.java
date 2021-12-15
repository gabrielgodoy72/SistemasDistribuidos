package com.fiuni.sd.service.cliente;

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
import com.fiuni.sd.domain.cliente.ClienteDomain;
import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<ClienteDTO, ClienteDomain, ClienteResult>
		implements IClienteService {

	@Autowired
	private IClienteDao clientDao; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_cliente_' + #result.getId()")
	public ClienteDTO save(final ClienteDTO dto) {
		return convertDomainToDto(clientDao.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_cliente_' + #id")
	public ClienteDTO getById(final Integer id) {
		ClienteDTO clienteCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_cliente_" + id, ClienteDTO.class);
		if (clienteCacheado != null) {
			return clienteCacheado;
		}
		return clientDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	public ClienteResult getAll(final Pageable pageable) {
		final List<ClienteDTO> list = new ArrayList<>();
		final ClienteResult result = new ClienteResult();
		Page<ClienteDomain> pages = clientDao.findAll(pageable);
		pages.forEach(cliente -> {
			ClienteDTO dto = convertDomainToDto(cliente);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_cliente_" + dto.getId(), dto);
		});
		result.setClientes(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_cliente_' + #id")
	public ClienteDTO deleteById(final Integer id) {
		if (!clientDao.existsById(id)) {
			throw new ResourceNotFoundException("Cliente", "id", id);
		}
		ClienteDTO cliente = convertDomainToDto(clientDao.getById(id));
		clientDao.deleteById(id);
		return cliente;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_cliente_' + #id")
	public ClienteDTO update(final Integer id, final ClienteDTO dto) {
		if (!clientDao.existsById(id)) {
			throw new ResourceNotFoundException("Cliente", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Cliente", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_cliente_" + id);
		return convertDomainToDto(clientDao.save(convertDtoToDomain(dto)));
	}

	@Override
	protected ClienteDTO convertDomainToDto(final ClienteDomain domain) {
		final ClienteDTO dto = new ClienteDTO();
		dto.setId(domain.getId());
		dto.setCi(domain.getCi());
		dto.setNombre(domain.getNombre());
		dto.setDireccion(domain.getDireccion());
		dto.setEmail(domain.getEmail());
		dto.setTelefono(domain.getTelefono());
		return dto;
	}

	@Override
	protected ClienteDomain convertDtoToDomain(final ClienteDTO dto) {
		final ClienteDomain domain = new ClienteDomain();
		domain.setId(dto.getId());
		domain.setCi(dto.getCi());
		domain.setNombre(dto.getNombre());
		domain.setDireccion(dto.getDireccion());
		domain.setEmail(dto.getEmail());
		domain.setTelefono(dto.getTelefono());
		return domain;
	}

}

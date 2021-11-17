package com.fiuni.sd.service.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IClienteDao;
import com.fiuni.sd.domain.cliente.ClienteDomain;
import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<ClienteDTO, ClienteDomain, ClienteResult>
		implements IClienteService {

	@Autowired
	private IClienteDao clientDao; // repository

	@Override
	public ClienteDTO save(final ClienteDTO dto) {
		return convertDomainToDto(clientDao.save(convertDtoToDomain(dto)));
	}

	@Override
	public ClienteDTO getById(final Integer id) {
		return clientDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
	}

	@Override
	public ClienteResult getAll(final Pageable pageable) {
		final ClienteResult result = new ClienteResult();
		result.setClientes(clientDao.findAll(pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!clientDao.existsById(id)) {
			throw new ResourceNotFoundException("Cliente", "id", id);
		}
		clientDao.deleteById(id);
	}

	@Override
	public ClienteDTO update(final Integer id, final ClienteDTO dto) {
		if (!clientDao.existsById(id)) {
			throw new ResourceNotFoundException("Cliente", "id", id);
		}
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

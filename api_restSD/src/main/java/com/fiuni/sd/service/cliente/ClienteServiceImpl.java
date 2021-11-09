package com.fiuni.sd.service.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IClienteDao;
import com.fiuni.sd.domain.cliente.ClienteDomain;
import com.fiuni.sd.dto.cliente.ClientDTO;
import com.fiuni.sd.dto.cliente.ClientResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class ClienteServiceImpl  extends BaseServiceImpl<ClientDTO, ClienteDomain, ClientResult> implements IClienteService{
	
	@Autowired
	private IClienteDao clientDao;

	@Override
	public ClientDTO save(ClientDTO dto) {
		final ClienteDomain clientDomain = convertDtoToDomain(dto);
		final ClienteDomain client = clientDao.save(clientDomain);
		return convertDomainToDto(client);
	}

	@Override
	public ClientDTO getById(Integer id) {
		Optional<ClienteDomain> result = clientDao.findById(id);
        ClientDTO client = null;
        if(result.isPresent()){
        	client = convertDomainToDto(result.get());
        } else {
            throw new RuntimeException("Did not find employee id: " + id);
        }
        return client;
	}

	@Override
	public ClientResult getAll(Pageable pageable) {
		final List<ClientDTO> clients = new ArrayList<>();
		Page<ClienteDomain> results = clientDao.findAll(pageable);
		for (ClienteDomain clientDomain : results) {
			clients.add(convertDomainToDto(clientDomain));
		}
		ClientResult clientResult = new ClientResult();
		clientResult.setClients(clients);
		return clientResult;
	}

	@Override
	public void deleteById(int id) {
		clientDao.deleteById(id);
	}

	@Override
	protected ClientDTO convertDomainToDto(ClienteDomain domain) {
		final ClientDTO dto = new ClientDTO();
		dto.setId(domain.getId());
		dto.setCi(domain.getCi());
		dto.setNombre(domain.getNombre());
		dto.setDireccion(domain.getDireccion());
		dto.setEmail(domain.getEmail());
		dto.setTelefono(domain.getTelefono());
        return dto;
	}

	@Override
	protected ClienteDomain convertDtoToDomain(ClientDTO dto) {
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

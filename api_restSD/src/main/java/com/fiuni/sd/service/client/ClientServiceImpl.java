package com.fiuni.sd.service.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.client.IClientDao;
import com.fiuni.sd.domain.client.ClientDomain;
import com.fiuni.sd.dto.client.ClientDTO;
import com.fiuni.sd.dto.client.ClientResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class ClientServiceImpl  extends BaseServiceImpl<ClientDTO, ClientDomain, ClientResult> implements IClientService{
	
	@Autowired
	private IClientDao clientDao;

	@Override
	public ClientDTO save(ClientDTO dto) {
		final ClientDomain clientDomain = convertDtoToDomain(dto);
		final ClientDomain client = clientDao.save(clientDomain);
		return convertDomainToDto(client);
	}

	@Override
	public ClientDTO getById(Integer id) {
		Optional<ClientDomain> result = clientDao.findById(id);
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
		Page<ClientDomain> results = clientDao.findAll(pageable);
		for (ClientDomain clientDomain : results) {
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
	protected ClientDTO convertDomainToDto(ClientDomain domain) {
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
	protected ClientDomain convertDtoToDomain(ClientDTO dto) {
		final ClientDomain domain = new ClientDomain();
		domain.setId(dto.getId());
		domain.setCi(dto.getCi());
		domain.setNombre(dto.getNombre());
		domain.setDireccion(dto.getDireccion());
		domain.setEmail(dto.getEmail());
		domain.setTelefono(dto.getTelefono());
        return domain;
	}

}

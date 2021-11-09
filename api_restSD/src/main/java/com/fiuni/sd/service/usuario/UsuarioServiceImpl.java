package com.fiuni.sd.service.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IUsuarioDao;
import com.fiuni.sd.domain.credenciales.UsuarioDomain;
import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<UsuarioDTO, UsuarioDomain, UsuarioResult> implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao userDao;

	@Override
	public UsuarioDTO save(UsuarioDTO dto) {
		final UsuarioDomain userDomain = convertDtoToDomain(dto);
		final UsuarioDomain user = userDao.save(userDomain);
		return convertDomainToDto(user);
	}

	@Override
	public UsuarioDTO getById(Integer id) {
		Optional<UsuarioDomain> result = userDao.findById(id);
        UsuarioDTO userDto = null;
        if(result.isPresent()){
        	userDto = convertDomainToDto(result.get());
        } else {
            throw new RuntimeException("Did not find employee id: " + id);
        }
        return userDto;
	}

	@Override
	public UsuarioResult getAll(Pageable pageable) {
		final List<UsuarioDTO> usersDto = new ArrayList<>();
		Page<UsuarioDomain> results = userDao.findAll(pageable);
		for (UsuarioDomain userDomain : results) {
			usersDto.add(convertDomainToDto(userDomain));
		}
		UsuarioResult userResult = new UsuarioResult();
		userResult.setUsers(usersDto);
		return userResult;
	}

	@Override
	public UsuarioResult getAllUsers() {
		final List<UsuarioDTO> usersDto = new ArrayList<>();
		List<UsuarioDomain> results = userDao.findAll();
		for (UsuarioDomain userDomain : results) {
			usersDto.add(convertDomainToDto(userDomain));
		}
		UsuarioResult userResult = new UsuarioResult();
		userResult.setUsers(usersDto);
		return userResult;
	}

	@Override
	public void deleteById(int id) {
		userDao.deleteById(id);
	}

	@Override
	protected UsuarioDTO convertDomainToDto(UsuarioDomain domain) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(domain.getId());
		dto.setFirstName(domain.getFirstName());
		dto.setLastName(domain.getLastName());
		dto.setEmail(domain.getEmail());
		dto.setUsername(domain.getUsername());
        return dto;
	}

	@Override
	protected UsuarioDomain convertDtoToDomain(UsuarioDTO dto) {
		final UsuarioDomain domain = new UsuarioDomain();
		domain.setId(dto.getId());
		domain.setFirstName(dto.getFirstName());
		domain.setLastName(dto.getLastName());
		domain.setEmail(dto.getEmail());
		domain.setUsername(dto.getUsername());
        return domain;
	}

}

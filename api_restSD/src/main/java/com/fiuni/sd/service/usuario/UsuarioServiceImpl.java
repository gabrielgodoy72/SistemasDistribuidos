package com.fiuni.sd.service.usuario;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IRolDao;
import com.fiuni.sd.dao.IUsuarioDao;
import com.fiuni.sd.domain.credenciales.RoleDomain;
import com.fiuni.sd.domain.credenciales.UsuarioDomain;
import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<UsuarioDTO, UsuarioDomain, UsuarioResult>
		implements IUsuarioService {

	@Autowired
	private IUsuarioDao userDao; // repository

	@Autowired
	private IRolDao rolDao; // repository

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UsuarioDTO save(final UsuarioDTO dto) {
		if (userDao.findByEmail(dto.getEmail()) == null) {
			throw new ResourceNotFoundException("Usuario", "email", dto.getEmail());
		}
		return convertDomainToDto(userDao.save(convertDtoToDomain(dto)));
	}

	@Override
	public UsuarioDTO createUserAccount(final UsuarioDTO signUp) {
		final UsuarioDomain user = convertDtoToDomain(signUp);
		user.setPassword(passwordEncoder.encode(signUp.getPassword()));
		final RoleDomain roleUser = new RoleDomain();
		roleUser.setNombre("ROLE_USER");
		roleUser.setUsuarios(Set.of(user));
		user.setRoles(Set.of(roleUser));
		return convertDomainToDto(userDao.save(user));
	}

	@Override
	public UsuarioDTO getById(final Integer id) {
		return userDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
	}

	@Override
	public UsuarioResult getAll(final Pageable pageable) {
		final UsuarioResult result = new UsuarioResult();
		result.setUsers(userDao.findAll(pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!userDao.existsById(id)) {
			throw new ResourceNotFoundException("Usuario", "id", id);
		}
		userDao.deleteById(id);
	}

	@Override
	public UsuarioDTO update(Integer id, UsuarioDTO dto) {
		if (!userDao.existsById(id)) {
			throw new ResourceNotFoundException("Usuario", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Usuario", "id", id);
		}
		return convertDomainToDto(userDao.save(convertDtoToDomain(dto)));
	}

	@Override
	protected UsuarioDTO convertDomainToDto(UsuarioDomain domain) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(domain.getId());
		dto.setNombre(domain.getNombre());
		dto.setApellido(domain.getApellido());
		dto.setEmail(domain.getEmail());
		return dto;
	}

	@Override
	protected UsuarioDomain convertDtoToDomain(UsuarioDTO dto) {
		final UsuarioDomain domain = new UsuarioDomain();
		domain.setId(dto.getId());
		domain.setNombre(dto.getNombre());
		domain.setApellido(dto.getApellido());
		domain.setEmail(dto.getEmail());
		return domain;
	}

}

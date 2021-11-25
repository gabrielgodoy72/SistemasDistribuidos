package com.fiuni.sd.service.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.dao.IRolDao;
import com.fiuni.sd.dao.IUsuarioDao;
import com.fiuni.sd.domain.credenciales.RoleDomain;
import com.fiuni.sd.domain.credenciales.UsuarioDomain;
import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
@Transactional
public class UsuarioServiceImpl extends BaseServiceImpl<UsuarioDTO, UsuarioDomain, UsuarioResult>
		implements IUsuarioService {

	@Autowired
	private IUsuarioDao userDao; // repository

	@Autowired
	private IRolDao rolDao; // repository

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public UsuarioDTO save(final UsuarioDTO dto) {
		if (userDao.findByEmail(dto.getEmail()) == null) {
			throw new ResourceNotFoundException("Usuario", "email", dto.getEmail());
		}
		final UsuarioDomain domain = userDao.save(convertDtoToDomain(dto));
		if (dto.getId() == null) {
			cacheManager.getCache(Setting.CACHE_NAME).put("api_usuario_" + domain.getId(), domain);
		}
		return convertDomainToDto(domain);
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
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_usuario_' + #id")
	public UsuarioDTO getById(final Integer id) {
		return userDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
	}

	@Override
	public UsuarioResult getAll(final Pageable pageable) {
		final List<UsuarioDTO> list = new ArrayList<>();
		final UsuarioResult result = new UsuarioResult();
		Page<UsuarioDomain> pages = userDao.findAll(pageable);
		pages.forEach(usuario -> {
			UsuarioDTO dto = convertDomainToDto(usuario);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_usuario_" + dto.getId(), dto);
		});
		result.setUsers(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
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

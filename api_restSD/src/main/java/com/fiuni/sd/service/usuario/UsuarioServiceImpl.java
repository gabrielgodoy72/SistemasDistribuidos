package com.fiuni.sd.service.usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
	@CachePut(value = Setting.CACHE_NAME, key = "'api_usuario_' + #result.getId()")
	public UsuarioDTO save(final UsuarioDTO dto) {
		if (!userDao.findByUsername(dto.getUsername()).isEmpty()) {
			throw new ResourceNotFoundException("Usuario", "username", dto.getUsername());
		}
		final UsuarioDomain user = convertDtoToDomain(dto);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		final RoleDomain role = new RoleDomain();
		role.setNombre("ROLE_USER");
		role.setUsuarios(Set.of(user));
		user.setRoles(Set.of(rolDao.findByDescripcion("ROLE_USER").orElseThrow())); // por defecto es ROLE_USER
		return convertDomainToDto(userDao.save(user));
	}
	
	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_usuario_' + count")
	public Integer count() {
		Integer countCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_usuario_count", Integer.class);
		if (countCacheado != null) {
			return countCacheado;
		}
		return (int) userDao.count();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_usuario_' + #id")
	public UsuarioDTO getById(final Integer id) {
		UsuarioDTO usuarioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_usuario_" + id, UsuarioDTO.class);
		if (usuarioCacheado != null) {
			return usuarioCacheado;
		}
		return userDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_usuario_' + #username")
	public UsuarioDTO getByUsername(final String username) {
		UsuarioDTO usuarioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_usuario_" + username, UsuarioDTO.class);
		if (usuarioCacheado != null) {
			return usuarioCacheado;
		}
		return userDao.findByUsername(username)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
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
		result.setTotal((int) userDao.count());
		result.set_hasPrev(pages.hasPrevious());
		result.set_hasNext(pages.hasNext());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_usuario_' + #id")
	public UsuarioDTO deleteById(final Integer id) {
		if (!userDao.existsById(id)) {
			throw new ResourceNotFoundException("Usuario", "id", id);
		}
		UsuarioDTO usuario = convertDomainToDto(userDao.getById(id));
		userDao.deleteById(id);
		return usuario;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_usuario_' + #id")
	public UsuarioDTO update(Integer id, UsuarioDTO dto) {
		if (!userDao.existsById(id)) {
			throw new ResourceNotFoundException("Usuario", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Usuario", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_usuario_" + id);
		return convertDomainToDto(userDao.save(convertDtoToDomain(dto)));
	}

	@Override
	protected UsuarioDTO convertDomainToDto(UsuarioDomain domain) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(domain.getId());
		dto.setNombre(domain.getNombre());
		dto.setApellido(domain.getApellido());
		dto.setEmail(domain.getEmail());
		dto.setUsername(domain.getUsername());
		dto.setPassword(domain.getPassword());
		Set<Integer> roles_id = new HashSet<>();
		// llama a roles para traer los roles del usuario
		// rolDao.ge
		domain.getRoles().forEach(rol -> {
			roles_id.add(rol.getId());
		});
		dto.setRoles_id(roles_id);
		return dto;
	}

	@Override
	protected UsuarioDomain convertDtoToDomain(UsuarioDTO dto) {
		final UsuarioDomain domain = new UsuarioDomain();
		domain.setId(dto.getId());
		domain.setNombre(dto.getNombre());
		domain.setApellido(dto.getApellido());
		domain.setEmail(dto.getEmail());
		domain.setUsername(dto.getUsername());
		domain.setPassword(dto.getPassword());
		Set<RoleDomain> roles = new HashSet<>();
		// llama a roles para traer los roles del usuario
		dto.getRoles_id().forEach(rol_id -> {
			roles.add(rolDao.getById(rol_id));
		});
		domain.setRoles(roles);
		return domain;
	}

}

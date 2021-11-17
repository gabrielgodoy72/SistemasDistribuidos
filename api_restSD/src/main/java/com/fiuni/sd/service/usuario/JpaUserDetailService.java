package com.fiuni.sd.service.usuario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.dao.IUsuarioDao;
import com.fiuni.sd.domain.credenciales.UsuarioDomain;

@Service
public class JpaUserDetailService implements UserDetailsService {

	private IUsuarioDao usuarioDao; // repository

	@Autowired
	public JpaUserDetailService(final IUsuarioDao userRepository) {
		this.usuarioDao = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String email) {
		final UsuarioDomain user = usuarioDao.findByEmail(email)//
				.orElseThrow(() -> new com.fiuni.sd.utils.ResourceNotFoundException("Usuario", "email", email));

		final List<GrantedAuthority> authorities = user.getRoles().stream() //
				.map(role -> new SimpleGrantedAuthority(role.getNombre())) //
				.collect(Collectors.toList());

		return new User( //
				user.getEmail(), //
				user.getPassword(), //
				true, // enabled
				true, // accountNonExpired
				true, // credentialsNonExpired
				true, // accountNonLocked
				authorities //
		);
	}

}

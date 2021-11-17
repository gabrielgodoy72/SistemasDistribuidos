package com.fiuni.sd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiuni.sd.dto.usuario.LoginRequest;
import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.service.usuario.IUsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping(path = "/singup")
	public ResponseEntity<UsuarioDTO> createUser(@RequestBody @Valid final UsuarioDTO dto) {
		try {
			return ResponseEntity.ok(usuarioService.createUserAccount(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping(path = "/login")
	public ResponseEntity<String> loginUser(@RequestBody @Valid final LoginRequest dto) {
		// delegate the work to JwtAuthenticationFilter class
		return ResponseEntity.ok("Sesion Iniciada");
	}
}

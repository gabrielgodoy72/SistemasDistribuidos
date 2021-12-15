package com.fiuni.sd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.fiuni.sd.service.usuario.IUsuarioService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/account")
public class UsuarioResource {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping(path = "/register")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody @Valid final UsuarioDTO dto) {
		try {
			return ResponseEntity.ok(usuarioService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/usuario/{id}")
	public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(usuarioService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/usuarios/page/{page_num}")
	public UsuarioResult getUsuarios(@PathVariable(value = "page_num") final Integer pageNum) {
		return usuarioService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/usuario/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final UsuarioDTO userDto) {
		return ResponseEntity.ok(usuarioService.update(id, userDto));
	}

	@DeleteMapping("/usuario/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<UsuarioDTO> deleteUsuario(@PathVariable final Integer id) {
		return ResponseEntity.ok(usuarioService.deleteById(id));
	}
}

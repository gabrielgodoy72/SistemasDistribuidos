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

import com.fiuni.sd.dto.rol.RolDTO;
import com.fiuni.sd.dto.rol.RolResult;
import com.fiuni.sd.service.rol.IRolService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/account")
public class RolResource {

	@Autowired
	private IRolService rolService;

	@PostMapping(path = "/rol")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<RolDTO> createRol(@RequestBody @Valid final RolDTO rolDto) {
		try {
			return ResponseEntity.ok(rolService.save(rolDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/rol/{id}")
	public ResponseEntity<RolDTO> getRol(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(rolService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/rol/search/nombre/{nombre}")
	public ResponseEntity<RolDTO> getRolByName(@PathVariable(value = "nombre") final String nombre) {
		try {
			return ResponseEntity.ok(rolService.getByNombre(nombre));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/rol/search/descripcion/{descripcion}")
	public ResponseEntity<RolDTO> getRolByDescipcion(@PathVariable(value = "descripcion") final String descripcion) {
		try {
			return ResponseEntity.ok(rolService.getByDescripcion(descripcion));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/roles/page/{page_num}")
	public RolResult getRoles(@PathVariable(value = "page_num") final Integer pageNum) {
		return rolService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/rol/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<RolDTO> updateRol(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final RolDTO rolDto) {
		return ResponseEntity.ok(rolService.update(id, rolDto));
	}

	@DeleteMapping("/rol/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<RolDTO> deleteRol(@PathVariable final Integer id) {
		try {
			return ResponseEntity.ok(rolService.deleteById(id));
		} catch (Exception ex) {
			return ResponseEntity.noContent().build();
		}
	}
}

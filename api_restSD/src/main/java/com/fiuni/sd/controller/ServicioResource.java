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

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.fiuni.sd.service.servicio.IServicioService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api")
public class ServicioResource {

	@Autowired
	private IServicioService servicioService;

	@PostMapping(path = "/servicio")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<ServicioDTO> createServicio(@RequestBody @Valid final ServicioDTO servicioDto) {
		try {
			return ResponseEntity.ok(servicioService.save(servicioDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/servicio/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<ServicioDTO> getServicio(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(servicioService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/servicio/search/descripcion/{descripcion}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<ServicioDTO> getServicioByDescripcion(
			@PathVariable(value = "descripcion") final String descripcion) {
		try {
			return ResponseEntity.ok(servicioService.getByDescripcion(descripcion));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/servicios/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ServicioResult getServicios(@PathVariable(value = "page_num") final Integer pageNum) {
		return servicioService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/servicio/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<ServicioDTO> updateServicio(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final ServicioDTO servicioDto) {
		try {
			return ResponseEntity.ok(servicioService.update(id, servicioDto));
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/servicio/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<ServicioDTO> deleteServicio(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(servicioService.deleteById(id));
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
}

package com.fiuni.sd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.fiuni.sd.service.proveedor.IProveedorService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api")
public class ProveedorResource {

	@Autowired
	private IProveedorService proveedorService;

	@PostMapping(path = "/proveedor")
	public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody @Valid final ProveedorDTO dto) {
		try {
			return ResponseEntity.ok(proveedorService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/proveedor/{id}")
	public ResponseEntity<ProveedorDTO> getProveedor(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(proveedorService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/proveedor/search/nombre/{nombre}")
	public ResponseEntity<ProveedorDTO> getProveedorByNombre(@PathVariable(value = "nombre") final String nombre) {
		try {
			return ResponseEntity.ok(proveedorService.getByNombre(nombre));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/proveedor/search/ruc/{ruc}")
	public ResponseEntity<ProveedorDTO> getProveedorByRuc(@PathVariable(value = "ruc") final String ruc) {
		try {
			return ResponseEntity.ok(proveedorService.getByRuc(ruc));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/proveedores/page/{page_num}")
	public ProveedorResult getProveedores(@PathVariable(value = "page_num") final Integer pageNum) {
		return proveedorService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/proveedor/{id}")
	public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final ProveedorDTO dto) {
		try {
			return ResponseEntity.ok(proveedorService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("proveedores/{id}")
	public ResponseEntity<ProveedorDTO> deleteProveedor(@PathVariable(value = "id") final Integer id) {
		try {
			proveedorService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.noContent().build();
		}
	}
}
package com.fiuni.sd.controller;

import java.util.Date;

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

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.service.factura.compra.IFacturaCompraService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api/factura")
public class FacturaCompraResource {

	@Autowired
	private IFacturaCompraService facturaCompraService;

	@PostMapping(path = "/compra")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaCompraDTO> create(@RequestBody @Valid final FacturaCompraDTO dto) {
		try {
			return ResponseEntity.ok(facturaCompraService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/compra/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaCompraDTO> getById(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(facturaCompraService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/compra/search/numero/{numero}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaCompraDTO> getByNumero(@PathVariable(value = "numero") final String numero) {
		try {
			return ResponseEntity.ok(facturaCompraService.getByNumero(numero));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// VER !
	@GetMapping(path = "/compra/search/date/{fecha}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public FacturaCompraResult getAllByFecha(@PathVariable(value = "fecha") final Date fecha,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaCompraService.getAllByFecha(fecha, PageRequest.of(pageNum, Setting.PAGE_SIZE));

	}

	@GetMapping(path = "/compra/search/proveedor/{idProveedor}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public FacturaCompraResult getAllByIdProveedor(@PathVariable(value = "idProveedor") final Integer idProveedor,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaCompraService.getAllByProveedor(idProveedor, PageRequest.of(pageNum, Setting.PAGE_SIZE));

	}

	@GetMapping(path = "/compra/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public FacturaCompraResult getAll(@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaCompraService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/compra/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaCompraDTO> update(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final FacturaCompraDTO dto) {
		try {
			return ResponseEntity.ok(facturaCompraService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/compra/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaCompraDTO> delete(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(facturaCompraService.deleteById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}

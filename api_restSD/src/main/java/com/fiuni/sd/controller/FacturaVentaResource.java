package com.fiuni.sd.controller;

import java.util.Date;

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

import com.fiuni.sd.dto.factura.venta.FacturaVentaDTO;
import com.fiuni.sd.dto.factura.venta.FacturaVentaResult;
import com.fiuni.sd.service.factura.venta.IFacturaVentaService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api/factura")
public class FacturaVentaResource {

	@Autowired
	private IFacturaVentaService facturaVentaService;

	@PostMapping(path = "/venta")
	public ResponseEntity<FacturaVentaDTO> create(@RequestBody @Valid final FacturaVentaDTO dto) {
		try {
			return ResponseEntity.ok(facturaVentaService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/venta/{id}")
	public ResponseEntity<FacturaVentaDTO> getById(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(facturaVentaService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/venta/search/numero/{numero}")
	public ResponseEntity<FacturaVentaDTO> getByNumero(@PathVariable(value = "numero") final String numero) {
		try {
			return ResponseEntity.ok(facturaVentaService.getByNumero(numero));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/venta/search/date/{fecha}/page/{page_num}")
	public ResponseEntity<FacturaVentaResult> getAllByFecha(@PathVariable(value = "fecha") final Date fecha,
			@PathVariable(value = "page_num") final Integer pageNum) {
		try {
			return ResponseEntity.ok(facturaVentaService.getAllByFecha(fecha, PageRequest.of(pageNum, Setting.PAGE_SIZE)));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/venta/search/proveedor/{cliente_id}/page/{page_num}")
	public ResponseEntity<FacturaVentaResult> getAllByIdCliente(
			@PathVariable(value = "cliente_id") final Integer idCliente,
			@PathVariable(value = "page_num") final Integer pageNum) {
		try {
			return ResponseEntity.ok(facturaVentaService.getAllByCliente(idCliente, PageRequest.of(pageNum, Setting.PAGE_SIZE)));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/venta/page/{page_num}")
	public ResponseEntity<FacturaVentaResult> getAll(@PathVariable(value = "page_num") final Integer pageNum) {
		try {
			return ResponseEntity.ok(facturaVentaService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE)));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(path = "/venta/{id}")
	public ResponseEntity<FacturaVentaDTO> update(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final FacturaVentaDTO dto) {
		try {
			return ResponseEntity.ok(facturaVentaService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/venta/{id}")
	public ResponseEntity<FacturaVentaDTO> delete(@PathVariable(value = "id") final Integer id) {
		try {
			facturaVentaService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}

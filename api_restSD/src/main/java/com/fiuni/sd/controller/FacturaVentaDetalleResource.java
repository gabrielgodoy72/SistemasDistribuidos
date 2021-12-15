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

import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleResult;
import com.fiuni.sd.service.factura_detalle.venta.IFacturaVentaDetalleService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api/factura/venta")
public class FacturaVentaDetalleResource {

	@Autowired
	private IFacturaVentaDetalleService facturaVentaDetalleService;

	@PostMapping(path = "/detalle")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaVentaDetalleDTO> create(@RequestBody @Valid final FacturaVentaDetalleDTO dto) {
		try {
			return ResponseEntity.ok(facturaVentaDetalleService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/detalle/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaVentaDetalleDTO> getById(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(facturaVentaDetalleService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/detalle/search/factura/{factura_id}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public FacturaVentaDetalleResult getAllByIdFactura(@PathVariable(value = "factura_id") final Integer idFactura,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaVentaDetalleService.getAllByFactura(idFactura, PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@GetMapping(path = "/detalle/search/servicio/{servicio_id}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public FacturaVentaDetalleResult getAllByIdServicio(@PathVariable(value = "servicio_id") final Integer idServicio,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaVentaDetalleService.getAllByServicio(idServicio, PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@GetMapping(path = "/detalle/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public FacturaVentaDetalleResult getAll(@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaVentaDetalleService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/detalle/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaVentaDetalleDTO> update(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final FacturaVentaDetalleDTO dto) {
		try {
			return ResponseEntity.ok(facturaVentaDetalleService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/detalle/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<FacturaVentaDetalleDTO> delete(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(facturaVentaDetalleService.deleteById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}

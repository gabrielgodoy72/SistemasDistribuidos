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

import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.fiuni.sd.service.factura_detalle.compra.IFacturaCompraDetalleService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api/factura/compra")
public class FacturaCompraDetalleResource {

	@Autowired
	private IFacturaCompraDetalleService facturaCompraDetalleService;

	@PostMapping(path = "/detalle")
	public ResponseEntity<FacturaCompraDetalleDTO> create(@RequestBody @Valid final FacturaCompraDetalleDTO dto) {
		try {
			return ResponseEntity.ok(facturaCompraDetalleService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/detalle/{id}")
	public ResponseEntity<FacturaCompraDetalleDTO> getById(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(facturaCompraDetalleService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/detalle/search/factura/{factura_id}/page/{page_num}")
	public FacturaCompraDetalleResult getAllByIdFactura(@PathVariable(value = "factura_id") final Integer idFactura,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaCompraDetalleService.getAllByFactura(idFactura, PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@GetMapping(path = "/detalle/search/producto/{producto_id}/page/{page_num}")
	public FacturaCompraDetalleResult getAllByIdProducto(@PathVariable(value = "producto_id") final Integer idProducto,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaCompraDetalleService.getAllByProducto(idProducto, PageRequest.of(pageNum, Setting.PAGE_SIZE));

	}

	@GetMapping(path = "/detalle/page/{page_num}")
	public FacturaCompraDetalleResult getAll(@PathVariable(value = "page_num") final Integer pageNum) {
		return facturaCompraDetalleService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/detalle/{id}")
	public ResponseEntity<FacturaCompraDetalleDTO> update(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final FacturaCompraDetalleDTO dto) {
		try {
			return ResponseEntity.ok(facturaCompraDetalleService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/detalle/{id}")
	public ResponseEntity<FacturaCompraDetalleDTO> delete(@PathVariable(value = "id") final Integer id) {
		try {
			facturaCompraDetalleService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}

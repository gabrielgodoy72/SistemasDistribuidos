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

import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.service.producto.IProductoService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api")
public class ProductoResource {

	@Autowired
	private IProductoService productoService;

	@PostMapping(path = "/producto")
	public ResponseEntity<ProductoDTO> createProducto(@RequestBody @Valid final ProductoDTO productoDto) {
		try {
			return ResponseEntity.ok(productoService.save(productoDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/producto/{id}")
	public ResponseEntity<ProductoDTO> getProducto(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(productoService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/producto/search/descripcion/{descripcion}")
	public ResponseEntity<ProductoDTO> getProductoByDescripcion(
			@PathVariable(value = "descripcion") String descripcion) {
		try {
			return ResponseEntity.ok(productoService.getByDescripcion(descripcion));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/productos/page/{page_num}")
	public ProductoResult getProductos(@PathVariable(value = "page_num") Integer pageNum) {
		return productoService.getAll(PageRequest.of(pageNum - 1, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/producto/{id}")
	public ResponseEntity<ProductoDTO> updateProducto(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final ProductoDTO productoDto) {
		return ResponseEntity.ok(productoService.update(id, productoDto));
	}

	@DeleteMapping("/producto/{id}")
	public ResponseEntity<ProductoDTO> deleteProducto(@PathVariable Integer id) {
		productoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
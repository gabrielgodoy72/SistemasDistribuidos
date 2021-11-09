package com.fiuni.sd.controller;

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
	
	@PostMapping(path = "productos")
	public ResponseEntity<ProductoDTO> createClient(@RequestBody ProductoDTO productoDto) {
		if(productoDto.getId() == null || productoDto.getId() == 0) {
			ProductoDTO newProducto = productoService.save(productoDto);
			return ResponseEntity.ok(newProducto);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "productos/{id}")
	public ResponseEntity<ProductoDTO> readClient(@PathVariable(value = "id") Integer id) {
		try {
			ProductoDTO clientDto = productoService.getById(id);
			return ResponseEntity.ok(clientDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "productos/page/{page_num}")
	public ProductoResult readClients(@PathVariable(value = "page_num") Integer pageNum) {
		return productoService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "productos")
    public ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoDTO productoDto) {
		if(productoDto.getId() != null || productoDto.getId() != 0) {
			try {
				productoService.getById(productoDto.getId());
				ProductoDTO editedProducto = productoService.save(productoDto);
		        return ResponseEntity.ok(editedProducto);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("productos/{id}")
    public ResponseEntity<ProductoDTO> deleteProducto(@PathVariable Integer id) {
        try {
        	productoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
}
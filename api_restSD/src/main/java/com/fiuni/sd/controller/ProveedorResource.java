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

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.fiuni.sd.service.proveedor.IProveedorService;
import com.fiuni.sd.utils.Setting;

@RestController 
@RequestMapping("/api")
public class ProveedorResource {
	
	@Autowired
    private IProveedorService proveedorService;
	
	@PostMapping(path = "proveedores")
	public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDto) {
		if(proveedorDto.getId() == null || proveedorDto.getId() == 0) {
			ProveedorDTO newProveedor = proveedorService.save(proveedorDto);
			return ResponseEntity.ok(newProveedor);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "proveedores/{id}")
	public ResponseEntity<ProveedorDTO> readProveedor(@PathVariable(value = "id") Integer id) {
		try {
			ProveedorDTO proveedorDto = proveedorService.getById(id);
			return ResponseEntity.ok(proveedorDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "proveedores/page/{page_num}")
	public ProveedorResult readProveedores(@PathVariable(value = "page_num") Integer pageNum) {
		return proveedorService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "proveedores")
    public ResponseEntity<ProveedorDTO> updateProveedor(@RequestBody ProveedorDTO proveedorDto) {
		if(proveedorDto.getId() != null || proveedorDto.getId() != 0) {
			try {
				proveedorService.getById(proveedorDto.getId());
				ProveedorDTO editedProveedor = proveedorService.save(proveedorDto);
		        return ResponseEntity.ok(editedProveedor);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("proveedores/{id}")
    public ResponseEntity<ProveedorDTO> deleteProveedor(@PathVariable Integer id) {
        try {
        	proveedorService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
}
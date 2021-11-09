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

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.fiuni.sd.service.servicio.IServicioService;
import com.fiuni.sd.utils.Setting;

@RestController 
@RequestMapping("/api")
public class ServicioResource {
	
	@Autowired
    private IServicioService servicioService;
	
	@PostMapping(path = "servicios")
	public ResponseEntity<ServicioDTO> createServicio(@RequestBody ServicioDTO servicioDto) {
		if(servicioDto.getId() == null || servicioDto.getId() == 0) {
			ServicioDTO newServicio = servicioService.save(servicioDto);
			return ResponseEntity.ok(newServicio);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "servicios/{id}")
	public ResponseEntity<ServicioDTO> readServicio(@PathVariable(value = "id") Integer id) {
		try {
			ServicioDTO servicioDto = servicioService.getById(id);
			return ResponseEntity.ok(servicioDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "servicios/page/{page_num}")
	public ServicioResult readServicos(@PathVariable(value = "page_num") Integer pageNum) {
		return servicioService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "servicios")
    public ResponseEntity<ServicioDTO> updateServicio(@RequestBody ServicioDTO servicioDto) {
		if(servicioDto.getId() != null || servicioDto.getId() != 0) {
			try {
				servicioService.getById(servicioDto.getId());
				ServicioDTO editedServicio = servicioService.save(servicioDto);
		        return ResponseEntity.ok(editedServicio);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("servicios/{id}")
    public ResponseEntity<ServicioDTO> deleteServicio(@PathVariable Integer id) {
        try {
        	servicioService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
}
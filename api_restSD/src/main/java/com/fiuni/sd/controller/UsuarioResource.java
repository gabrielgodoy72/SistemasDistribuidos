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

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioDTO2;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.fiuni.sd.service.usuario.IUsuarioService;
import com.fiuni.sd.utils.Setting;

@RestController 
@RequestMapping("/api")
public class UsuarioResource {
	
	@Autowired
	private IUsuarioService userService;
	
	@PostMapping(path = "usuario")
	public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO2 userDto) {
		if(userDto.getId() == null || userDto.getId() == 0) {
			UsuarioDTO newUser = userService.saveFirst(userDto);
			return ResponseEntity.ok(newUser);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "usuario/{id}")
	public ResponseEntity<UsuarioDTO> readUser(@PathVariable(value = "id") Integer id) {
		try {
			UsuarioDTO userDto = userService.getById(id);
			return ResponseEntity.ok(userDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "usuario/page/{page_num}")
	public UsuarioResult readUsers(@PathVariable(value = "page_num") Integer pageNum) {
		return userService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "usuario")
    public ResponseEntity<UsuarioDTO> updateUser(@RequestBody UsuarioDTO userDto) {
		if(userDto.getId() != null || userDto.getId() != 0) {
			try {
				userService.getById(userDto.getId());
				UsuarioDTO editedUser = userService.save(userDto);
		        return ResponseEntity.ok(editedUser);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("usuario/{id}")
    public ResponseEntity<UsuarioDTO> deleteUser(@PathVariable Integer id) {
        try {
        	userService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
}

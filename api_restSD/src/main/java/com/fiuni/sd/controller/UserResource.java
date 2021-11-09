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

import com.fiuni.sd.dto.user.UserDTO;
import com.fiuni.sd.dto.user.UserResult;
import com.fiuni.sd.service.user.IUserService;
import com.fiuni.sd.utils.Setting;

@RestController 
@RequestMapping("/api")
public class UserResource {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping(path = "user")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
		if(userDto.getId() == null || userDto.getId() == 0) {
			UserDTO newUser = userService.save(userDto);
			return ResponseEntity.ok(newUser);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "user/{id}")
	public ResponseEntity<UserDTO> readUser(@PathVariable(value = "id") Integer id) {
		try {
			UserDTO userDto = userService.getById(id);
			return ResponseEntity.ok(userDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "user/page/{page_num}")
	public UserResult readUsers(@PathVariable(value = "page_num") Integer pageNum) {
		return userService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "user")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto) {
		if(userDto.getId() != null || userDto.getId() != 0) {
			try {
				userService.getById(userDto.getId());
				UserDTO editedUser = userService.save(userDto);
		        return ResponseEntity.ok(editedUser);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("user/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer id) {
        try {
        	userService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
}

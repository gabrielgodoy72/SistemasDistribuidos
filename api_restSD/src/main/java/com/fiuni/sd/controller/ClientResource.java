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

import com.fiuni.sd.dto.client.ClientDTO;
import com.fiuni.sd.dto.client.ClientResult;
import com.fiuni.sd.service.client.IClientService;
import com.fiuni.sd.utils.Setting;

@RestController 
@RequestMapping("/api")
public class ClientResource {
	
	@Autowired
    private IClientService clientService;
	
	@PostMapping(path = "client")
	public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDto) {
		if(clientDto.getId() == null || clientDto.getId() == 0) {
			ClientDTO newClient = clientService.save(clientDto);
			return ResponseEntity.ok(newClient);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "client/{id}")
	public ResponseEntity<ClientDTO> readClient(@PathVariable(value = "id") Integer id) {
		try {
			ClientDTO clientDto = clientService.getById(id);
			return ResponseEntity.ok(clientDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "client/page/{page_num}")
	public ClientResult readClients(@PathVariable(value = "page_num") Integer pageNum) {
		return clientService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "client")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDto) {
		if(clientDto.getId() != null || clientDto.getId() != 0) {
			try {
				clientService.getById(clientDto.getId());
				ClientDTO editedClient = clientService.save(clientDto);
		        return ResponseEntity.ok(editedClient);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("client/{id}")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable Integer id) {
        try {
        	clientService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
}

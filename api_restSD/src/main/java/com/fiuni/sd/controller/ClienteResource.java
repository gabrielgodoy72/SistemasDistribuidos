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

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.fiuni.sd.service.cliente.IClienteService;
import com.fiuni.sd.utils.Setting;

@RestController 
@RequestMapping("/api")
public class ClienteResource {
	
	@Autowired
    private IClienteService clientService;
	
	@PostMapping(path = "clientes")
	public ResponseEntity<ClienteDTO> createClient(@RequestBody ClienteDTO clientDto) {
		if(clientDto.getId() == null || clientDto.getId() == 0) {
			ClienteDTO newClient = clientService.save(clientDto);
			return ResponseEntity.ok(newClient);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "clientes/{id}")
	public ResponseEntity<ClienteDTO> readClient(@PathVariable(value = "id") Integer id) {
		try {
			ClienteDTO clientDto = clientService.getById(id);
			return ResponseEntity.ok(clientDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "clientes/page/{page_num}")
	public ClienteResult readClients(@PathVariable(value = "page_num") Integer pageNum) {
		return clientService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "clientes")
    public ResponseEntity<ClienteDTO> updateClient(@RequestBody ClienteDTO clientDto) {
		if(clientDto.getId() != null || clientDto.getId() != 0) {
			try {
				clientService.getById(clientDto.getId());
				ClienteDTO editedClient = clientService.save(clientDto);
		        return ResponseEntity.ok(editedClient);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("clientes/{id}")
    public ResponseEntity<ClienteDTO> deleteClient(@PathVariable Integer id) {
        try {
        	clientService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
}

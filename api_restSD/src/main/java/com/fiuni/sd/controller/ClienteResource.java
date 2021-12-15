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

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.fiuni.sd.service.cliente.IClienteService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api")
public class ClienteResource {

	@Autowired
	private IClienteService clienteService;

	@PostMapping(path = "/cliente")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<ClienteDTO> createClient(@RequestBody @Valid final ClienteDTO clientDto) {
		try {
			return ResponseEntity.ok(clienteService.save(clientDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/cliente/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<ClienteDTO> readClient(@PathVariable(value = "id") final Integer id) {
		try {
			ClienteDTO clientDto = clienteService.getById(id);
			return ResponseEntity.ok(clientDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/clientes/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ClienteResult readClients(@PathVariable(value = "page_num") final Integer pageNum) {
		return clienteService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/cliente/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<ClienteDTO> updateClient(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final ClienteDTO clientDto) {
		return ResponseEntity.ok(clienteService.update(id, clientDto));
	}

	@DeleteMapping("/cliente/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<ClienteDTO> deleteClient(@PathVariable final Integer id) {
		try {
			return ResponseEntity.ok(clienteService.deleteById(id));
		} catch (Exception ex) {
			return ResponseEntity.noContent().build();
		}
	}
}

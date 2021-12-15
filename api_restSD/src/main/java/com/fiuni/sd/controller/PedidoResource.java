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

import com.fiuni.sd.dto.pedido.PedidoDTO;
import com.fiuni.sd.dto.pedido.PedidoResult;
import com.fiuni.sd.service.pedido.IPedidoService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api")
public class PedidoResource {

	@Autowired
	private IPedidoService pedidoService;

	@PostMapping(path = "/pedido")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDTO> createPedido(@RequestBody @Valid final PedidoDTO pedidoDTO) {
		try {
			return ResponseEntity.ok(pedidoService.save(pedidoDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/pedidos/search/cliente/{id_cliente}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public PedidoResult getAllPedidosByIdCliente(@PathVariable(value = "id_cliente") final Integer idCliente,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return pedidoService.getAllByCliente(idCliente, PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@GetMapping(path = "/pedidos/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public PedidoResult getAllPedidos(@PathVariable(value = "page_num") final Integer pageNum) {
		return pedidoService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@GetMapping(path = "/pedido/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDTO> getPedido(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(pedidoService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(path = "/pedido/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDTO> updatePedido(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final PedidoDTO dto) {
		return ResponseEntity.ok(pedidoService.update(id, dto));
	}

	@DeleteMapping(path = "/pedido/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDTO> deletePedido(@PathVariable(value = "id") final Integer id) {
		return ResponseEntity.ok(pedidoService.deleteById(id));
	}

}

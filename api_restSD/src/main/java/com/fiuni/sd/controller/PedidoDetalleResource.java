package com.fiuni.sd.controller;

import java.util.Date;

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

import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleDTO;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleResult;
import com.fiuni.sd.service.pedido_detalle.IPedidoDetalleService;
import com.fiuni.sd.utils.Setting;

@RestController
@RequestMapping("/api/pedido")
public class PedidoDetalleResource {

	@Autowired
	private IPedidoDetalleService pedidoDetalleService;

	@PostMapping(path = "/detalle")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDetalleDTO> create(@RequestBody @Valid final PedidoDetalleDTO dto) {
		try {
			return ResponseEntity.ok(pedidoDetalleService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = "/detalle/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDetalleDTO> getById(@PathVariable(value = "id") final Integer id) {
		try {
			return ResponseEntity.ok(pedidoDetalleService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/detalle/search/pedido/{id_pedido}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public PedidoDetalleResult getAllPedidosDetalleByIdPedido(@PathVariable(value = "id_pedido") final Integer idPedido,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return pedidoDetalleService.getAllByPedido(idPedido, PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@GetMapping(path = "/detalle/search/servicio/{id_servicio}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public PedidoDetalleResult getAllPedidosDetalleByIdServicio(
			@PathVariable(value = "id_servicio") final Integer idServicio,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return pedidoDetalleService.getAllByServicio(idServicio, PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	// VER !
	@GetMapping(path = "/detalle/search/fecha/{fecha}/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public PedidoDetalleResult getAllPedidosDetalleByFechaAdquisicion(
			@PathVariable(value = "fecha") final Date fecha,
			@PathVariable(value = "page_num") final Integer pageNum) {
		return pedidoDetalleService.getAllByFecha(fecha, PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@GetMapping(path = "/detalles/page/{page_num}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public PedidoDetalleResult getAll(@PathVariable(value = "page_num") final Integer pageNum) {
		return pedidoDetalleService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}

	@PutMapping(path = "/detalle/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDetalleDTO> update(@PathVariable(value = "id") final Integer id,
			@RequestBody @Valid final PedidoDetalleDTO dto) {
		return ResponseEntity.ok(pedidoDetalleService.update(id, dto));
	}

	@DeleteMapping(path = "/detalle/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<PedidoDetalleDTO> delete(@PathVariable(value = "id") final Integer id) {
		pedidoDetalleService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}

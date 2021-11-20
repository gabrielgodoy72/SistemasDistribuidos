package com.fiuni.sd.service.pedido;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IClienteDao;
import com.fiuni.sd.dao.IPedidoDao;
import com.fiuni.sd.domain.pedido.PedidoDomain;
import com.fiuni.sd.dto.pedido.PedidoDTO;
import com.fiuni.sd.dto.pedido.PedidoResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<PedidoDTO, PedidoDomain, PedidoResult>
		implements IPedidoService {

	@Autowired
	private IPedidoDao pedidoDao; // repository

	@Autowired
	private IClienteDao clienteDao; // repository

	@Override
	public PedidoDTO save(final PedidoDTO dto) {
		return convertDomainToDto(pedidoDao.save(convertDtoToDomain(dto)));
	}

	@Override
	public PedidoDTO getById(final Integer id) {
		return pedidoDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Pedido", "id", id));
	}

	@Override
	public PedidoResult getAll(final Pageable pageable) {
		final PedidoResult result = new PedidoResult();
		Page<PedidoDomain> pages = pedidoDao.findAll(pageable);
		result.setPedidos(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!pedidoDao.existsById(id)) {
			throw new ResourceNotFoundException("Pedido", "id", id);
		}
		pedidoDao.deleteById(id);
	}

	@Override
	public PedidoDTO update(final Integer id, final PedidoDTO dto) {
		if (!pedidoDao.existsById(id)) {
			throw new ResourceNotFoundException("Pedido", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Pedido", "id", id);
		}
		return convertDomainToDto(pedidoDao.save(convertDtoToDomain(dto)));
	}

	@Override
	public PedidoResult getAllByCliente(final Integer idCliente, final Pageable pageable) {
		final PedidoResult result = new PedidoResult();
		Page<PedidoDomain> pages = pedidoDao.findAllByCliente(idCliente, pageable);
		result.setPedidos(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	protected PedidoDTO convertDomainToDto(final PedidoDomain domain) {
		final PedidoDTO dto = new PedidoDTO();
		dto.setId(domain.getId());
		dto.setEstado(domain.getEstado());
		dto.setId_cliente(domain.getCliente().getId());
		return dto;
	}

	@Override
	protected PedidoDomain convertDtoToDomain(final PedidoDTO dto) {
		final PedidoDomain domain = new PedidoDomain();
		domain.setId(dto.getId());
		domain.setEstado(dto.getEstado());
		domain.setCliente(clienteDao.getById(dto.getId_cliente()));
		return domain;
	}

}

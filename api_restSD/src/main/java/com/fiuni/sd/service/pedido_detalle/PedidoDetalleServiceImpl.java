package com.fiuni.sd.service.pedido_detalle;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IPedidoDao;
import com.fiuni.sd.dao.IPedidoDetalleDao;
import com.fiuni.sd.dao.IServicioDao;
import com.fiuni.sd.domain.pedido_detalle.PedidoDetalleDomain;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleDTO;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class PedidoDetalleServiceImpl extends
		BaseServiceImpl<PedidoDetalleDTO, PedidoDetalleDomain, PedidoDetalleResult> implements IPedidoDetalleService {

	@Autowired
	private IPedidoDetalleDao repository; // repository

	@Autowired
	private IPedidoDao pedidoRepository; // repository

	@Autowired
	private IServicioDao servicioRepository; // repository

	@Override
	public PedidoDetalleDTO save(final PedidoDetalleDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	public PedidoDetalleDTO getById(final Integer id) {
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("PedidoDetalle", "id", id));
	}

	@Override
	public PedidoDetalleResult getAllByPedido(final Integer idPedido, final Pageable pageable) {
		final PedidoDetalleResult result = new PedidoDetalleResult();
		result.setPedidosDetalle(repository.findAllByPedido(idPedido, pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public PedidoDetalleResult getAllByServicio(final Integer idServicio, final Pageable pageable) {
		final PedidoDetalleResult result = new PedidoDetalleResult();
		result.setPedidosDetalle(repository.findAllByServicio(idServicio, pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public PedidoDetalleResult getAllByFecha(final Date fecha, final Pageable pageable) {
		final PedidoDetalleResult result = new PedidoDetalleResult();
		result.setPedidosDetalle(repository.findAllByFecha(fecha, pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public PedidoDetalleResult getAll(final Pageable pageable) {
		final PedidoDetalleResult result = new PedidoDetalleResult();
		result.setPedidosDetalle(repository.findAll(pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("PedidoDetalle", "id", id);
		}
		repository.deleteById(id);
	}

	@Override
	public PedidoDetalleDTO update(final Integer id, final PedidoDetalleDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("PedidoDetalle", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("PedidoDetalle", "id", id);
		}
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected PedidoDetalleDTO convertDomainToDto(final PedidoDetalleDomain domain) {
		final PedidoDetalleDTO dto = new PedidoDetalleDTO();
		dto.setId(domain.getId());
		dto.setFecha(domain.getFecha());
		dto.setPedido_id(domain.getPedido().getId());
		dto.setServicio_id(domain.getServicio().getId());
		return dto;
	}

	@Override
	protected PedidoDetalleDomain convertDtoToDomain(final PedidoDetalleDTO dto) {
		final PedidoDetalleDomain domain = new PedidoDetalleDomain();
		domain.setId(dto.getId());
		domain.setFecha(dto.getFecha());
		domain.setPedido(pedidoRepository.getById(dto.getPedido_id()));
		domain.setServicio(servicioRepository.getById(dto.getServicio_id()));
		return domain;
	}

}

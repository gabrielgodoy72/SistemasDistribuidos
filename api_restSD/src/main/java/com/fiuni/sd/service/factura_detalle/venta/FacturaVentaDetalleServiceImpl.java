package com.fiuni.sd.service.factura_detalle.venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IFacturaVentaDao;
import com.fiuni.sd.dao.IFacturaVentaDetalleDao;
import com.fiuni.sd.dao.IServicioDao;
import com.fiuni.sd.domain.factura_detalle.venta.FacturaVentaDetalleDomain;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class FacturaVentaDetalleServiceImpl
		extends BaseServiceImpl<FacturaVentaDetalleDTO, FacturaVentaDetalleDomain, FacturaVentaDetalleResult>
		implements IFacturaVentaDetalleService {

	@Autowired
	private IFacturaVentaDetalleDao repository; // repository

	@Autowired
	private IFacturaVentaDao facturaRepository; // repository

	@Autowired
	private IServicioDao servicioRepository; // repository

	@Override
	public FacturaVentaDetalleDTO save(final FacturaVentaDetalleDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	public FacturaVentaDetalleDTO getById(final Integer id) {
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaVentaDetalle", "id", id));
	}

	@Override
	public FacturaVentaDetalleResult getAllByFactura(final Integer idFactura, final Pageable pageable) {
		final FacturaVentaDetalleResult result = new FacturaVentaDetalleResult();
		result.setFacturasVentaDetalle(repository.findAllByFactura(idFactura, pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public FacturaVentaDetalleResult getAllByServicio(final Integer idServicio, final Pageable pageable) {
		final FacturaVentaDetalleResult result = new FacturaVentaDetalleResult();
		result.setFacturasVentaDetalle(repository.findAllByServicio(idServicio, pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public FacturaVentaDetalleResult getAll(final Pageable pageable) {
		final FacturaVentaDetalleResult result = new FacturaVentaDetalleResult();
		result.setFacturasVentaDetalle(repository.findAll(pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVentaDetalle", "id", id);
		}
		repository.deleteById(id);
	}

	public FacturaVentaDetalleDTO update(final Integer id, final FacturaVentaDetalleDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVentaDetalle", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaVentaDetalle", "id", id);
		}
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaVentaDetalleDTO convertDomainToDto(final FacturaVentaDetalleDomain domain) {
		final FacturaVentaDetalleDTO dto = new FacturaVentaDetalleDTO();
		dto.setId(domain.getId());
		dto.setCantidad(domain.getCantidad());
		dto.setSubtotal(domain.getSubtotal());
		dto.setFactura_id(domain.getFactura().getId());
		dto.setServicio_id(domain.getServicio().getId());
		return dto;
	}

	@Override
	protected FacturaVentaDetalleDomain convertDtoToDomain(final FacturaVentaDetalleDTO dto) {
		final FacturaVentaDetalleDomain domain = new FacturaVentaDetalleDomain();
		domain.setId(dto.getId());
		domain.setCantidad(dto.getCantidad());
		domain.setSubtotal(dto.getSubtotal());
		domain.setFactura(facturaRepository.getById(dto.getFactura_id()));
		domain.setServicio(servicioRepository.getById(dto.getServicio_id()));
		return domain;
	}

}

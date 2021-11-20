package com.fiuni.sd.service.factura_detalle.compra;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IFacturaCompraDao;
import com.fiuni.sd.dao.IFacturaCompraDetalleDao;
import com.fiuni.sd.dao.IProductoDao;
import com.fiuni.sd.domain.factura_detalle.compra.FacturaCompraDetalleDomain;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class FacturaCompraDetalleServiceImpl
		extends BaseServiceImpl<FacturaCompraDetalleDTO, FacturaCompraDetalleDomain, FacturaCompraDetalleResult>
		implements IFacturaCompraDetalleService {

	@Autowired
	private IFacturaCompraDetalleDao facturaCompraDetalleRepository; // repository

	@Autowired
	private IFacturaCompraDao facturaCompraRepository; // repository

	@Autowired
	private IProductoDao productoRepository; // repository

	@Override
	public FacturaCompraDetalleDTO save(final FacturaCompraDetalleDTO dto) {
		return convertDomainToDto(facturaCompraDetalleRepository.save(convertDtoToDomain(dto)));
	}

	@Override
	public FacturaCompraDetalleDTO getById(final Integer id) {
		return facturaCompraDetalleRepository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaCompraDetalle", "id", id));
	}

	@Override
	public FacturaCompraDetalleResult getAllByFactura(final Integer idFactura, final Pageable pageable) {
		final FacturaCompraDetalleResult result = new FacturaCompraDetalleResult();
		Page<FacturaCompraDetalleDomain> pages = facturaCompraDetalleRepository.findAllByFactura(idFactura, pageable);
		result.setFacturasCompraDetalle(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaCompraDetalleResult getAllByProducto(final Integer idProducto, final Pageable pageable) {
		final FacturaCompraDetalleResult result = new FacturaCompraDetalleResult();
		Page<FacturaCompraDetalleDomain> pages = facturaCompraDetalleRepository.findAllByProducto(idProducto, pageable);
		result.setFacturasCompraDetalle(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaCompraDetalleResult getAll(final Pageable pageable) {
		final FacturaCompraDetalleResult result = new FacturaCompraDetalleResult();
		Page<FacturaCompraDetalleDomain> pages = facturaCompraDetalleRepository.findAll(pageable);
		result.setFacturasCompraDetalle(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!facturaCompraDetalleRepository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaCompraDetalle", "id", id);
		}
		facturaCompraDetalleRepository.deleteById(id);
	}

	@Override
	public FacturaCompraDetalleDTO update(final Integer id, final FacturaCompraDetalleDTO dto) {
		if (!facturaCompraDetalleRepository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaCompraDetalle", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaCompraDetalle", "id", id);
		}
		return convertDomainToDto(facturaCompraDetalleRepository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaCompraDetalleDTO convertDomainToDto(final FacturaCompraDetalleDomain domain) {
		final FacturaCompraDetalleDTO dto = new FacturaCompraDetalleDTO();
		dto.setId(domain.getId());
		dto.setCantidad(domain.getCantidad());
		dto.setSubtotal(domain.getSubtotal());
		dto.setFactura_id(domain.getFactura().getId());
		dto.setProducto_id(domain.getProducto().getId());
		return dto;
	}

	@Override
	protected FacturaCompraDetalleDomain convertDtoToDomain(final FacturaCompraDetalleDTO dto) {
		final FacturaCompraDetalleDomain domain = new FacturaCompraDetalleDomain();
		domain.setId(dto.getId());
		domain.setCantidad(dto.getCantidad());
		domain.setSubtotal(dto.getSubtotal());
		domain.setFactura(facturaCompraRepository.getById(dto.getFactura_id()));
		domain.setProducto(productoRepository.getById(dto.getProducto_id()));
		return domain;
	}

}

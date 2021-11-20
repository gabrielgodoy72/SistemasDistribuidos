package com.fiuni.sd.service.factura.venta;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IClienteDao;
import com.fiuni.sd.dao.IFacturaVentaDao;
import com.fiuni.sd.domain.factura.venta.FacturaVentaDomain;
import com.fiuni.sd.dto.factura.venta.FacturaVentaDTO;
import com.fiuni.sd.dto.factura.venta.FacturaVentaResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class FacturaVentaServiceImpl extends BaseServiceImpl<FacturaVentaDTO, FacturaVentaDomain, FacturaVentaResult>
		implements IFacturaVentaService {

	@Autowired
	private IFacturaVentaDao repository; // repository

	@Autowired
	private IClienteDao clienteRepository; // repository

	@Override
	public FacturaVentaDTO save(FacturaVentaDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	public FacturaVentaDTO getById(Integer id) {
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaVenta", "id", id));
	}

	@Override
	public FacturaVentaResult getAllByFecha(Date fecha, Pageable pageable) {
		final FacturaVentaResult result = new FacturaVentaResult();
		Page<FacturaVentaDomain> pages = repository.findAllByFecha(fecha, pageable);
		result.setFacturasVenta(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaVentaDTO getByNumero(String numero) {
		return repository.findByNumero(numero)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaVenta", "numero", numero));
	}

	@Override
	public FacturaVentaResult getAllByCliente(Integer idCliente, Pageable pageable) {
		final FacturaVentaResult result = new FacturaVentaResult();
		Page<FacturaVentaDomain> pages = repository.findAllByCliente(idCliente, pageable);
		result.setFacturasVenta(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaVentaResult getAll(Pageable pageable) {
		final FacturaVentaResult result = new FacturaVentaResult();
		Page<FacturaVentaDomain> pages = repository.findAll(pageable);
		result.setFacturasVenta(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVenta", "id", id);
		}
		repository.deleteById(id);
	}

	@Override
	public FacturaVentaDTO update(final Integer id, final FacturaVentaDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaVenta", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaVenta", "id", id);
		}
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaVentaDTO convertDomainToDto(final FacturaVentaDomain domain) {
		final FacturaVentaDTO dto = new FacturaVentaDTO();
		dto.setId(domain.getId());
		dto.setNumero(domain.getNumero());
		dto.setFecha(domain.getFecha());
		dto.setTotal(domain.getTotal());
		dto.setCliente_id(domain.getCliente().getId());
		return dto;
	}

	@Override
	protected FacturaVentaDomain convertDtoToDomain(final FacturaVentaDTO dto) {
		final FacturaVentaDomain domain = new FacturaVentaDomain();
		domain.setId(dto.getId());
		domain.setNumero(dto.getNumero());
		domain.setFecha(dto.getFecha());
		domain.setTotal(dto.getTotal());
		domain.setCliente(clienteRepository.getById(dto.getCliente_id()));
		return domain;
	}

}

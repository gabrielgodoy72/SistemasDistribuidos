package com.fiuni.sd.service.factura.compra;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.dao.IFacturaCompraDao;
import com.fiuni.sd.dao.IProveedorDao;
import com.fiuni.sd.domain.factura.compra.FacturaCompraDomain;
import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class FacturaCompraServiceImpl extends
		BaseServiceImpl<FacturaCompraDTO, FacturaCompraDomain, FacturaCompraResult> implements IFacturaCompraService {

	@Autowired
	private IFacturaCompraDao repository; // repository

	@Autowired
	private IProveedorDao proveedorRepository; // repository

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FacturaCompraDTO save(FacturaCompraDTO dto) {
		proveedorRepository.getById(dto.getProveedor_id());
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	public FacturaCompraDTO getById(Integer id) {
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaCompra", "id", id));
	}

	@Override
	public FacturaCompraResult getAllByFecha(Date fecha, Pageable pageable) {
		final FacturaCompraResult result = new FacturaCompraResult();
		Page<FacturaCompraDomain> pages = repository.findAllByFecha(fecha, pageable);
		result.setFacturasCompra(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaCompraDTO getByNumero(String numero) {
		return repository.findByNumero(numero)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("FacturaCompra", "numero", numero));
	}

	@Override
	public FacturaCompraResult getAllByProveedor(Integer idProveedor, Pageable pageable) {
		final FacturaCompraResult result = new FacturaCompraResult();
		Page<FacturaCompraDomain> pages = repository.findAllByProveedor(proveedorRepository.getById(idProveedor), pageable);
		result.setFacturasCompra(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public FacturaCompraResult getAll(Pageable pageable) {
		final FacturaCompraResult result = new FacturaCompraResult();
		Page<FacturaCompraDomain> pages = repository.findAll(pageable);
		result.setFacturasCompra(pages.getContent()//
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
			throw new ResourceNotFoundException("FacturaCompra", "id", id);
		}
		repository.deleteById(id);
	}

	@Override
	public FacturaCompraDTO update(final Integer id, final FacturaCompraDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("FacturaCompra", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("FacturaCompra", "id", id);
		}
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected FacturaCompraDTO convertDomainToDto(final FacturaCompraDomain domain) {
		final FacturaCompraDTO dto = new FacturaCompraDTO();
		dto.setId(domain.getId());
		dto.setNumero(domain.getNumero());
		dto.setFecha(domain.getFecha());
		dto.setTotal(domain.getTotal());
		dto.setProveedor_id(domain.getProveedor().getId());
		return dto;
	}

	@Override
	protected FacturaCompraDomain convertDtoToDomain(final FacturaCompraDTO dto) {
		final FacturaCompraDomain domain = new FacturaCompraDomain();
		domain.setId(dto.getId());
		domain.setNumero(dto.getNumero());
		domain.setFecha(dto.getFecha());
		domain.setTotal(dto.getTotal());
		domain.setProveedor(proveedorRepository.getById(dto.getProveedor_id()));
		return domain;
	}

}

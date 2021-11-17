package com.fiuni.sd.service.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IProductoDao;
import com.fiuni.sd.domain.producto.ProductoDomain;
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<ProductoDTO, ProductoDomain, ProductoResult>
		implements IProductoService {

	@Autowired
	private IProductoDao productoDao; // repository

	@Override
	public ProductoDTO save(final ProductoDTO dto) {
		return convertDomainToDto(productoDao.save(convertDtoToDomain(dto)));
	}

	@Override
	public ProductoDTO getById(final Integer id) {
		return productoDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
	}

	@Override
	public ProductoResult getAll(final Pageable pageable) {
		final ProductoResult result = new ProductoResult();
		result.setProductos(productoDao.findAll(pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public void deleteById(Integer id) {
		if (!productoDao.existsById(id)) {
			throw new ResourceNotFoundException("Producto", "id", id);
		}
		productoDao.deleteById(id);
	}

	@Override
	public ProductoDTO update(final Integer id, final ProductoDTO dto) {
		if (!productoDao.existsById(id)) {
			throw new ResourceNotFoundException("Producto", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Producto", "id", id);
		}
		return convertDomainToDto(productoDao.save(convertDtoToDomain(dto)));
	}

	@Override
	public ProductoDTO getByDescripcion(String descripcion) {
		return productoDao.findByDescripcion(descripcion)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Producto", "descripcion", descripcion));
	}

	@Override
	protected ProductoDTO convertDomainToDto(ProductoDomain domain) {
		final ProductoDTO dto = new ProductoDTO();
		dto.setId(domain.getId());
		dto.setDescripcion(domain.getDescripcion());
		dto.setCosto(domain.getCosto());
		return dto;
	}

	@Override
	protected ProductoDomain convertDtoToDomain(ProductoDTO dto) {
		final ProductoDomain domain = new ProductoDomain();
		domain.setId(dto.getId());
		domain.setDescripcion(dto.getDescripcion());
		domain.setCosto(dto.getCosto());
		return domain;
	}

}
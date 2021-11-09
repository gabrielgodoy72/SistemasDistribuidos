package com.fiuni.sd.service.producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IProductoDao;
import com.fiuni.sd.domain.producto.ProductoDomain;
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<ProductoDTO, ProductoDomain, ProductoResult> implements IProductoService{

	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public ProductoDTO save(ProductoDTO dto) {
		final ProductoDomain productoDomain = convertDtoToDomain(dto);
		final ProductoDomain producto = productoDao.save(productoDomain);
		return convertDomainToDto(producto);
	}

	@Override
	public ProductoDTO getById(Integer id) {
		Optional<ProductoDomain> result = productoDao.findById(id);
        ProductoDTO producto = null;
        if(result.isPresent()){
        	producto = convertDomainToDto(result.get());
        } else {
            throw new RuntimeException("Did not find product id: " + id);
        }
        return producto;
	}

	@Override
	public ProductoResult getAll(Pageable pageable) {
		final List<ProductoDTO> productos = new ArrayList<>();
		Page<ProductoDomain> results = productoDao.findAll(pageable);
		for (ProductoDomain productoDomain : results) {
			productos.add(convertDomainToDto(productoDomain));
		}
		ProductoResult proveedorResult = new ProductoResult();
		proveedorResult.setProductos(productos);
		return proveedorResult;
	}

	@Override
	public void deleteById(int id) {
		productoDao.deleteById(id);
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
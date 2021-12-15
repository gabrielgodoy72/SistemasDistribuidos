package com.fiuni.sd.service.producto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IProductoDao;
import com.fiuni.sd.domain.producto.ProductoDomain;
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<ProductoDTO, ProductoDomain, ProductoResult>
		implements IProductoService {

	@Autowired
	private IProductoDao productoDao; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_producto_' + #result.getId()")
	public ProductoDTO save(final ProductoDTO dto) {
		return convertDomainToDto(productoDao.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_producto_' + #id")
	public ProductoDTO getById(final Integer id) {
		ProductoDTO productoCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_producto_" + id, ProductoDTO.class);
		if (productoCacheado != null) {
			return productoCacheado;
		}
		return productoDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	public ProductoResult getAll(final Pageable pageable) {
		final List<ProductoDTO> list = new ArrayList<>();
		final ProductoResult result = new ProductoResult();
		Page<ProductoDomain> pages = productoDao.findAll(pageable);
		pages.forEach(producto -> {
			ProductoDTO dto = convertDomainToDto(producto);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_producto_" + dto.getId(), dto);
		});
		result.setProductos(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_producto_' + #id")
	public ProductoDTO deleteById(Integer id) {
		if (!productoDao.existsById(id)) {
			throw new ResourceNotFoundException("Producto", "id", id);
		}
		ProductoDTO producto = convertDomainToDto(productoDao.getById(id));
		productoDao.deleteById(id);
		return producto;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_producto_' + #id")
	public ProductoDTO update(final Integer id, final ProductoDTO dto) {
		if (!productoDao.existsById(id)) {
			throw new ResourceNotFoundException("Producto", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Producto", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_producto_" + id);
		return convertDomainToDto(productoDao.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_producto_' + #result.getDescripcion()")
	public ProductoDTO getByDescripcion(String descripcion) {
		ProductoDTO productoCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_producto_" + descripcion, ProductoDTO.class);
		if (productoCacheado != null) {
			return productoCacheado;
		}
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
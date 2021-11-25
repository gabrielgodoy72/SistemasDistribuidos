package com.sd.service.producto;

import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.sd.beans.producto.ProductoB;
import com.sd.beans.producto.ProductoBResult;
import com.sd.rest.producto.IProductoResource;
import com.sd.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("productoService")
public class ProductoServiceImpl extends BaseServiceImpl<ProductoB, ProductoDTO> implements IProductoService {

    @Autowired
    private IProductoResource productoResource;

    @Override
    public ProductoB save(ProductoB bean) {
        return convertDtoToBean(productoResource.save(convertBeanToDto(bean)));
    }

    @Override
    public ProductoBResult getAll(Integer page) {
        final ProductoBResult productosB = new ProductoBResult();
        final ProductoResult result = productoResource.getAll(page);
        if(result.getProductos() == null) {
            productosB.setProductos(Collections.emptyList());
        } else {
            productosB.setProductos(result.getProductos().stream().map(this::convertDtoToBean).collect(Collectors.toList()));
            productosB.setPage(result.getPage());
            productosB.setTotalPages(result.getTotalPages());
            productosB.setTotal(result.getTotal());
        }
        return productosB;
    }

    @Override
    public ProductoB getById(Integer id) {
        return convertDtoToBean(productoResource.getById(id));
    }

    @Override
    public void delete(Integer id) {
        productoResource.delete(id);
    }

    @Override
    public ProductoB update(Integer id, ProductoB bean) {
        return convertDtoToBean(productoResource.update(id, convertBeanToDto(bean)));
    }

    @Override
    protected ProductoB convertDtoToBean(ProductoDTO dto) {
        final Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("descripcion", String.valueOf(dto.getDescripcion()));
        params.put("costo", String.valueOf(dto.getCosto()));
        return new ProductoB(params);
    }

    @Override
    protected ProductoDTO convertBeanToDto(ProductoB bean) {
        final ProductoDTO dto = new ProductoDTO();
        dto.setId(bean.getId());
        dto.setDescripcion(bean.getDescripcion());
        dto.setCosto(bean.getCosto());
        return dto;
    }
}

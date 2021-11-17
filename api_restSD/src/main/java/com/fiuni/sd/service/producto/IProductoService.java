package com.fiuni.sd.service.producto;

import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IProductoService extends IBaseService<ProductoDTO, ProductoResult> {

	public ProductoDTO update(final Integer id, final ProductoDTO dto);

	public ProductoDTO getByDescripcion(final String descripcion);
}
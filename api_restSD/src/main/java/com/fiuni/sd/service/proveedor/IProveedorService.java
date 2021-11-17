package com.fiuni.sd.service.proveedor;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IProveedorService extends IBaseService<ProveedorDTO, ProveedorResult> {

	public ProveedorDTO update(final Integer id, final ProveedorDTO dto);

	public ProveedorDTO getByNombre(final String nombre);
	
	public ProveedorDTO getByRuc(final String ruc);

}
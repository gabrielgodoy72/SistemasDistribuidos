package com.sd.service.proveedor;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.sd.beans.proveedor.ProveedorB;
import com.sd.beans.proveedor.ProveedorBResult;
import com.sd.rest.proveedor.IProveedorResource;
import com.sd.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("proveedorService")
public class ProveedorServiceImpl extends BaseServiceImpl<ProveedorB, ProveedorDTO> implements IProveedorService {

    @Autowired
    private IProveedorResource proveedorResource;

    @Override
    public ProveedorB save(ProveedorB bean) {
        bean.setId(0);
        return convertDtoToBean(proveedorResource.save(convertBeanToDto(bean)));
    }

    @Override
    public ProveedorBResult getAll(Integer page) {
        final ProveedorBResult proveedorBResult = new ProveedorBResult();
        final ProveedorResult result = proveedorResource.getAll(page);
        if(result.getProveedores() == null) {
            proveedorBResult.setProveedores(Collections.emptyList());
        } else {
            List<ProveedorB> list = new ArrayList<>();
            result.getProveedores().forEach(proveedor -> {
                ProveedorB bean = convertDtoToBean(proveedor);
                list.add(bean);
            });
            proveedorBResult.setProveedores(list);
            proveedorBResult.setPage(result.getPage());
            proveedorBResult.setTotalPages(result.getTotalPages());
            proveedorBResult.setTotal(result.getTotal());
            proveedorBResult.setHasPrev(result.get_hasPrev());
            proveedorBResult.setHasNext(result.get_hasNext());
            proveedorBResult.setPrevPage(result.getPrevPage());
            proveedorBResult.setNextPage(result.getNextPage());
        }
        return proveedorBResult;
    }

    @Override
    public ProveedorB getById(Integer id) {
        return convertDtoToBean(proveedorResource.getById(id));
    }

    @Override
    public ProveedorB delete(Integer id) {
        return convertDtoToBean(proveedorResource.delete(id));
    }

    @Override
    public ProveedorB update(Integer id, ProveedorB bean) {
        return convertDtoToBean(proveedorResource.update(id, convertBeanToDto(bean)));
    }

    @Override
    protected ProveedorB convertDtoToBean(ProveedorDTO dto) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("nombre", String.valueOf(dto.getNombre()));
        params.put("ruc", String.valueOf(dto.getRuc()));
        params.put("telefono", String.valueOf(dto.getTelefono()));
        params.put("direccion", String.valueOf(dto.getDireccion()));
        return new ProveedorB(params);
    }

    @Override
    protected ProveedorDTO convertBeanToDto(ProveedorB bean) {
        final ProveedorDTO dto = new ProveedorDTO();
        dto.setId(bean.getId());
        dto.setNombre(bean.getNombre());
        dto.setRuc(bean.getRuc());
        dto.setTelefono(bean.getTelefono());
        dto.setDireccion(bean.getDireccion());
        return dto;
    }
}

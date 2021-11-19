package com.sd.service.facturaCompra;

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.sd.beans.facturaCompra.FacturaCompraB;
import com.sd.rest.facturaCompra.IFacturaCompraResource;
import com.sd.rest.proveedor.IProveedorResource;
import com.sd.service.base.BaseServiceImpl;
import com.sd.service.proveedor.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("facturaCompraService")
public class FacturaCompraServiceImpl extends BaseServiceImpl<FacturaCompraB, FacturaCompraDTO> implements IFacturaCompraService {

    @Autowired
    private IFacturaCompraResource facturaCompraResource;
    @Autowired
    private IProveedorService proveedorService;

    @Override
    public FacturaCompraB save(FacturaCompraB bean) {
        bean.setId(0);
        return convertDtoToBean(facturaCompraResource.save(convertBeanToDto(bean), "/compra"));
    }

    @Override
    public List<FacturaCompraB> getAll(Integer page) {
        final FacturaCompraResult result = facturaCompraResource.getAll(page);
        if(result.getFacturaCompra() == null){
            return Collections.emptyList();
        }
        return result.getFacturaCompra().stream().map(this::convertDtoToBean).collect(Collectors.toList());
    }

    @Override
    public FacturaCompraB getById(Integer id) {
        return convertDtoToBean(facturaCompraResource.getById(id, "/compra"));
    }

    @Override
    public void delete(Integer id) {
        facturaCompraResource.delete(id, "/compra");
    }

    @Override
    public FacturaCompraB update(Integer id, FacturaCompraB bean) {
        return convertDtoToBean(facturaCompraResource.update(id, convertBeanToDto(bean), "/compra"));
    }

    @Override
    protected FacturaCompraB convertDtoToBean(FacturaCompraDTO dto) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("numero", String.valueOf(dto.getNumero()));
        FacturaCompraB factura = new FacturaCompraB(params);
        factura.setProveedor(proveedorService.getById(dto.getProveedor_id()));
        factura.setFecha(dto.getFecha());
        return factura;
    }

    @Override
    protected FacturaCompraDTO convertBeanToDto(FacturaCompraB bean) {
        final FacturaCompraDTO dto = new FacturaCompraDTO();
        dto.setId(bean.getId());
        dto.setFecha(bean.getFecha());
        dto.setNumero(bean.getNumero());
        dto.setTotal(bean.getTotal());
        dto.setProveedor_id(bean.getProveedor().getId());
        return dto;
    }
}

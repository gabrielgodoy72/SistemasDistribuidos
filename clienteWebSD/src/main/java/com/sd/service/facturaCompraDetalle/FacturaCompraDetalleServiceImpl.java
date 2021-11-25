package com.sd.service.facturaCompraDetalle;

import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.sd.beans.facturaCompra.FacturaCompraBResult;
import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleB;
import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleBResult;
import com.sd.rest.facturaCompraDetalle.IFacturaCompraDetalleResource;
import com.sd.service.base.BaseServiceImpl;
import com.sd.service.facturaCompra.IFacturaCompraService;
import com.sd.service.producto.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("facturaCompraDetalleService")
public class FacturaCompraDetalleServiceImpl extends BaseServiceImpl<FacturaCompraDetalleB, FacturaCompraDetalleDTO>
        implements IFacturaCompraDetalleService {

    @Autowired
    private IFacturaCompraDetalleResource facturaCompraDetalleResource;
    @Autowired
    private IFacturaCompraService facturaCompraService;
    @Autowired
    private IProductoService productoService;

    @Override
    public FacturaCompraDetalleB save(FacturaCompraDetalleB bean) {
        bean.setId(0);
        return convertDtoToBean(facturaCompraDetalleResource.save(convertBeanToDto(bean)));
    }

    @Override
    public FacturaCompraDetalleBResult getAll(Integer page) {
        final FacturaCompraDetalleBResult FacturaCompraDetalleB = new FacturaCompraDetalleBResult();
        final FacturaCompraDetalleResult result = facturaCompraDetalleResource.getAll(page);
        if(result.getFacturasCompraDetalle() == null) {
            FacturaCompraDetalleB.setFacturasCompraDetalle(Collections.emptyList());
        } else {
            FacturaCompraDetalleB.setFacturasCompraDetalle(result.getFacturasCompraDetalle().stream().map(this::convertDtoToBean).collect(Collectors.toList()));
            FacturaCompraDetalleB.setPage(result.getPage());
            FacturaCompraDetalleB.setTotalPages(result.getTotalPages());
            FacturaCompraDetalleB.setTotal(result.getTotal());
        }
        return FacturaCompraDetalleB;
    }

    @Override
    public FacturaCompraDetalleB getById(Integer id) {
        return convertDtoToBean(facturaCompraDetalleResource.getById(id));
    }

    @Override
    public void delete(Integer id) {
        facturaCompraDetalleResource.delete(id);
    }

    @Override
    public FacturaCompraDetalleB update(Integer id, FacturaCompraDetalleB bean) {
        return convertDtoToBean(facturaCompraDetalleResource.update(id, convertBeanToDto(bean)));
    }

    @Override
    protected FacturaCompraDetalleB convertDtoToBean(FacturaCompraDetalleDTO dto) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("cantidad", String.valueOf(dto.getCantidad()));
        params.put("subtotal", String.valueOf(dto.getSubtotal()));
        FacturaCompraDetalleB facturaDetalle = new FacturaCompraDetalleB(params);
        facturaDetalle.setFactura(facturaCompraService.getById(dto.getFactura_id()));
        facturaDetalle.setProducto(productoService.getById(dto.getProducto_id()));
        return facturaDetalle;
    }

    @Override
    protected FacturaCompraDetalleDTO convertBeanToDto(FacturaCompraDetalleB bean) {
        final FacturaCompraDetalleDTO dto = new FacturaCompraDetalleDTO();
        dto.setId(bean.getId());
        dto.setCantidad(bean.getCantidad());
        dto.setSubtotal(bean.getSubtotal());
        dto.setFactura_id(bean.getFactura().getId());
        dto.setProducto_id(bean.getProducto().getId());
        return dto;
    }
}
package com.sd.service.facturaCompraDetalle;

import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleB;
import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleBResult;
import com.sd.rest.facturaCompraDetalle.IFacturaCompraDetalleResource;
import com.sd.service.base.BaseServiceImpl;
import com.sd.service.facturaCompra.IFacturaCompraService;
import com.sd.service.producto.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        final FacturaCompraDetalleBResult facturaCompraDetalleBResult = new FacturaCompraDetalleBResult();
        final FacturaCompraDetalleResult result = facturaCompraDetalleResource.getAll(page);
        if(result.getFacturasCompraDetalle() == null) {
            facturaCompraDetalleBResult.setFacturasCompraDetalle(Collections.emptyList());
        } else {
            List<FacturaCompraDetalleB> list = new ArrayList<>();
            result.getFacturasCompraDetalle().forEach(detalle -> {
                FacturaCompraDetalleB bean = convertDtoToBean(detalle);
                list.add(bean);
            });
            facturaCompraDetalleBResult.setFacturasCompraDetalle(list);
            facturaCompraDetalleBResult.setPage(result.getPage());
            facturaCompraDetalleBResult.setTotalPages(result.getTotalPages());
            facturaCompraDetalleBResult.setTotal(result.getTotal());
        }
        return facturaCompraDetalleBResult;
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
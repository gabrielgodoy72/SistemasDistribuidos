package com.sd.rest.facturaCompra;

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.sd.rest.base.BaseResourceImpl;
import org.springframework.stereotype.Repository;

@Repository("facturaCompraResource")
public class FacturaCompraResourceImpl extends BaseResourceImpl<FacturaCompraDTO> implements IFacturaCompraResource {

    public FacturaCompraResourceImpl() {
        super(FacturaCompraDTO.class, "/api/factura");
    }

    @Override
    public FacturaCompraResult getAll(Integer page) {
        final FacturaCompraResult result = getWebResource().path("/compra/page/" + page).get(FacturaCompraResult.class);
        return result;
    }

    @Override
    public FacturaCompraDTO save(FacturaCompraDTO dto) {
        return getWebResource().path("/compra").entity(dto).post(getDtoClass());
    }

    @Override
    public FacturaCompraDTO getById(Integer id) {
        return getWebResource().path("/compra/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        getWebResource().path("/compra/" + id).delete();
    }

    @Override
    public FacturaCompraDTO update(Integer id, FacturaCompraDTO dto) {
        return getWebResource().path("/compra/" + id).entity(dto).put(getDtoClass());
    }
}
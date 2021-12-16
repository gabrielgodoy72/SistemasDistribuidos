package com.sd

import com.sd.beans.facturaCompra.FacturaCompraB
import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleBResult
import com.sd.service.facturaCompra.IFacturaCompraService
import com.sd.service.facturaCompraDetalle.IFacturaCompraDetalleService
import com.sd.service.proveedor.IProveedorService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FacturaCompraController {

    IFacturaCompraService facturaCompraService
    IFacturaCompraDetalleService facturaCompraDetalleService
    IProveedorService proveedorService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer id) {
        def page = Math.max(id?:0,0)
        def facturasCompra = facturaCompraService.getAll(page)
        //def facturas = null == facturasCompra? new ArrayList<FacturaCompraB>() : facturasCompra.getList()
        [facturas: facturasCompra.getList(), facturasIntanceTotal: facturasCompra.getTotal(),
         totalPages: facturasCompra.getTotalPages(), currentPage: facturasCompra.getPage(),
         hasNexPage: facturasCompra.getHasNext(), hasPrevPage: facturasCompra.getHasPrev(),
         nextPage: facturasCompra.getNextPage(), prevPage: facturasCompra.getPrevPage()]
    }

    def show(Integer id) {
        def factura
        try {
            factura = facturaCompraService.getById(id.toInteger())
        } catch (Exception ex) { redirect(action: "notFound") }
        FacturaCompraDetalleBResult facturasCompraDetalle = facturaCompraDetalleService.getAllByFactura(id, 0);
        [factura: factura, detalles: facturasCompraDetalle.getList()]
    }

    def edit(Integer id) {
        def factura
        try {
            factura = facturaCompraService.getById(id.toInteger())
        } catch (Exception ex) { redirect(action: "notFound") }
        def proveedores = proveedorService.getAll(0)
        def facturasCompraDetalle = facturaCompraDetalleService.getAllByFactura(id, 0);
        [factura: factura, detalles: facturasCompraDetalle.getList(), proveedores: proveedores.getList()]
    }

    def update() {
        System.out.println("\n\n" + params.toString());
        def facturaCompra = new FacturaCompraB(params)
        FacturaCompraB factura = facturaCompraService.getById(facturaCompra.getId())
        facturaCompra.setProveedor(proveedorService.getById(Integer.valueOf(params.proveedorId)))
        facturaCompra.setFecha(factura.getFecha())
        facturaCompra.setTotal(factura.getTotal())
        facturaCompraService.update(facturaCompra.getId(), facturaCompra)
        redirect(action: "list")
    }

    def create() {
        def factura = new FacturaCompraB(params)
        def proveedores = proveedorService.getAll(0)
        [factura: factura, proveedores: proveedores.getList()]
    }

    def save() {
        def facturaCompra = new FacturaCompraB(params)
        facturaCompra.setProveedor(proveedorService.getById(Integer.valueOf(params.proveedorId)))
        facturaCompra.setTotal(0.0)
        facturaCompra.setFecha(new Date())
        def factura = facturaCompraService.save(facturaCompra)
        redirect(action: "show", controller: "facturaCompra", params: [id: factura.getId()])
    }

    def delete() {
        def id = Integer.valueOf(params['id'])
        FacturaCompraDetalleBResult facturasCompraDetalle = facturaCompraDetalleService.getAllByFactura(id, 0);
        for(int i = 0; i < facturasCompraDetalle.getList().size(); i++) {
            facturaCompraDetalleService.delete(facturasCompraDetalle.getList().get(i).getId())
        }
        facturaCompraService.delete(id)
        redirect(action: "list")
    }

    protected void notFound() {
        request.withFormat {
            '*'{ render status: NOT_FOUND }
        }
    }

}

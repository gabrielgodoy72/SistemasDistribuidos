package com.sd

import grails.gorm.services.Service

@Service(FacturaCompraDetalle)
interface FacturaCompraDetalleService {

    FacturaCompraDetalle get(Serializable id)

    List<FacturaCompraDetalle> list(Map args)

    Long count()

    void delete(Serializable id)

    FacturaCompraDetalle save(FacturaCompraDetalle facturaCompraDetalle)

}
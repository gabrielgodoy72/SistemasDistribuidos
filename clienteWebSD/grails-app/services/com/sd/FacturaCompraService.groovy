package com.sd

import grails.gorm.services.Service

@Service(FacturaCompra)
interface FacturaCompraService {

    FacturaCompra get(Serializable id)

    List<FacturaCompra> list(Map args)

    Long count()

    void delete(Serializable id)

    FacturaCompra save(FacturaCompra facturaCompra)

}
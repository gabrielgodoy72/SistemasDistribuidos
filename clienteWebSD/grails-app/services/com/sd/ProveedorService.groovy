package com.sd

import grails.gorm.services.Service

@Service(Proveedor)
interface ProveedorService {

    Proveedor get(Serializable id)

    List<Proveedor> list(Map args)

    Long count()

    void delete(Serializable id)

    Proveedor save(Proveedor proveedor)

}
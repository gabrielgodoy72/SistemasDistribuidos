package com.sd

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProveedorServiceSpec extends Specification {

    ProveedorService proveedorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Proveedor(...).save(flush: true, failOnError: true)
        //new Proveedor(...).save(flush: true, failOnError: true)
        //Proveedor proveedor = new Proveedor(...).save(flush: true, failOnError: true)
        //new Proveedor(...).save(flush: true, failOnError: true)
        //new Proveedor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //proveedor.id
    }

    void "test get"() {
        setupData()

        expect:
        proveedorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Proveedor> proveedorList = proveedorService.list(max: 2, offset: 2)

        then:
        proveedorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        proveedorService.count() == 5
    }

    void "test delete"() {
        Long proveedorId = setupData()

        expect:
        proveedorService.count() == 5

        when:
        proveedorService.delete(proveedorId)
        sessionFactory.currentSession.flush()

        then:
        proveedorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Proveedor proveedor = new Proveedor()
        proveedorService.save(proveedor)

        then:
        proveedor.id != null
    }
}

package com.sd

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FacturaCompraDetalleServiceSpec extends Specification {

    FacturaCompraDetalleService facturaCompraDetalleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FacturaCompraDetalle(...).save(flush: true, failOnError: true)
        //new FacturaCompraDetalle(...).save(flush: true, failOnError: true)
        //FacturaCompraDetalle facturaCompraDetalle = new FacturaCompraDetalle(...).save(flush: true, failOnError: true)
        //new FacturaCompraDetalle(...).save(flush: true, failOnError: true)
        //new FacturaCompraDetalle(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //facturaCompraDetalle.id
    }

    void "test get"() {
        setupData()

        expect:
        facturaCompraDetalleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FacturaCompraDetalle> facturaCompraDetalleList = facturaCompraDetalleService.list(max: 2, offset: 2)

        then:
        facturaCompraDetalleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        facturaCompraDetalleService.count() == 5
    }

    void "test delete"() {
        Long facturaCompraDetalleId = setupData()

        expect:
        facturaCompraDetalleService.count() == 5

        when:
        facturaCompraDetalleService.delete(facturaCompraDetalleId)
        sessionFactory.currentSession.flush()

        then:
        facturaCompraDetalleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FacturaCompraDetalle facturaCompraDetalle = new FacturaCompraDetalle()
        facturaCompraDetalleService.save(facturaCompraDetalle)

        then:
        facturaCompraDetalle.id != null
    }
}

package com.sd

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FacturaCompraServiceSpec extends Specification {

    FacturaCompraService facturaCompraService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FacturaCompra(...).save(flush: true, failOnError: true)
        //new FacturaCompra(...).save(flush: true, failOnError: true)
        //FacturaCompra facturaCompra = new FacturaCompra(...).save(flush: true, failOnError: true)
        //new FacturaCompra(...).save(flush: true, failOnError: true)
        //new FacturaCompra(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //facturaCompra.id
    }

    void "test get"() {
        setupData()

        expect:
        facturaCompraService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FacturaCompra> facturaCompraList = facturaCompraService.list(max: 2, offset: 2)

        then:
        facturaCompraList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        facturaCompraService.count() == 5
    }

    void "test delete"() {
        Long facturaCompraId = setupData()

        expect:
        facturaCompraService.count() == 5

        when:
        facturaCompraService.delete(facturaCompraId)
        sessionFactory.currentSession.flush()

        then:
        facturaCompraService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FacturaCompra facturaCompra = new FacturaCompra()
        facturaCompraService.save(facturaCompra)

        then:
        facturaCompra.id != null
    }
}

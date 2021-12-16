<%--
  Created by IntelliJ IDEA.
  User: godoy
  Date: 13/12/2021
  Time: 11:40
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Agregar un Detalle</title>
    </head>
    <body>
        <h1>Agregar Items a la Factura</h1>
        <g:form action="save" >
            <div class="row align-items-start">
                <div class="col">
                    <label for="factura">Número de Factura</label>
                    <g:textField class="form-control"
                                 type="text"
                                 name="factura"
                                 value="${detalle?.factura.numero}"
                                 disabled="true"/>
                </div>
                <div class="col">
                    <label for="proveedor">Nombre del Proveedor</label>
                    <g:textField class="form-control"
                                 type="text"
                                 name="proveedor"
                                 value="${detalle?.factura.proveedor.nombre}"
                                 disabled="true"/>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <label for="producto">Producto</label>
                    <g:select class="form-control"
                              id="producto"
                              name="productoId"
                              from="${productos}"
                              optionKey="id"
                              optionValue="descripcion"
                              value="${detalle?.producto?.id}" />
                </div>
                <div class="col">
                    <label for="cantidad">Cantidad</label>
                    <g:textField class="form-control"
                                 type="text"
                                 name="cantidad"
                                 value="${detalle?.cantidad}"
                                 autoComplete="off"/>
                </div>
                <div class="col pt-4">
                    <g:hiddenField name="facturaId" value="${detalle?.factura.id}"/>
                    <g:actionSubmit class="btn btn-primary" action="save" value="Agregar"/>
                </div>
            </div>
        </g:form>
    </body>
</html>
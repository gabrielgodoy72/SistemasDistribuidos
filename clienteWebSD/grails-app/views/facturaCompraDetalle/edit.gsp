<%--
  Created by IntelliJ IDEA.
  User: godoy
  Date: 13/12/2021
  Time: 17:51
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Editar un Detalle</title>
</head>
<body>
<h1>Edita Items a la Factura</h1>
<g:form method="put" >
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
                         value="${detalle?.cantidad}"/>
        </div>
        <div class="col pt-4">
            <g:hiddenField name="id" value="${detalle?.id}"/>
            <g:actionSubmit class="btn btn-primary" action="update" value="Modificar"/>
        </div>
    </div>
</g:form>
</body>
</html>
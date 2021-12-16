
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Factura Compra</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
<div class="d-flex justify-content-between my-3 ">
    <g:link class="btn btn-outline-dark" action="list"><i class="fas fa-arrow-left"></i> Volver a la lista</g:link>
</div>
<g:form action="save">
    <div class="row align-items-start">
        <div class="col">
            <label for="numero" style="font-weight: bold">Número de Factura</label>
            <g:textField class="form-control" type="text" name="numero" value="${factura?.numero}" autoComplete="off"/>
        </div>
        <div class="col">
            <label for="proveedor" style="font-weight: bold">Nombre del Proveedor</label>
            <g:select class="form-control" id="proveedor" name="proveedorId" from="${proveedores}" optionKey="id" optionValue="nombre" value="${factura?.proveedor?.id}" />
        </div>
    </div>
    <div class="d-flex justify-content-end my-3">
        <g:actionSubmit class="btn btn-primary mx-5" action="save" value="Siguiente"/>
    </div>
</g:form>
</body>
</html>
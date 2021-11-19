
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <g:set var="entityName" value="${message(code: 'facturaCompra.label', default: 'Factura Compra')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-facturaCompra" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>

<div id="list-facturaCompra" class="" role="main">
    <h1>Lista de Facturas de Compra</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table class="table table-light">
        <thead class="table-primary" >
        <tr class="">
            <th scope="col">Factura N°</th>
            <th scope="col">Fecha</th>
            <th scope="col">Total</th>
            <th scope="col">Nombre del Proveedor</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <tbody class="">
        <g:each in="${facturaCompraInstanceList}" status="i" var="facturaCompraInstance">
            <tr>
                <td><h6>${fieldValue(bean: facturaCompraInstance, field: "numero")}</h6></td>
                <td><h6>${fieldValue(bean: facturaCompraInstance, field: "fecha")}</h6></td>
                <td><h6>${fieldValue(bean: facturaCompraInstance, field: "total")}</h6></td>
                <td><h6>${fieldValue(bean: facturaCompraInstance, field: "proveedor.nombre")}</h6></td>
                <td><h6><g:link class="edit" action="show" id="${facturaCompraInstance?.id}">
                    <button class="btn btn-outline-info btn-sm">Ver</button>
                </g:link></h6></td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

</div>
</body>
</html>

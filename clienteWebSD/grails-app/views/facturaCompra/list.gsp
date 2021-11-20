
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <g:set var="entityName" value="${message(code: 'facturaCompra.label', default: 'Factura Compra')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="list-facturaCompra" class="pt-3" role="main">
    <div class="d-flex justify-content-between">
        <h1>Lista de Facturas de Compra</h1>
        <div class="pt-3">
            <g:link controller="facturaCompra" class="btn btn-primary" action="create">Nueva Factura</g:link>
        </div>
    </div>
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
                <td>
                    <div class="d-flex">
                        <g:link controller="facturaCompra" class="btn btn-outline-info" action="show" id="${facturaCompraInstance?.id}">Ver</g:link>
                        <g:link controller="facturaCompra" class="btn btn-outline-primary mx-2" action="edit" id="${facturaCompraInstance?.id}">Editar</g:link>
                        <g:form method="delete" >
                            <g:hiddenField name="id" value="${facturaCompraInstance?.id}" />
                            <fieldset class="">
                                <g:actionSubmit class="btn btn-outline-danger" action="delete" value="Borrar" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                            </fieldset>
                        </g:form>

                    </div>
                </td>
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

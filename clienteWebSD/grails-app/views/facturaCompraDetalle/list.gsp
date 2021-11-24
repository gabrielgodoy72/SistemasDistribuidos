
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <g:set var="entityName" value="${message(code: 'facturaCompraDetalle.label', default: 'Factura Compra')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="list-facturaCompraDetalle" class="pt-3" role="main">
    <div class="d-flex justify-content-between">
        <h1>Lista de Detalles de Facturas de Compra</h1>
        <div class="pt-3">
            <g:link controller="facturaCompraDetalle" class="btn btn-primary" action="create">Nueva Factura</g:link>
        </div>
    </div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table class="table table-light">
        <thead class="table-primary" >
        <tr class="">
            <th scope="col">Producto</th>
            <th scope="col">Cantidad</th>
            <th scope="col">Número de Factura</th>
            <th scope="col">Subtotal</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <tbody class="">
        <g:each in="${facturaCompraDetalleInstanceList}" status="i" var="facturaCompraDetalleInstance">
            <tr>
                <td><h6>${fieldValue(bean: facturaCompraDetalleInstance, field: "producto.descripcion")}</h6></td>
                <td><h6>${fieldValue(bean: facturaCompraDetalleInstance, field: "cantidad")}</h6></td>
                <td><h6>${fieldValue(bean: facturaCompraDetalleInstance, field: "factura.numero")}</h6></td>
                <td><h6>${fieldValue(bean: facturaCompraDetalleInstance, field: "subtotal")}</h6></td>
                <td>
                    <div class="d-flex">
                        <g:link controller="facturaCompraDetalle" class="btn btn-outline-info"
                                action="show" id="${facturaCompraDetalleInstance?.id}">Ver</g:link>
                        <g:link controller="facturaCompraDetalle" class="btn btn-outline-primary mx-2"
                                action="edit" id="${facturaCompraDetalleInstance?.id}">Editar</g:link>
                        <g:form method="delete" >
                            <g:hiddenField name="id" value="${facturaCompraDetalleInstance?.id}" />
                            <fieldset class="">
                                <g:actionSubmit class="btn btn-outline-danger" action="delete" value="Borrar" formnovalidate=""
                                                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
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

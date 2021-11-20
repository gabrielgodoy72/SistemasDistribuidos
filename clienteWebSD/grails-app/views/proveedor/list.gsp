
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <g:set var="entityName" value="${message(code: 'proveedor.label', default: 'Proveedor')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="list-proveedor" class="pt-3" role="main">
    <div class="d-flex justify-content-between">
        <h1>Lista de Proveedores</h1>
        <div class="pt-3">
            <g:link controller="proveedor" class="btn btn-primary" action="create">Nuevo Proveedor</g:link>
        </div>
    </div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table class="table table-light">
        <thead class="table-primary" >
        <tr class="">
            <th scope="col">Nombre</th>
            <th scope="col">R.U.C.</th>
            <th scope="col">Teléfono</th>
            <th scope="col">Dirección</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <tbody class="">
        <g:each in="${proveedorInstanceList}" status="i" var="proveedorInstance">
            <tr>
                <td><h6>${fieldValue(bean: proveedorInstance, field: "nombre")}</h6></td>
                <td><h6>${fieldValue(bean: proveedorInstance, field: "ruc")}</h6></td>
                <td><h6>${fieldValue(bean: proveedorInstance, field: "telefono")}</h6></td>
                <td><h6>${fieldValue(bean: proveedorInstance, field: "direccion")}</h6></td>
                <td>
                    <div class="d-flex">
                        <g:link controller="proveedor" class="btn btn-outline-info" action="show" id="${proveedorInstance?.id}">Ver</g:link>
                        <g:link controller="proveedor" class="btn btn-outline-primary mx-2" action="edit" id="${proveedorInstance?.id}">Editar</g:link>
                        <g:form method="delete" >
                            <g:hiddenField name="id" value="${proveedorInstance?.id}" />
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

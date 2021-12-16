
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta name="layout" content="main"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <g:set var="entityName" value="${message(code: 'servicio.label', default: 'Servicio')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="list-servicio" class="pt-3" role="main">
    <div class="d-flex justify-content-between">
        <h1>Lista de Servicios</h1>
        <div class="pt-3">
            <g:link controller="servicio" class="btn btn-primary" action="create">Nuevo Servicio</g:link>
        </div>
    </div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table class="table table-light">
        <thead class="table-primary" >
        <tr class="">
            <th scope="col">Descripcion</th>
            <th scope="col">Costo</th>
            <th scope="col">Accion</th>
        </tr>
        </thead>
        <tbody class="">

        <g:each in="${servicioInstanceList}" status="i" var="servicioInstance">
            <tr>
                <td><h6>${fieldValue(bean: servicioInstance, field: "descripcion")}</h6></td>
                <td><h6>${fieldValue(bean: servicioInstance, field: "costo")}</h6></td>
                <td>
                    <div class="d-flex">
                        <g:link controller="servicio" class="btn btn-outline-info" action="show" id="${servicioInstance?.id}">Ver</g:link>
                        <g:link controller="servicio" class="btn btn-outline-primary mx-2" action="edit" id="${servicioInstance?.id}">Editar</g:link>
                        <g:form method="delete" >
                            <g:hiddenField name="id" value="${servicioInstance?.id}" />
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
    <!-- Pagination section -->
    <div class="pagination">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item <g:if test="${!hasPrevPage}">disabled</g:if>">
                    <g:link controller="servicio" class="page-link" action="list" id="${prevPage}">&laquo;</g:link>
                </li>
                <g:each var="i" in="${ (0..< totalPages) }">
                    <li class="page-item <g:if test="${i==currentPage}">active</g:if>"">
                        <g:link controller="servicio" class="page-link" action="list" id="${i}">${i}</g:link>
                    </li>
                </g:each>
                <li class="page-item <g:if test="${!hasNexPage}">disabled</g:if>">
                    <g:link controller="servicio" class="page-link" action="list" id="${nextPage}">&raquo;</g:link>
                </li>
            </ul>
        </nav>
    </div>
</div>
<script>

</script>
</body>
</html>

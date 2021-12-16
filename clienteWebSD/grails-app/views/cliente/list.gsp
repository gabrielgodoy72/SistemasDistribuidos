
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <g:set var="entityName" value="${message(code: 'cliente.label', default: 'Cliente')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="list-cliente" class="pt-3" role="main">
    <div class="d-flex justify-content-between">
        <h1>Lista de Clientes</h1>
        <div class="pt-3">
            <g:link controller="cliente" class="btn btn-primary" action="create">Nuevo Cliente</g:link>
        </div>
    </div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table class="table table-light">
        <thead class="table-primary" >
        <tr class="">
            <th scope="col" style="font-size: medium; font-weight: bold">C.I. N°</th>
            <th scope="col" style="font-size: medium; font-weight: bold">Nombre</th>
            <th scope="col" style="font-size: medium; font-weight: bold">Dirección</th>
            <th scope="col" style="font-size: medium; font-weight: bold">Email</th>
            <th scope="col" style="font-size: medium; font-weight: bold">Telefono</th>
            <th scope="col" style="font-size: medium; font-weight: bold">Acción</th>
        </tr>
        </thead>
        <tbody class="">
        <g:each in="${clienteInstanceList}" status="i" var="clienteInstance">
            <tr>
                <td><h6>${fieldValue(bean: clienteInstance, field: "ci")}</h6></td>
                <td><h6>${fieldValue(bean: clienteInstance, field: "nombre")}</h6></td>
                <td><h6>${fieldValue(bean: clienteInstance, field: "direccion")}</h6></td>
                <td><h6>${fieldValue(bean: clienteInstance, field: "email")}</h6></td>
                <td><h6>${fieldValue(bean: clienteInstance, field: "telefono")}</h6></td>
                <td>
                    <div class="d-flex">
                        <g:link controller="cliente" class="btn btn-outline-info" action="show" id="${clienteInstance?.id}">Ver</g:link>
                        <g:link controller="cliente" class="btn btn-outline-primary mx-2" action="edit" id="${clienteInstance?.id}">Editar</g:link>
                        <g:form method="delete" >
                            <g:hiddenField name="id" value="${clienteInstance?.id}" />
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

</div>
</body>
</html>

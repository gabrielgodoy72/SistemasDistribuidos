<%--
  Created by IntelliJ IDEA.
  User: godoy
  Date: 12/12/2021
  Time: 17:10
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Facturas de Compra</title>
    </head>

    <body>
        <div class="pt-3">
            <div class="d-flex justify-content-between">
                <h1>Lista de Facturas de Compra</h1>
                <div class="pt-3">
                    <g:link controller="facturaCompra" class="btn btn-primary" action="create">Nueva Factura</g:link>
                </div>
            </div>

            <table class="table table-light">
                <thead class="table-primary" >
                <tr class="">
                    <th scope="col" style="font-size: medium; font-weight: bold">Factura N°</th>
                    <th scope="col" style="font-size: medium; font-weight: bold">Fecha</th>
                    <th scope="col" style="font-size: medium; font-weight: bold">Total</th>
                    <th scope="col" style="font-size: medium; font-weight: bold">Nombre del Proveedor</th>
                    <th scope="col" style="font-size: medium; font-weight: bold">Acción</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${facturas}" status="i" var="factura">
                    <tr>
                        <td><h6>${fieldValue(bean: factura, field: "numero")}</h6></td>
                        <td><h6>${fieldValue(bean: factura, field: "fecha")}</h6></td>
                        <td><h6>${fieldValue(bean: factura, field: "total")}</h6></td>
                        <td><h6>${fieldValue(bean: factura, field: "proveedor.nombre")}</h6></td>
                        <td>
                            <div class="d-flex">
                                <g:link controller="facturaCompra" class="btn btn-outline-info" action="show" id="${factura?.id}">Ver</g:link>
                                <g:link controller="facturaCompra" class="btn btn-outline-primary mx-2" action="edit" id="${factura?.id}">Editar</g:link>
                                <g:form controller="facturaCompra" method="delete">
                                    <g:hiddenField name="id" value="${factura?.id}"/>
                                    <fieldset>
                                        <g:actionSubmit class="btn btn-outline-danger"
                                                        action="delete"
                                                        value="Borrar"
                                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Estas seguro?')}');" />
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
    </body>
</html>

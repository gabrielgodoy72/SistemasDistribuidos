<%--
  Created by IntelliJ IDEA.
  User: godoy
  Date: 12/12/2021
  Time: 18:01
--%>

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
        <div class="d-flex justify-content-between">
            <div class="my-3 d-flex">
                <g:link class="btn btn-outline-dark" action="list"><i class="fas fa-arrow-left"></i> Volver a la lista</g:link>
                <g:form>
                    <g:link class="btn btn-outline-primary mx-2"
                            controller="facturaCompraDetalle"
                            action="create"
                            id="${factura?.id}">
                        <i class="fas fa-plus"></i> Agregar Detalle
                    </g:link>
                </g:form>
                <g:link class="btn btn-outline-warning" action="edit" id="${factura?.id}"><i class="fas fa-pen"></i> Editar</g:link>
            </div>
            <div class="d-flex align-items-end">
                <strong>Fecha:&nbsp;</strong>
                <g:formatDate format="dd-MM-yyyy" date="${factura?.fecha}"/>
            </div>
        </div>
        <div class="row align-items-start">
            <div class="col">
                <label for="numero" style="font-weight: bold">Número de Factura</label>
                <g:textField class="form-control" type="text" name="numero" value="${factura?.numero}" disabled="true"/>
            </div>
            <div class="col">
                <label for="proveedor" style="font-weight: bold">Nombre del Proveedor</label>
                <g:textField class="form-control" type="text" name="proveedor" value="${factura?.proveedor.nombre}" disabled="true"/>
            </div>
            <div class="col">
                <label for="total" style="font-weight: bold">Total</label>
                <g:textField class="form-control" type="text" name="total" value="${factura?.total}" disabled="true"/>
            </div>
        </div>

    <div id="list-facturaCompraDetalle" class="pt-3" role="main">
        <h4>Detalles de la Factura</h4>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <table class="table table-light">
            <thead class="table-primary" >
            <tr>
                <th scope="col" style="font-size: medium; font-weight: bold">Producto</th>
                <th scope="col" style="font-size: medium; font-weight: bold">Cantidad</th>
                <th scope="col" style="font-size: medium; font-weight: bold">Precio</th>
                <th scope="col" style="font-size: medium; font-weight: bold">Subtotal</th>
            </tr>
            </thead>
            <tbody class="">
            <g:each in="${detalles}" status="i" var="detalle">
                <tr>
                    <td><h6>${fieldValue(bean: detalle, field: "producto.descripcion")}</h6></td>
                    <td><h6>${fieldValue(bean: detalle, field: "cantidad")}</h6></td>
                    <td><h6>${fieldValue(bean: detalle, field: "producto.costo")}</h6></td>
                    <td><h6>${fieldValue(bean: detalle, field: "subtotal")}</h6></td>
                </tr>
            </g:each>
            </tbody>
        </table>

    </div>

    </body>
</html>
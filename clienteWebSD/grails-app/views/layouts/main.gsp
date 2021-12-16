<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>
<nav class="navbar navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand text-primary" href="/">
            <span class="text-dark font-weight-bold">HOME</span>
        </a>

        <div class="collapse navbar-collapse mx-5" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="controller">
                    <div class="dropdown">
                        <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButtonFacturacion"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Facturación
                        </button>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <g:link class="dropdown-item" controller="facturaCompra">Facturas Compra</g:link>
                            <g:link class="dropdown-item" controller="facturaCompra">Facturas Venta</g:link>
                        </div>
                    </div>
                </li>
                <li class="controller">
                    <button class="btn btn-light text-dark">
                        <g:link style="color: black" controller="proveedor">Pedidos</g:link>
                    </button>
                </li>
                <li class="controller">
                    <button class="btn btn-light text-dark">
                        <g:link style="color: black" controller="proveedor">Proveedores</g:link>
                    </button>
                </li>
                <li class="controller">
                    <button class="btn btn-light text-dark">
                        <g:link style="color: black" controller="cliente">Clientes</g:link>
                    </button>
                </li>
                <li class="controller">
                    <button class="btn btn-light text-dark">
                        <g:link style="color: black" controller="producto">Productos</g:link>
                    </button>
                </li>
                <li class="controller">
                    <button class="btn btn-light">
                        <g:link style="color: black" controller="servicio">Servicios</g:link>
                    </button>
                </li>
                <li class="controller">
                    <div class="dropdown">
                        <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButtonConfiguracion"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Configuración
                        </button>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <g:link class="dropdown-item" controller="usuario">Usuarios</g:link>
                            <g:link class="dropdown-item" controller="usuario">Roles</g:link>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="contenedor">
    <g:layoutBody/>
</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>

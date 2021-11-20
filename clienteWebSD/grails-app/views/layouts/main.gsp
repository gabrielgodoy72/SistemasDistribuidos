<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>
<nav class="navbar navbar-expand-lg bg-primary" >
    <div class="container-fluid">
        <a class="navbar-brand text-primary" href="/">
            <span class="text-dark font-weight-bold">HOME</span>
        </a>
        <div class="collapse navbar-collapse mx-5" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <!--<li class="nav-item">
                    <a class="nav-link" href="#">Registrarse</a>
                </li>-->
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                    <li class="controller">
                        <g:link controller="${c.logicalPropertyName}" class="dropdown-item text-capitalize">
                            ${c.logicalPropertyName}
                        </g:link>
                    </li>
                </g:each>
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

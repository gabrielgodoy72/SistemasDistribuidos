
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="edit-usuario" class="content scaffold-edit" role="main">
    <h1>Editar Usuario</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${usuarioInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${usuarioInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form method="put" >
        <g:hiddenField name="id" value="${usuarioInstance?.id}" />
        <fieldset class="form">
            <g:render template="formShow"/>
        </fieldset>
        <fieldset class="buttons">
            <g:actionSubmit class="save" action="update" value="Guardar" />
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>
</body>
</html>

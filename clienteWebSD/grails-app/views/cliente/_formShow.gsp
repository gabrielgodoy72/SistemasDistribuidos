<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'ci', 'error')} required">
    <label for="ci">
        <g:message code="cliente.ci.label" default="C.I. N°" />
    </label>
    <g:textField name="ci" maxlength="50" required="" value="${clienteInstance?.ci}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'nombre', 'error')} required">
    <label for="nombre">
        <g:message code="cliente.nombre.label" default="Nombre" />
    </label>
    <g:textField name="nombre" maxlength="50" required="" value="${clienteInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion"', 'error')} required">
    <label for="direccion">
        <g:message code="cliente.direccion.label" default="Dirección" />
    </label>
    <g:textField name="direccion" maxlength="50" required="" value="${clienteInstance?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="cliente.email.label" default="Email" />
    </label>
    <g:textField name="email" maxlength="50" required="" value="${clienteInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'telefono', 'error')} required">
    <label for="telefono">
        <g:message code="cliente.telefono.label" default="Teléfono" />
    </label>
    <g:textField name="telefono" maxlength="50" required="" value="${clienteInstance?.telefono}"/>
</div>

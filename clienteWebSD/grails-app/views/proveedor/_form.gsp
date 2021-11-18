
<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="proveedor.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="50" required="" value="${proveedorInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'ruc', 'error')} required">
	<label for="ruc">
		<g:message code="proveedor.ruc.label" default="R.U.C." />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ruc" maxlength="50" required="" value="${proveedorInstance?.ruc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="proveedor.telefono.label" default="Teléfono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" maxlength="50" required="" value="${proveedorInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion"', 'error')} required">
	<label for="direccion">
		<g:message code="proveedor.direccion.label" default="Dirección" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField type="direccion" name="direccion" maxlength="50" required="" value="${proveedorInstance?.direccion}"/>
</div>


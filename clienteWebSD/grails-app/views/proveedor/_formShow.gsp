
<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="proveedor.nombre.label" default="Nombre" />
	</label>
	<g:textField name="nombre" value="${proveedorInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'ruc', 'error')} required">
	<label for="ruc">
		<g:message code="proveedor.ruc.label" default="R.U.C." />
	</label>
	<g:textField name="ruc" value="${proveedorInstance?.ruc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="proveedor.telefono.label" default="Teléfono" />
	</label>
	<g:textField name="telefono" value="${proveedorInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="proveedor.direccion.label" default="Teléfono" />
	</label>
	<g:textField name="direccion" value="${proveedorInstance?.direccion}"/>
</div>


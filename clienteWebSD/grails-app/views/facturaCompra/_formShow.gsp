
<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="facturaCompra.numero.label" default="Número de Factura" />
	</label>
	<g:textField name="numero" maxlength="50" required="" value="${facturaCompraInstance?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="facturaCompra.fecha.label" default="Fecha" />
	</label>
	<g:textField name="fecha" maxlength="50" required="" value="${facturaCompraInstance?.ruc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="facturaCompra.telefono.label" default="Total" />
	</label>
	<g:textField name="total" maxlength="50" required="" value="${facturaCompraInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'proveedor.id"', 'error')} required">
	<label for="proveedor.id">
		<g:message code="facturaCompra.proveedor.id.label" default="Id del Proveedor" />
	</label>
	<g:textField type="proveedor.id" name="proveedor.id" maxlength="50" required="" value="${facturaCompraInstance?.direccion}"/>
</div>

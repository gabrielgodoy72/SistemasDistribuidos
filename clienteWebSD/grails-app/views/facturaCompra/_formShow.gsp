
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
	<g:datePicker  id="fecha" name="fecha" precision="day"
				   value="${facturaCompraInstance?.fecha}"
				   years="[2020, 2021, 2022, 2023]" ></g:datePicker >
</div>

<div class="fieldcontain d-flex ${hasErrors(bean: facturaCompraInstance, field: 'total', 'error')} required">
	<label for="total">
		<g:message code="facturaCompra.telefono.label" default="Total" />
	</label>
		<g:textField disabled="true" name="total" value="${facturaCompraInstance?.total}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'proveedor.id"', 'error')} required">
	<label for="proveedor">
		<g:message code="facturaCompra.proveedor.id.label" default="Proveedor" />
	</label>
	<g:select id="proveedor" name="proveedorId" from="${proveedores}" optionKey="id" optionValue="nombre" required="" value="${facturaCompraInstance?.proveedor?.id}" class="many-to-one"/>
</div>

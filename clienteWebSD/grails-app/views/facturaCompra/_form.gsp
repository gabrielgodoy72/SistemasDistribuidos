<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="facturaCompra.numero.label" default="Número de Factura" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" maxlength="50" required="" value="${facturaCompraInstance?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="facturaCompra.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker  id="fecha" name="fecha" precision="day"
				   value="${facturaCompraInstance?.fecha}"
				   years="[2020, 2021, 2022, 2023]" ></g:datePicker >
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraInstance, field: 'proveedor', 'error')} required">
	<label for="proveedor">
		<g:message code="state.city.label" default="Proveedor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="proveedor" name="proveedorId" from="${proveedores}" optionKey="id" optionValue="nombre" required="" value="${facturaCompraInstance?.proveedor?.id}" class="many-to-one"/>
</div>
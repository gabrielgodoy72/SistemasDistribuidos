
<div class="fieldcontain ${hasErrors(bean: facturaCompraDetalleInstance, field: 'producto', 'error')} required">
	<label for="producto">
		<g:message code="producto.descripcion.label" default="Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="producto" name="productoId" from="${productos}" optionKey="id" optionValue="descripcion" required="" value="${facturaCompraDetalleInstance?.producto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraDetalleInstance, field: 'factura', 'error')} required">
	<label for="factura">
		<g:message code="facturaCompra.numero.label" default="Factura Número" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="factura" name="facturaCompraId" from="${facturasCompra}" optionKey="id" optionValue="numero" required="" value="${facturaCompraDetalleInstance?.factura?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraDetalleInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="facturaCompraDetalle.numero.label" default="Cantidad de productos" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cantidad" maxlength="50" required="" value="${facturaCompraDetalleInstance?.cantidad}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'subtotal', 'error')} required">
	<label for="subtotal">
		<g:message code="facturaCompraDetalle.subtotal.label" default="Subtotal" />
	</label>
	<g:textField name="subtotal" disabled="true" value="${facturaCompraDetalleInstance?.subtotal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraDetalleInstance, field: 'producto', 'error')} required">
	<label for="producto">
		<g:message code="producto.descripcion.label" default="Producto" />
	</label>
	<g:select id="producto" name="productoId" from="${productos}" optionKey="id" optionValue="descripcion" required="" value="${facturaCompraDetalleInstance?.producto?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraDetalleInstance, field: 'factura', 'error')} required">
	<label for="factura">
		<g:message code="facturaCompra.numero.label" default="Factura Número" />
	</label>
	<g:select id="factura" name="facturaCompraId" from="${facturasCompra}" optionKey="id" optionValue="numero" required="" value="${facturaCompraDetalleInstance?.factura?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facturaCompraDetalleInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="facturaCompraDetalle.cantidad.label" default="Cantidad de productos" />
	</label>
	<g:textField name="cantidad" maxlength="50" required="" value="${facturaCompraDetalleInstance?.cantidad}"/>
</div>



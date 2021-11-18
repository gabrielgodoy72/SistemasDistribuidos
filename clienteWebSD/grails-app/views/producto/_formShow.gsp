
<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
	</label>
	<g:textField name="descripcion" value="${productoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'costo', 'error')} required">
	<label for="costo">
		<g:message code="producto.costo.label" default="costo" />
	</label>
	<g:textField name="costo" value="${productoInstance?.costo}"/>
</div>

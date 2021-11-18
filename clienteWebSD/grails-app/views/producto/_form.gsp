
<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="50" required="" value="${productoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'costo', 'error')} required">
	<label for="costo">
		<g:message code="producto.costo.label" default="costo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="costo" required="" value="${productoInstance?.costo}"/>
</div>

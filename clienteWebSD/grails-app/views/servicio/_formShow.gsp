
<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="servicio.descripcion.label" default="Descripcion" />
	</label>
	<g:textField name="descripcion" value="${servicioInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'costo', 'error')} required">
	<label for="costo">
		<g:message code="servicio.costo.label" default="costo" />
	</label>
	<g:textField name="costo" value="${servicioInstance?.costo}"/>
</div>
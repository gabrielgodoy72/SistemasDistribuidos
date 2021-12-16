
<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="servicio.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="50" required="" value="${servicioInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: servicioInstance, field: 'costo', 'error')} required">
	<label for="costo">
		<g:message code="servicio.costo.label" default="costo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="costo" required="" value="${servicioInstance?.costo}"/>
</div>

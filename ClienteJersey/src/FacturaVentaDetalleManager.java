
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleResult;
import com.sun.jersey.api.client.ClientResponse;

public class FacturaVentaDetalleManager extends AbstractBaseManager<FacturaVentaDetalleDTO> {

	public FacturaVentaDetalleManager() {
		super();
	}

	
	public FacturaVentaDetalleDTO create(int facturaId, int servicioId, int cantidad) {
		FacturaVentaDetalleDTO req = new FacturaVentaDetalleDTO();
		req.setId(0);
		req.setFactura_id(facturaId);
		req.setCantidad(cantidad);
		req.setServicio_id(servicioId);
		req.setSubtotal(0.0);
		
		ClientResponse response = save("factura/venta/detalle", req);
		
		if(response.getStatus() == 200) {
			FacturaVentaDetalleDTO res = response.getEntity(FacturaVentaDetalleDTO.class);
			return res;
		}
		return null;
	}
	
	public FacturaVentaDetalleDTO read(int id) {
		ClientResponse response = getById("factura/venta/detalle", id);
		
		if(response.getStatus() == 200) {
			FacturaVentaDetalleDTO dto = response.getEntity(FacturaVentaDetalleDTO.class);
			return dto;
		}
		return null;
	}
	
	public FacturaVentaDetalleDTO update(int id, int facturaId, int servicioId, int cantidad) {
		FacturaVentaDetalleDTO req = new FacturaVentaDetalleDTO();
		req.setId(id);
		req.setFactura_id(facturaId);
		req.setCantidad(cantidad);
		req.setServicio_id(servicioId);
		req.setSubtotal(0.0);
		
		ClientResponse response = update("factura/venta/detalle", req, id);
		
		if(response.getStatus() == 200) {
			FacturaVentaDetalleDTO res = response.getEntity(FacturaVentaDetalleDTO.class);
			return res;
		}
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("factura/venta/detalle", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public FacturaVentaDetalleResult readAll(int page) {
		ClientResponse response = getAll("factura/venta/detalle", page);
		
		if(response.getStatus() == 200) {
			FacturaVentaDetalleResult result = response.getEntity(FacturaVentaDetalleResult.class);
			return result;
		}
		return null;
	}
	
}

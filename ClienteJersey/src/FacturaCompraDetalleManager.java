
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.sun.jersey.api.client.ClientResponse;

public class FacturaCompraDetalleManager extends AbstractBaseManager<FacturaCompraDetalleDTO> {

	public FacturaCompraDetalleManager() {
		super();
	}

	
	public FacturaCompraDetalleDTO create(int facturaId, int productoId, int cantidad) {
		FacturaCompraDetalleDTO req = new FacturaCompraDetalleDTO();
		req.setId(0);
		req.setFactura_id(facturaId);
		req.setCantidad(cantidad);
		req.setProducto_id(productoId);
		req.setSubtotal(0.0);
		
		ClientResponse response = save("factura/compra/detalle", req);
		
		if(response.getStatus() == 200) {
			FacturaCompraDetalleDTO res = response.getEntity(FacturaCompraDetalleDTO.class);
			return res;
		}
		return null;
	}
	
	public FacturaCompraDetalleDTO read(int id) {
		ClientResponse response = getById("factura/compra/detalle", id);
		
		if(response.getStatus() == 200) {
			FacturaCompraDetalleDTO dto = response.getEntity(FacturaCompraDetalleDTO.class);
			return dto;
		}
		return null;
	}
	
	public FacturaCompraDetalleDTO update(int id, int facturaId, int productoId, int cantidad) {
		FacturaCompraDetalleDTO req = new FacturaCompraDetalleDTO();
		req.setId(id);
		req.setFactura_id(facturaId);
		req.setCantidad(cantidad);
		req.setProducto_id(productoId);
		req.setSubtotal(0.0);
		
		ClientResponse response = update("factura/compra/detalle", req, id);
		
		if(response.getStatus() == 200) {
			FacturaCompraDetalleDTO res = response.getEntity(FacturaCompraDetalleDTO.class);
			return res;
		}
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("factura/compra/detalle", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public FacturaCompraDetalleResult readAll(int page) {
		ClientResponse response = getAll("factura/compra/detalle", page);
		
		if(response.getStatus() == 200) {
			FacturaCompraDetalleResult result = response.getEntity(FacturaCompraDetalleResult.class);
			return result;
		}
		return null;
	}
	
}

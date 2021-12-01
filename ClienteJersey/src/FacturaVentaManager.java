
import java.util.Date;
import com.fiuni.sd.dto.factura.venta.FacturaVentaDTO;
import com.fiuni.sd.dto.factura.venta.FacturaVentaResult;
import com.sun.jersey.api.client.ClientResponse;

public class FacturaVentaManager extends AbstractBaseManager<FacturaVentaDTO> {

	public FacturaVentaManager() {
		super();
	}

	
	public FacturaVentaDTO create(String numero, int clienteId, Date fecha) {
		FacturaVentaDTO req = new FacturaVentaDTO();
		req.setId(0);
		req.setNumero(numero);
		req.setCliente_id(clienteId);
		req.setFecha(fecha);
		req.setTotal(0.0);
		
		ClientResponse response = save("factura/venta", req);
		
		if(response.getStatus() == 200) {
			FacturaVentaDTO res = response.getEntity(FacturaVentaDTO.class);
			return res;
		}
		return null;
	}
	
	public FacturaVentaDTO read(int id) {
		ClientResponse response = getById("factura/venta", id);

		if(response.getStatus() == 200) {
			FacturaVentaDTO dto = response.getEntity(FacturaVentaDTO.class);
			return dto;
		}
		return null;
	}
	
	public FacturaVentaDTO update(int id, String numero, int clienteId, Date fecha) {
		FacturaVentaDTO req = new FacturaVentaDTO();
		req.setId(id);
		req.setNumero(numero);
		req.setCliente_id(clienteId);
		req.setFecha(fecha);
		req.setTotal(0.0);
		
		ClientResponse response = update("factura/venta", req, id);
		
		if(response.getStatus() == 200) {
			FacturaVentaDTO res = response.getEntity(FacturaVentaDTO.class);
			return res;
		}
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("factura/venta", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public FacturaVentaResult readAll(int page) {
		ClientResponse response = getAll("factura/venta", page);
		
		if(response.getStatus() == 200) {
			FacturaVentaResult result = response.getEntity(FacturaVentaResult.class);
			return result;
		}
		return null;
	}
	
}


import java.util.Date;
import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.sun.jersey.api.client.ClientResponse;

public class FacturaCompraManager extends AbstractBaseManager<FacturaCompraDTO> {

	public FacturaCompraManager() {
		super();
	}

	
	public FacturaCompraDTO create(String numero, int proveedorId, Date fecha) {
		FacturaCompraDTO req = new FacturaCompraDTO();
		req.setId(0);
		req.setNumero(numero);
		req.setProveedor_id(proveedorId);
		req.setFecha(fecha);
		req.setTotal(0.0);
		
		ClientResponse response = save("factura/compra", req);
		
		if(response.getStatus() == 200) {
			FacturaCompraDTO res = response.getEntity(FacturaCompraDTO.class);
			return res;
		}
		return null;
	}
	
	public FacturaCompraDTO read(int id) {
		ClientResponse response = getById("factura/compra", id);
		
		if(response.getStatus() == 200) {
			FacturaCompraDTO dto = response.getEntity(FacturaCompraDTO.class);
			return dto;
		}
		return null;
	}
	
	public FacturaCompraDTO update(int id, String numero, int proveedorId, Date fecha) {
		FacturaCompraDTO req = new FacturaCompraDTO();
		req.setId(id);
		req.setNumero(numero);
		req.setProveedor_id(proveedorId);
		req.setFecha(fecha);
		req.setTotal(0.0);
		
		ClientResponse response = update("factura/compra", req, id);
		
		if(response.getStatus() == 200) {
			FacturaCompraDTO res = response.getEntity(FacturaCompraDTO.class);
			return res;
		}
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("factura/compra", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public FacturaCompraResult readAll(int page) {
		ClientResponse response = getAll("factura/compra", page);
		
		if(response.getStatus() == 200) {
			FacturaCompraResult result = response.getEntity(FacturaCompraResult.class);
			return result;
		}
		return null;
	}
	
}

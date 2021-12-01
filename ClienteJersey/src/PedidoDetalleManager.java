
import java.util.Date;

import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleDTO;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleResult;
import com.sun.jersey.api.client.ClientResponse;

public class PedidoDetalleManager extends AbstractBaseManager<PedidoDetalleDTO> {

	public PedidoDetalleManager() {
		super();
	}

	
	public PedidoDetalleDTO create(int pedidoId, int servicioId, Date fechaAdquisicion) {
		PedidoDetalleDTO req = new PedidoDetalleDTO();
		req.setId(0);
		req.setFecha(fechaAdquisicion);
		req.setPedido_id(pedidoId);
		req.setServicio_id(servicioId);
		
		ClientResponse response = save("pedido/detalle", req);
		
		if(response.getStatus() == 200) {
			PedidoDetalleDTO res = response.getEntity(PedidoDetalleDTO.class);
			return res;
		}
		return null;
	}
	
	public PedidoDetalleDTO read(int id) {
		ClientResponse response = getById("pedido/detalle", id);
		
		if(response.getStatus() == 200) {
			PedidoDetalleDTO dto = response.getEntity(PedidoDetalleDTO.class);
			return dto;
		}
		return null;
	}
	
	public PedidoDetalleDTO update(int id, int pedidoId, int servicioId, Date fechaAdquisicion) {
		PedidoDetalleDTO req = new PedidoDetalleDTO();
		req.setId(id);
		req.setFecha(fechaAdquisicion);
		req.setPedido_id(pedidoId);
		req.setServicio_id(servicioId);
		
		ClientResponse response = update("pedido/detalle", req, id);
		
		if(response.getStatus() == 200) {
			PedidoDetalleDTO res = response.getEntity(PedidoDetalleDTO.class);
			return res;
		}
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("pedido/detalle", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public PedidoDetalleResult readAll(int page) {
		ClientResponse response = getAll("pedido/detalles", page);
		
		if(response.getStatus() == 200) {
			PedidoDetalleResult result = response.getEntity(PedidoDetalleResult.class);
			return result;
		}
		return null;
	}
	
}

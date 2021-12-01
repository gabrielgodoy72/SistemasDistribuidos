
import com.fiuni.sd.dto.pedido.PedidoDTO;
import com.fiuni.sd.dto.pedido.PedidoResult;
import com.sun.jersey.api.client.ClientResponse;

public class PedidoManager extends AbstractBaseManager<PedidoDTO> {

	public PedidoManager() {
		super();
	}

	
	public PedidoDTO create(int clienteId, boolean estado) {
		PedidoDTO req = new PedidoDTO();
		req.setId_cliente(clienteId);
		req.setEstado(estado);
		
		ClientResponse response = save("pedido", req);
		
		if(response.getStatus() == 200) {
			PedidoDTO res = response.getEntity(PedidoDTO.class);
			return res;
		}
		return null;
	}
	
	public PedidoDTO read(int id) {
		ClientResponse response = getById("pedido", id);
		
		if(response.getStatus() == 200) {
			PedidoDTO dto = response.getEntity(PedidoDTO.class);
			return dto;
		}
		return null;
	}
	
	public PedidoDTO update(int id, int clienteId, boolean estado) {
		PedidoDTO req = new PedidoDTO();
		req.setId(id);
		req.setId_cliente(clienteId);
		req.setEstado(estado);
		
		ClientResponse response = update("pedido", req, id);
		
		if(response.getStatus() == 200) {
			PedidoDTO res = response.getEntity(PedidoDTO.class);
			return res;
		}
		
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("pedido", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public PedidoResult readAll(int page) {
		ClientResponse response = getAll("pedidos", page);
		
		if(response.getStatus() == 200) {
			PedidoResult result = response.getEntity(PedidoResult.class);
			return result;
		}
		return null;
	}
	
}
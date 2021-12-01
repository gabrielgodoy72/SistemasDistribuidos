
import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.sun.jersey.api.client.ClientResponse;

public class ClienteManager extends AbstractBaseManager<ClienteDTO> {

	public ClienteManager() {
		super();
	}

	
	public ClienteDTO create(String ci, String nombre, String direccion, String email, String telefono) {
		ClienteDTO req = new ClienteDTO();
		req.setId(0);
		req.setCi(ci);
		req.setNombre(nombre);
		req.setDireccion(direccion);
		req.setEmail(email);
		req.setTelefono(telefono);
		
		ClientResponse response = save("cliente", req);
		
		if(response.getStatus() == 200) {
			ClienteDTO res = response.getEntity(ClienteDTO.class);
			return res;
		}
		return null;
	}
	
	public ClienteDTO read(int id) {
		ClientResponse response = getById("cliente", id);
		
		if(response.getStatus() == 200) {
			ClienteDTO dto = response.getEntity(ClienteDTO.class);
			return dto;
		}
		return null;
	}
	
	public ClienteDTO update(int id, String ci, String nombre, String direccion, String email, String telefono) {
		ClienteDTO req = new ClienteDTO();
		req.setId(id);
		req.setCi(ci);
		req.setNombre(nombre);
		req.setDireccion(direccion);
		req.setEmail(email);
		req.setTelefono(telefono);
		
		ClientResponse response = update("cliente", req, id);
		
		if(response.getStatus() == 200) {
			ClienteDTO res = response.getEntity(ClienteDTO.class);
			return res;
		}
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("cliente", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public ClienteResult readAll(int page) {
		ClientResponse response = getAll("clientes", page);
		
		if(response.getStatus() == 200) {
			ClienteResult result = response.getEntity(ClienteResult.class);
			return result;
		}
		return null;
	}
	
}

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.sun.jersey.api.client.ClientResponse;

public class ServicioManager extends AbstractBaseManager<ServicioDTO> {

	public ServicioManager() {
		super();
	}

	
	public ServicioDTO create(String descripcion, double costo) {
		ServicioDTO req = new ServicioDTO();
		req.setDescripcion(descripcion);
		req.setCosto(costo);
		
		ClientResponse response = save("servicio", req);
		
		if(response.getStatus() == 200) {
			ServicioDTO res = response.getEntity(ServicioDTO.class);
			return res;
		}
		return null;
	}
	
	public ServicioDTO read(int id) {
		ClientResponse response = getById("servicio", id);
		
		if(response.getStatus() == 200) {
			ServicioDTO dto = response.getEntity(ServicioDTO.class);
			return dto;
		}
		return null;
	}
	
	public ServicioDTO update(int id, String descripcion, double costo) {
		ServicioDTO req = new ServicioDTO();
		req.setId(id);
		req.setDescripcion(descripcion);
		req.setCosto(costo);
		
		ClientResponse response = update("servicio", req, id);
		
		if(response.getStatus() == 200) {
			ServicioDTO res = response.getEntity(ServicioDTO.class);
			return res;
		}
		
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("servicio", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public ServicioResult readAll(int page) {
		ClientResponse response = getAll("servicios", page);
		
		if(response.getStatus() == 200) {
			ServicioResult result = response.getEntity(ServicioResult.class);
			return result;
		}
		return null;
	}
	
}

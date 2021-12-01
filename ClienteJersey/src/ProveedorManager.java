
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.sun.jersey.api.client.ClientResponse;

public class ProveedorManager extends AbstractBaseManager<ProveedorDTO> {

	public ProveedorManager() {
		super();
	}

	
	public ProveedorDTO create(String nombre, String direccion, String ruc, String telefono) {
		ProveedorDTO req = new ProveedorDTO();
		req.setId(0);
		req.setNombre(nombre);
		req.setDireccion(direccion);
		req.setRuc(ruc);
		req.setTelefono(telefono);
		
		ClientResponse response = save("proveedor", req);
		
		if(response.getStatus() == 200) {
			ProveedorDTO res = response.getEntity(ProveedorDTO.class);
			return res;
		}
		return null;
	}
	
	public ProveedorDTO read(int id) {
		ClientResponse response = getById("proveedor", id);
		
		if(response.getStatus() == 200) {
			ProveedorDTO dto = response.getEntity(ProveedorDTO.class);
			return dto;
		}
		return null;
	}
	
	public ProveedorDTO update(int id, String nombre, String direccion, String ruc, String telefono) {
		ProveedorDTO req = new ProveedorDTO();
		req.setId(id);
		req.setNombre(nombre);
		req.setDireccion(direccion);
		req.setRuc(ruc);
		req.setTelefono(telefono);
		
		ClientResponse response = update("proveedor", req, id);
		
		if(response.getStatus() == 200) {
			ProveedorDTO res = response.getEntity(ProveedorDTO.class);
			return res;
		}
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("proveedores", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public ProveedorResult readAll(int page) {
		ClientResponse response = getAll("proveedores", page);
		
		if(response.getStatus() == 200) {
			ProveedorResult result = response.getEntity(ProveedorResult.class);
			return result;
		}
		return null;
	}
	
}
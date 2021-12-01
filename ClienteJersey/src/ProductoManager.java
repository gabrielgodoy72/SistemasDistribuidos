
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.sun.jersey.api.client.ClientResponse;

public class ProductoManager extends AbstractBaseManager<ProductoDTO> {

	public ProductoManager() {
		super();
	}

	
	public ProductoDTO create(String descripcion, double costo) {
		ProductoDTO req = new ProductoDTO();
		req.setDescripcion(descripcion);
		req.setCosto(costo);
		
		ClientResponse response = save("producto", req);
		
		if(response.getStatus() == 200) {
			ProductoDTO res = response.getEntity(ProductoDTO.class);
			return res;
		}
		return null;
	}
	
	public ProductoDTO read(int id) {
		ClientResponse response = getById("producto", id);
		
		if(response.getStatus() == 200) {
			ProductoDTO dto = response.getEntity(ProductoDTO.class);
			return dto;
		}
		return null;
	}
	
	public ProductoDTO update(int id, String descripcion, double costo) {
		ProductoDTO req = new ProductoDTO();
		req.setId(id);
		req.setDescripcion(descripcion);
		req.setCosto(costo);
		
		ClientResponse response = update("producto", req, id);
		
		if(response.getStatus() == 200) {
			ProductoDTO res = response.getEntity(ProductoDTO.class);
			return res;
		}
		
		return null;
	}
	

	public boolean delete(int id) {
		ClientResponse response = delete("producto", id);
		
		if(response.getStatus() == 200) {
			return true;
		} 
		return false;
	}

	public ProductoResult readAll(int page) {
		ClientResponse response = getAll("productos", page);
		
		if(response.getStatus() == 200) {
			ProductoResult result = response.getEntity(ProductoResult.class);
			return result;
		}
		return null;
	}
	
}

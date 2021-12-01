import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import com.fiuni.sd.dto.base.BaseDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class AbstractBaseManager<DTO extends BaseDTO>  {
	private final String BASE_URL = "http://localhost:3000/api/";
	private final Client jerseyClient;
	private final WebResource webResource;

	public AbstractBaseManager() {
		ClientConfig config = new DefaultClientConfig();
		config.getClasses().add(JacksonJsonProvider.class);
		jerseyClient = Client.create(config);
		webResource = jerseyClient.resource(BASE_URL);
	}
	
	protected ClientResponse save(String path, DTO req) {
		return webResource.path(path).accept("application/json").type("application/json").post(ClientResponse.class, req);
	}
	
	protected ClientResponse getById(String path, int id) {
		return webResource.path(path + "/"+ id).accept("application/json").get(ClientResponse.class);
	}
	
	protected ClientResponse update(String path, DTO req, int id) {
		return webResource.path(path + "/"+ id).accept("application/json").type("application/json").put(ClientResponse.class, req);
	}
	
	protected ClientResponse delete(String path, int id) {
		return webResource.path(path + "/"+ id).accept("application/json").type("application/json").delete(ClientResponse.class);
	}
	
	protected ClientResponse getAll(String path, int page) {
		return webResource.path(path + "/page/" + page).accept("application/json").type("application/json").get(ClientResponse.class);
	}
	
}

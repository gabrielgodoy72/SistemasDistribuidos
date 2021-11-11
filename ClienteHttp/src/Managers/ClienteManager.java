package Managers;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.google.gson.Gson;

public class ClienteManager extends AbstractBaseManager {

	private Gson gson = new Gson();
	private String path; 

	public ClienteManager() {
		super();
		path = getBaseUrl() + "clientes/";
	}
	
	public void addClient(ClienteDTO clienteDto) {
		String clienteJson = gson.toJson(clienteDto);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(clienteJson)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ClienteDTO cliente = gson.fromJson(response.body(), ClienteDTO.class);
				System.out.println(cliente.toString());
			} else {
				System.out.println("Ocurrió un error al agregar al cliente");
			}    
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void getClientById(int id) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(path + id)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				ClienteDTO cliente = gson.fromJson(response.body(), ClienteDTO.class);
				System.out.println(cliente.toString());
			} else {
				System.out.println("El servidor no pudo encontrar el contenido solicitado");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

}

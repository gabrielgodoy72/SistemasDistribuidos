package Managers;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
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
	
	public void updateClient(ClienteDTO clienteDto) {
		String clienteJson = gson.toJson(clienteDto);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(clienteJson)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ClienteDTO cliente = gson.fromJson(response.body(), ClienteDTO.class);
				System.out.println(cliente.toString());
			} else {
				System.out.println("Ocurrió un error al actualizar al cliente");
			}    
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void delete(int id) {
		HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(path + id)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				System.out.println("Pedido eliminado con exito");
			} else {
				System.out.println("El servidor no pudo encontrar el contenido solicitado");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void getClients(int page_num) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(path + "page/" + page_num)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				ArrayList<ClienteDTO> misObjetosDTO = new ArrayList<ClienteDTO>();
	            String respuestaJson = getList(response.body());
	            int total = getListSize(response.body());
	            int desde = 0;
	            for(int i = 0; i < total; i++) {
	            	int hasta = respuestaJson.indexOf('}') + 1;
	            	misObjetosDTO.add(gson.fromJson(respuestaJson.substring(desde, hasta), ClienteDTO.class));
	            	respuestaJson = respuestaJson.substring(hasta);
	            	desde = 1;
	            }
	            System.out.println("Hay un total de " + total + " Clientes");
	            for(int i = 0; i < misObjetosDTO.size(); i++) {
	            	System.out.println("\t" + misObjetosDTO.get(i));
	            }
			} else {
				System.out.println("El servidor no pudo encontrar el contenido solicitado");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
}

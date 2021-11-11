package Managers;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.google.gson.Gson;

public class ProveedorManager extends AbstractBaseManager  {
	private Gson gson = new Gson();
	private String path; 
	public ProveedorManager() {
		super();
		path = getBaseUrl() + "proveedores/";
	}
	
	public void addProveedor( ProveedorDTO proveedorDto) {
		String proveedorJson = gson.toJson(proveedorDto);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(proveedorJson)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ProveedorDTO proveedor = gson.fromJson(response.body(), ProveedorDTO.class);
				System.out.println(proveedor.toString());
			} else {
				System.out.println("Ocurrió un error al agregar el proveedor");
			}    
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void getProveedoryId(int id) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(path + id)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				ProveedorDTO proveedor  = gson.fromJson(response.body(), ProveedorDTO.class);
				System.out.println(proveedor.toString());
			} else {
				System.out.println("El servidor no pudo encontrar el contenido solicitado");
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
				ArrayList<ProveedorDTO> misObjetosDTO = new ArrayList<ProveedorDTO>();
	            String respuestaJson = getList(response.body());
	            int total = getListSize(response.body());
	            int desde = 0;
	            for(int i = 0; i < total; i++) {
	            	int hasta = respuestaJson.indexOf('}') + 1;
	            	misObjetosDTO.add(gson.fromJson(respuestaJson.substring(desde, hasta), ProveedorDTO.class));
	            	respuestaJson = respuestaJson.substring(hasta);
	            	desde = 1;
	            }
	            System.out.println("Hay un total de " + total + " Proveedores");
	            for(int i = 0; i < misObjetosDTO.size(); i++) {
	            	System.out.println("\t" + misObjetosDTO.get(i));
	            }
			} else {
				System.out.println("El servidor no pudo encontrar el contenido solicitado");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
}

package Managers;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.google.gson.Gson;

public class ServicioManager  extends AbstractBaseManager {


	private Gson gson = new Gson();
	private String path; 

	public ServicioManager() {
		super();
		path = getBaseUrl() + "servicios/";
	}
	
	public void addServicio(ServicioDTO servicioDto) {
		String servicioJson = gson.toJson(servicioDto);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(servicioJson)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ServicioDTO servicio = gson.fromJson(response.body(), ServicioDTO.class);
				System.out.println(servicio.toString());
			} else {
				System.out.println(response.statusCode());
				System.out.println("Ocurrió un error al agregar  el servicio");
			}    
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void updateServicio(ServicioDTO servicioDto) {
		String servicioJson = gson.toJson(servicioDto);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(servicioJson)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ServicioDTO servicio = gson.fromJson(response.body(), ServicioDTO.class);
				System.out.println(servicio.toString());
			} else {
				System.out.println(response.statusCode());
				System.out.println("Ocurrió un error al actualizar  el servicio");
			}    
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void getServicioById(int id) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(path + id)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				ServicioDTO servicio = gson.fromJson(response.body(), 	ServicioDTO.class);
				System.out.println(servicio.toString());
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
	
	public void getServicios(int page_num) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(path + "page/" + page_num)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				ArrayList<ServicioDTO> misObjetosDTO = new ArrayList<ServicioDTO>();
	            String respuestaJson = getList(response.body());
	            int total = getListSize(response.body());
	            int desde = 0;
	            for(int i = 0; i < total; i++) {
	            	int hasta = respuestaJson.indexOf('}') + 1;
	            	misObjetosDTO.add(gson.fromJson(respuestaJson.substring(desde, hasta), ServicioDTO.class));
	            	respuestaJson = respuestaJson.substring(hasta);
	            	desde = 1;
	            }
	            System.out.println("Hay un total de " + total + " Servicios");
	            for(int i = 0; i < misObjetosDTO.size(); i++) {
	            	System.out.println("\t" + misObjetosDTO.get(i));
	            }
			} else {
				System.out.println("El servidor no pudo encontrar el contenido solicitado");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}


}

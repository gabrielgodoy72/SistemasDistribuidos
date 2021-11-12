package Managers;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioDTO2;
import com.google.gson.Gson;

public class UsuarioManager extends AbstractBaseManager {

	private Gson gson = new Gson();
	private String path; 

	public UsuarioManager() {
		super();
		path = getBaseUrl() + "usuario/";
	}
	
	public void addUsuario(UsuarioDTO2 usuarioDto2) {
		String usuarioJson = gson.toJson(usuarioDto2);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(usuarioJson)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				UsuarioDTO usuario = gson.fromJson(response.body(), UsuarioDTO.class);
				System.out.println(usuario.toString());
			} else {
				System.out.println("Ocurrió un error al agregar al usuario");
			}    
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void updateUsuario(UsuarioDTO usuarioDto) {
		String usuarioJson = gson.toJson(usuarioDto);
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(usuarioJson)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				UsuarioDTO usuario = gson.fromJson(response.body(), UsuarioDTO.class);
				System.out.println(usuario.toString());
			} else {
				System.out.println("Ocurrió un error al actualizar al usuario");
			}    
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void getUsuarioById(int id) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(path + id)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				UsuarioDTO usuario = gson.fromJson(response.body(), UsuarioDTO.class);
				System.out.println(usuario.toString());
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
	
	public void getUsuarios(int page_num) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(path + "page/" + page_num)).build();
		HttpResponse<String> response;
		try {
			response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());		
			if (response.statusCode() == 200) {
				ArrayList<UsuarioDTO> misObjetosDTO = new ArrayList<UsuarioDTO>();
	            String respuestaJson = getList(response.body());
	            int total = getListSize(response.body());
	            int desde = 0;
	            for(int i = 0; i < total; i++) {
	            	int hasta = respuestaJson.indexOf('}') + 1;
	            	misObjetosDTO.add(gson.fromJson(respuestaJson.substring(desde, hasta), UsuarioDTO.class));
	            	respuestaJson = respuestaJson.substring(hasta);
	            	desde = 1;
	            }
	            System.out.println("Hay un total de " + total + " Usuarios");
	            for(int i = 0; i < misObjetosDTO.size(); i++) {
	            	System.out.println("\t" + misObjetosDTO.get(i));
	            }
			} else {
				System.out.println("El servidor no pudo encontrar el contenido solicitado");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}


}

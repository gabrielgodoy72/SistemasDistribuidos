import java.util.Random;

import com.fiuni.sd.dto.cliente.ClienteDTO;

import Managers.ClienteManager;

public class Principal {

	public static void main(String args[]) {
		String nombres[] = {"Pedro", "Lucy", "Andrea", "Gerardo", "Raquel", "Esteban", "Lorena", "Luis"};
		String apellidos[] = {"Ramirez", "Gonzalez", "Gutierrez", "Pereira", "Segovia", "Lopez", "Acosta", "Gimenez"};
		Random rnd = new Random();
		
		ClienteManager cliente = new ClienteManager();
		cliente.getClientById(1);
		
		for(int i = 0; i < 10; i++) {
			int rnd1 = rnd.nextInt(nombres.length);
			int rnd2 = rnd.nextInt(apellidos.length);
			
			ClienteDTO clienteNuevo= new ClienteDTO();
			clienteNuevo.setCi(rnd1 + rnd2 + "-" + i);
			clienteNuevo.setDireccion("Encarnacion");
			clienteNuevo.setEmail(nombres[rnd1].toLowerCase() + "@gmail.com");
			clienteNuevo.setId(0);
			clienteNuevo.setNombre(nombres[rnd1] + " " + apellidos[rnd1]);
			clienteNuevo.setTelefono("0985555" + rnd2 + rnd1 );
			cliente.addClient(clienteNuevo);
		}
		
		cliente.getClients(0);
	}
}

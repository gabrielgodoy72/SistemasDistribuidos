import com.fiuni.sd.dto.cliente.ClienteDTO;

import Managers.ClienteManager;

public class Principal {

	public static void main(String args[]) {
		ClienteManager cliente = new ClienteManager();
		cliente.getClientById(1);
		
		ClienteDTO clienteNuevo= new ClienteDTO();
		clienteNuevo.setCi("88564");
		clienteNuevo.setDireccion("Encarnacion");
		clienteNuevo.setEmail("pepe@gmail.com");
		clienteNuevo.setId(0);
		clienteNuevo.setNombre("Pepe Perez");
		clienteNuevo.setTelefono("0985777555");
		cliente.addClient(clienteNuevo);
	}
}

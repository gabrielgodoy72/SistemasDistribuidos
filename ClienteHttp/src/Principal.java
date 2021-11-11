import java.util.Random;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;

import Managers.ClienteManager;
import Managers.ProveedorManager;

public class Principal {

	public static void main(String args[]) {
		String nombres[] = {"Pedro", "Lucy", "Andrea", "Gerardo", "Raquel", "Esteban", "Lorena", "Luis"};
		String apellidos[] = {"Ramirez", "Gonzalez", "Gutierrez", "Pereira", "Segovia", "Lopez", "Acosta", "Gimenez"};
		Random rnd = new Random();
		
		ClienteManager cliente = new ClienteManager();
		ProveedorManager proveedor = new ProveedorManager();
		
		/*                   ----------------                       */
		//cliente.getClientById(2);
		//cliente.delete(1);
		
		/*for(int i = 0; i < 3; i++) {
			int rnd1 = rnd.nextInt(nombres.length);
			int rnd2 = rnd.nextInt(apellidos.length);
			
			ClienteDTO clienteNuevo= new ClienteDTO();
			clienteNuevo.setCi(rnd1 + rnd2 + "-" + i);
			clienteNuevo.setDireccion("Encarnacion");
			clienteNuevo.setEmail(nombres[rnd1].toLowerCase() + "@gmail.com");
			clienteNuevo.setId(0);
			clienteNuevo.setNombre(nombres[rnd1] + " " + apellidos[rnd2]);
			clienteNuevo.setTelefono("0985555" + rnd2 + rnd1 );
			cliente.addClient(clienteNuevo);
		}*/
		
		//cliente.getClients(0);
		
		/*                   ----------------                       */
		
		/*for(int i = 0; i < 3; i++) {
			int rnd1 = rnd.nextInt(nombres.length);
			int rnd2 = rnd.nextInt(apellidos.length);
			
			ProveedorDTO proveedorNuevo= new ProveedorDTO();
			proveedorNuevo.setRuc(rnd2 + "555" + rnd1);
			proveedorNuevo.setDireccion("Encarnacion");
			proveedorNuevo.setId(0);
			proveedorNuevo.setNombre(nombres[rnd1] + " " + apellidos[rnd2]);
			proveedorNuevo.setTelefono("0985555" + rnd2 + rnd1 );
			proveedor.addProveedor(proveedorNuevo);
		}*/
		
		proveedor.getProveedores(0);
		//proveedor.delete(1);
		
		ProveedorDTO proveedorEditado= new ProveedorDTO();
		proveedorEditado.setRuc("12548");
		proveedorEditado.setDireccion("Asuncion");
		proveedorEditado.setId(2);
		proveedorEditado.setNombre("Fernando Samaniego");
		proveedorEditado.setTelefono("0985555999" );
		//proveedor.updateProveedor(proveedorEditado);
		
		/*                   ----------------                       */
		
		
	}
}

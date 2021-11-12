import java.util.Random;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioDTO2;

import Managers.ClienteManager;
import Managers.ProductoManager;
import Managers.ProveedorManager;
import Managers.ServicioManager;
import Managers.UsuarioManager;

public class Principal {

	public static void main(String args[]) {
		String nombres[] = {"Pedro", "Lucy", "Andrea", "Gerardo", "Raquel", "Esteban", "Lorena", "Luis"};
		String apellidos[] = {"Ramirez", "Gonzalez", "Gutierrez", "Pereira", "Segovia", "Lopez", "Acosta", "Gimenez"};
		Random rnd = new Random();
		
		ClienteManager cliente = new ClienteManager();
		ProveedorManager proveedor = new ProveedorManager();
		ProductoManager producto = new ProductoManager();
		ServicioManager servicio = new ServicioManager();
		UsuarioManager usuario = new UsuarioManager();
		
		/*                   ----------------                       */
		System.out.println("Se cargan 6 clientes");
		for(int i = 0; i < 6; i++) {
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
		}
		System.out.println("\nSe obtienen los primeros 5 clientes");
		cliente.getClients(0);
		System.out.println("\nSe obtienen el cliente con id 2");
		cliente.getClientById(2);
		System.out.println("\nSe elimina el cliente con id 1");
		cliente.delete(1);
		System.out.println("\nSe obtienen los primeros 5 clientes");
		cliente.getClients(0);
		System.out.println("\nSe edita el cliente con id 2");
		ClienteDTO clienteEditado = new ClienteDTO();
		clienteEditado.setCi("12354");
		clienteEditado.setDireccion("Encarnacion");
		clienteEditado.setEmail("fernando@gmail.com");
		clienteEditado.setId(2);
		clienteEditado.setNombre("Fernando Robles");
		clienteEditado.setTelefono("0985555888");
		cliente.updateClient(clienteEditado);
		
		/*                   ----------------                       ****************************                           ------------------------------*/
		System.out.println("\nSe cargan 6 proveedores");
		for(int i = 0; i < 6; i++) {
			int rnd1 = rnd.nextInt(nombres.length);
			int rnd2 = rnd.nextInt(apellidos.length);
			
			ProveedorDTO proveedorNuevo= new ProveedorDTO();
			proveedorNuevo.setRuc(rnd2 + "555" + rnd1);
			proveedorNuevo.setDireccion("Encarnacion");
			proveedorNuevo.setId(0);
			proveedorNuevo.setNombre(nombres[rnd1] + " " + apellidos[rnd2]);
			proveedorNuevo.setTelefono("0985555" + rnd2 + rnd1 );
			proveedor.addProveedor(proveedorNuevo);
		}
		
		System.out.println("\nSe obtienen los primeros 5 proveedores");
		proveedor.getProveedores(0);
		System.out.println("\nSe obtienen el proveedores con id 2");
		proveedor.getProveedoryId(2);
		System.out.println("\nSe elimina el proveedores con id 1");
		proveedor.delete(1);
		System.out.println("\nSe obtienen los primeros 5 proveedores");
		proveedor.getProveedores(0);
		System.out.println("\nSe edita el proveedores con id 2");
		ProveedorDTO proveedorEditado= new ProveedorDTO();
		proveedorEditado.setRuc("12548");
		proveedorEditado.setDireccion("Asuncion");
		proveedorEditado.setId(2);
		proveedorEditado.setNombre("Fernando Samaniego");
		proveedorEditado.setTelefono("0985555999" );
		proveedor.updateProveedor(proveedorEditado);
		
		/*                   ----------------                       ****************************                           ------------------------------*/
		System.out.println("\nSe cargan 2 Productos");
		ProductoDTO productoNuevo = new ProductoDTO();
		productoNuevo.setId(0);
		productoNuevo.setDescripcion("Switch");
		productoNuevo.setCosto(80000.00);
		producto.addProducto(productoNuevo);
		productoNuevo.setId(0);
		productoNuevo.setDescripcion("Router");
		productoNuevo.setCosto(100000.00);
		producto.addProducto(productoNuevo);
		System.out.println("\nSe obtienen los primeros 5 productos");
		producto.getProductos(0);
		System.out.println("\nSe obtienen el productos con id 2");
		producto.getProductoById(2);
		System.out.println("\nSe elimina el productos con id 1");
		producto.delete(1);
		System.out.println("\nSe obtienen los primeros 5 productos");
		producto.getProductos(0);
		System.out.println("\nSe edita el productos con id 2");
		ProductoDTO productoEditado = new ProductoDTO();
		productoEditado.setDescripcion("Cable de Fibra Optica");
		productoEditado.setCosto(150000.00);
		productoEditado.setId(2);
		producto.updateProducto(productoEditado);
		
		/*                   ----------------                       ****************************                           ------------------------------*/
		System.out.println("\nSe cargan 2 Servicios");
		ServicioDTO servicioNuevo = new ServicioDTO();
		servicioNuevo.setId(0);
		servicioNuevo.setDescripcion("Iptv copaco");
		servicioNuevo.setCosto(80000.00);
		servicio.addServicio(servicioNuevo);
		servicioNuevo.setId(0);
		servicioNuevo.setDescripcion("Internet fibra optica 50 Mbits/s");
		servicioNuevo.setCosto(100000.00);
		servicio.addServicio(servicioNuevo);
		System.out.println("\nSe obtienen los primeros 5 servicios");
		servicio.getServicios(0);
		System.out.println("\nSe obtienen el servicios con id 2");
		servicio.getServicioById(2);
		System.out.println("\nSe elimina el servicios con id 1");
		servicio.delete(1);
		System.out.println("\nSe obtienen los primeros 5 servicios");
		servicio.getServicios(0);
		System.out.println("\nSe edita el servicios con id 2");
		ServicioDTO servicioEditado = new ServicioDTO();
		productoEditado.setDescripcion("Internet fibra optica 100 Mbits/s");
		productoEditado.setCosto(150000.00);
		productoEditado.setId(2);
		servicio.updateServicio(servicioEditado);
		
		/*                   ----------------                       ****************************                           ------------------------------*/
		System.out.println("Se cargan 6 usuarios");
		for(int i = 0; i < 6; i++) {
			int rnd1 = rnd.nextInt(nombres.length);
			int rnd2 = rnd.nextInt(apellidos.length);
			
			UsuarioDTO2 usuarioNuevo= new UsuarioDTO2();
			usuarioNuevo.setNombre(nombres[rnd1]);
			usuarioNuevo.setApellido(apellidos[rnd2]);
			usuarioNuevo.setEmail(nombres[rnd1].toLowerCase() + "@gmail.com");
			usuarioNuevo.setId(0);
			usuarioNuevo.setUsername((nombres[rnd1] + apellidos[rnd2]).toLowerCase());
			usuarioNuevo.setPassword("muySecreto" + i);
			usuario.addUsuario(usuarioNuevo);
		}
		System.out.println("\nSe obtienen los primeros 5 usuarios");
		usuario.getUsuarios(0);
		System.out.println("\nSe obtienen el usuarios con id 2");
		usuario.getUsuarioById(2);
		System.out.println("\nSe elimina el usuarios con id 1");
		usuario.delete(1);
		System.out.println("\nSe obtienen los primeros 5 usuarios");
		usuario.getUsuarios(0);
		System.out.println("\nSe edita el usuario con id 2");
		UsuarioDTO usuarioEditado= new UsuarioDTO();
		usuarioEditado.setNombre("Gabriel");
		usuarioEditado.setApellido("Bareiro");
		usuarioEditado.setEmail("gabriel@gmail.com");
		usuarioEditado.setId(2);
		usuarioEditado.setUsername("bareirobareiro");
		usuario.updateUsuario(usuarioEditado);
	}
}

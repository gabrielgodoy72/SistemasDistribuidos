import java.text.DateFormat;
import java.util.Date;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.dto.factura.venta.FacturaVentaDTO;
import com.fiuni.sd.dto.factura.venta.FacturaVentaResult;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleResult;
import com.fiuni.sd.dto.pedido.PedidoDTO;
import com.fiuni.sd.dto.pedido.PedidoResult;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleDTO;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleResult;
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;

public class Principal {

	public static void main(String args[]) {
		
		boolean confirmacionBorrado;
		
//**********************************--------------------------------------****************************************
		ProductoManager productoM = new ProductoManager();
		ProductoDTO producto;
		//CREATE
			/*productoM.create("Router", 200000);
			productoM.create("Cable UTP", 3500);
			productoM.create("Cable Fibra Optica", 9000);
			productoM.create("Switch", 160000);*/
		//READ
			/*producto = productoM.read(1);
			showProduct(producto);*/
		//UPDATE
			/*producto = productoM.update(5, "Cinta Aisladora", 5500);
			showProduct(producto);*/
		//DELETE
			/*confirmacionBorrado = productoM.delete(5);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*ProductoResult productos = productoM.readAll(1);
			productos.getProductos().forEach((ProductoDTO p) -> showProduct(p));*/
		
//**********************************--------------------------------------****************************************
		ProveedorManager proveedorM = new ProveedorManager();
		ProveedorDTO proveedor;
		//CREATE
			/*proveedorM.create("Gabriel Godoy", "San Juan del Parana", "4532853", "0985941699");
			proveedorM.create("Eliza Perez", "Encarnación", "4523657", "0975668459");
			proveedorM.create("Gerardo Gimenez", "Carmen del Paraná", "3546985", "0984569966");
			proveedorM.create("María Segovia", "Obligado", "1532666", "0976544552");*/
		//READ
			/*proveedor = proveedorM.read(4);
			showProvider(proveedor);*/
		//UPDATE
			/*proveedor = proveedorM.update(4, "Gabriel Gonzalez",  "San Juan del Parana", "4532853", "0985941699");
			showProvider(proveedor);*/
		//DELETE
			/*confirmacionBorrado = proveedorM.delete(4);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*ProveedorResult proveedores = proveedorM.readAll(0);
			proveedores.getProveedores().forEach((ProveedorDTO p) -> showProvider(p));*/

//**********************************--------------------------------------****************************************
		FacturaCompraManager facturaCompraM = new FacturaCompraManager();
		FacturaCompraDTO facturaCompra;
		//CREATE
			/*facturaCompraM.create("0001-01", 1, new Date(121, 10, 28));
			facturaCompraM.create("0001-02", 2, new Date(121, 10, 29));
			facturaCompraM.create("0001-03", 2, new Date(121, 10, 30));*/
		//READ
			/*facturaCompra = facturaCompraM.read(6);
			showInvoice(facturaCompra);*/
		//UPDATE
			/*facturaCompra = facturaCompraM.update(8, "0001-1256", 2, new Date(120, 5, 3));
			showInvoice(facturaCompra);*/
		//DELETE
			/*confirmacionBorrado = facturaCompraM.delete(7);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*FacturaCompraResult facturasCompra = facturaCompraM.readAll(0);
			facturasCompra.getFacturasCompra().forEach((FacturaCompraDTO f) -> showInvoice(f));*/
		
//**********************************--------------------------------------****************************************
		FacturaCompraDetalleManager facturaCompraDetalleM = new FacturaCompraDetalleManager();
		FacturaCompraDetalleDTO facturaCompraDetalle;
		//CREATE
			/*facturaCompraDetalleM.create(1, 1, 2);//facturaId, productoId, cantidad
			facturaCompraDetalleM.create(1, 2, 3);
			facturaCompraDetalleM.create(2, 3, 2);
			facturaCompraDetalleM.create(2, 4, 4);
			facturaCompraDetalleM.create(3, 1, 1);
			facturaCompraDetalleM.create(3, 4, 3);*/
		//READ
			/*facturaCompraDetalle = facturaCompraDetalleM.read(1);
			showInvoiceDetail(facturaCompraDetalle);*/
		//UPDATE
			/*facturaCompraDetalle = facturaCompraDetalleM.update(1, 2, 2, 10);
			showInvoiceDetail(facturaCompraDetalle);*/
		//DELETE
			/*confirmacionBorrado = facturaCompraM.delete(1);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*FacturaCompraDetalleResult facturasCompraDetalle = facturaCompraDetalleM.readAll(0);
			facturasCompraDetalle.getFacturasCompraDetalle().forEach((FacturaCompraDetalleDTO f) -> showInvoiceDetail(f));*/
			
//**********************************--------------------------------------****************************************
		ServicioManager servicioM = new ServicioManager();
		ServicioDTO servicio;
		//CREATE
			/*servicioM.create("Internet de 5 mb/s", 200000);
			servicioM.create("Internet de 15 mb/s", 9000);
			servicioM.create("Video Cable", 160000);
			servicioM.create("test", 160000);*/
		//READ
			/*servicio = servicioM.read(4);
			showService(servicio);*/
		//UPDATE
			/*servicio = servicioM.update(1, "Internet de 10 mb/s", 5500);
			showService(servicio);*/
		//DELETE
			/*confirmacionBorrado = servicioM.delete(4);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*ServicioResult servicios = servicioM.readAll(0);
			servicios.getServicio().forEach((ServicioDTO s) -> showService(s));*/
			
//**********************************--------------------------------------****************************************
		ClienteManager clienteM = new ClienteManager();
		ClienteDTO cliente;
		//CREATE
			/*clienteM.create("1523654", "Pamela Suarez", "Encarnación", "pamela@gmail.com", "0975888999");
			clienteM.create("2563211", "Hector Rodriguez", "Cambyreta", "hector@gmail.com", "0985469854");
			clienteM.create("3211122", "Emilio Bordon", "Cambyreta", "emilio@gmail.com", "0986254158");
			clienteM.create("1212121", "Lara Acosta", "Cambyreta", "lara@gmail.com", "0995232145");*/
		//READ
			/*cliente = clienteM.read(4);
			showClient(cliente);*/
		//UPDATE
			/*cliente = clienteM.update(4, "1212121", "Lara Morel", "Cambyreta", "lara@gmail.com", "0995232145");
			showClient(cliente);*/
		//DELETE
			/*confirmacionBorrado = clienteM.delete(4);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*ClienteResult clientes = clienteM.readAll(0);
			clientes.getClientes().forEach((ClienteDTO c) -> showClient(c));*/
			
//**********************************--------------------------------------****************************************
		FacturaVentaManager facturaVentaM = new FacturaVentaManager();
		FacturaVentaDTO facturaVenta;
		//CREATE
			/*facturaVentaM.create("1551-13", 1, new Date(121, 10, 28));
			facturaVentaM.create("1551-14", 2, new Date(121, 10, 29));
			facturaVentaM.create("1551-15", 2, new Date(121, 10, 30));
			facturaVentaM.create("test", 2, new Date(121, 10, 30));*/
		//READ
			/*facturaVenta = facturaVentaM.read(4);
			showBillOfSale(facturaVenta);*/
		//UPDATE
			/*facturaVenta = facturaVentaM.update(4, "testeado", 2, new Date(120, 5, 3));
			showBillOfSale(facturaVenta);*/
		//DELETE
			/*confirmacionBorrado = facturaVentaM.delete(4);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*FacturaVentaResult facturasVenta = facturaVentaM.readAll(0);
			facturasVenta.getFacturasVenta().forEach((FacturaVentaDTO f) -> showBillOfSale(f));*/
			
//**********************************--------------------------------------****************************************
		FacturaVentaDetalleManager facturaVentaDetalleM = new FacturaVentaDetalleManager();
		FacturaVentaDetalleDTO facturaVentaDetalle;
		//CREATE
			/*facturaVentaDetalleM.create(1, 1, 2);//facturaId, servicioId, cantidad
			facturaVentaDetalleM.create(1, 2, 3);
			facturaVentaDetalleM.create(2, 3, 2);
			facturaVentaDetalleM.create(3, 1, 1);
			facturaVentaDetalleM.create(2, 1, 3);
			facturaVentaDetalleM.create(3, 2, 1);*/
		//READ
			/*facturaVentaDetalle = facturaVentaDetalleM.read(6);
			showBillOfSaleDetail(facturaVentaDetalle);*/
		//UPDATE
			/*facturaVentaDetalle = facturaVentaDetalleM.update(6, 3, 2, 10);
			showBillOfSaleDetail(facturaVentaDetalle);*/
		//DELETE
			/*confirmacionBorrado = facturaVentaDetalleM.delete(6);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*FacturaVentaDetalleResult facturasVentaDetalle = facturaVentaDetalleM.readAll(0);
			facturasVentaDetalle.getFacturasVentaDetalle().forEach((FacturaVentaDetalleDTO f) -> showBillOfSaleDetail(f));*/
		
//**********************************--------------------------------------****************************************
		PedidoManager pedidoM = new PedidoManager();
		PedidoDTO pedido;
		//CREATE
			/*pedidoM.create(1, false);//clienteId, estado
			pedidoM.create(2, false);
			pedidoM.create(3, false);
			pedidoM.create(3, true);*/
		//READ
			/*pedido = pedidoM.read(4);
			showOrder(pedido);*/
		//UPDATE
			/*pedido = pedidoM.update(4, 3, false);
			showOrder(pedido);*/
		//DELETE
			/*confirmacionBorrado = pedidoM.delete(4);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			/*PedidoResult pedidos = pedidoM.readAll(0);
			pedidos.getPedidos().forEach((PedidoDTO p) -> showOrder(p));*/

//**********************************--------------------------------------****************************************
		PedidoDetalleManager pedidoDetalleM = new PedidoDetalleManager();
		PedidoDetalleDTO pedidoDetalle;
		//CREATE
			/*pedidoDetalleM.create(1, 1, new Date(121, 2, 4));//pedidoId, servicioId, fechaAdquisicion
			pedidoDetalleM.create(1, 2, new Date(121, 4, 9));
			pedidoDetalleM.create(1, 3, new Date(121, 5, 10));
			pedidoDetalleM.create(2, 1, new Date(121, 5, 2));
			pedidoDetalleM.create(2, 2, new Date(121, 5, 3));*/
		//READ
			/*pedidoDetalle = pedidoDetalleM.read(5);
			showOrderDetail(pedidoDetalle);*/
		//UPDATE
			/*pedidoDetalle = pedidoDetalleM.update(5, 2, 2, new Date(116, 5, 3));
			showOrderDetail(pedidoDetalle);*/
		//DELETE
			/*confirmacionBorrado = pedidoDetalleM.delete(5);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			PedidoDetalleResult pedidosDetalle = pedidoDetalleM.readAll(0);
			pedidosDetalle.getPedidosDetalle().forEach((PedidoDetalleDTO p) -> showOrderDetail(p));
	}
	
	private static void showProduct(ProductoDTO producto) {
		if(null != producto) {
			System.out.println("ProductoId: " + producto.getId() + 
								"\nDescripción: " + producto.getDescripcion() + 
								"\nCosto: " + producto.getCosto() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showProvider(ProveedorDTO proveedor) {
		if(null != proveedor) {
			System.out.println("ProveedorId: " + proveedor.getId() + 
								"\nNombre: " + proveedor.getNombre() + 
								"\nDirección: " + proveedor.getDireccion() + 
								"\nRuc: " + proveedor.getRuc() + 
								"\nTeléfono: " + proveedor.getTelefono() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showInvoice(FacturaCompraDTO factura) {
		DateFormat formateador = DateFormat.getDateInstance(DateFormat.SHORT);
		if(null != factura) {
			System.out.println("FacturaId: " + factura.getId() + 
								"\nNúmero: " + factura.getNumero() + 
								"\nFecha: " + formateador.format(factura.getFecha()) + 
								"\nProveedorId: " + factura.getProveedor_id() + 
								"\nTotal: " + factura.getTotal() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showInvoiceDetail(FacturaCompraDetalleDTO facturaDetalle) {
		if(null != facturaDetalle) {
			System.out.println("FacturaDetalleId: " + facturaDetalle.getId() + 
								"\nFacturaId: " + facturaDetalle.getFactura_id() + 
								"\nProductoId: " + facturaDetalle.getProducto_id() + 
								"\nCantidad: " + facturaDetalle.getCantidad() + 
								"\nSubTotal: " + facturaDetalle.getSubtotal() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}

	private static void showService(ServicioDTO servicio) {
		if(null != servicio) {
			System.out.println("ServicioId: " + servicio.getId() + 
								"\nDescripción: " + servicio.getDescripcion() + 
								"\nCosto: " + servicio.getCosto() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showClient(ClienteDTO cliente) {
		if(null != cliente) {
			System.out.println("ClienteId: " + cliente.getId() + 
								"\nC.I. N°: " + cliente.getCi() + 
								"\nNombre: " + cliente.getNombre() + 
								"\nDireccion: " + cliente.getDireccion() +
								"\nEmail: " + cliente.getEmail() +
								"\nTelefono: " + cliente.getTelefono() +"\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showBillOfSale(FacturaVentaDTO factura) {
		DateFormat formateador = DateFormat.getDateInstance(DateFormat.SHORT);
		if(null != factura) {
			System.out.println("FacturaId: " + factura.getId() + 
								"\nNúmero: " + factura.getNumero() + 
								"\nFecha: " + formateador.format(factura.getFecha()) + 
								"\nClienteId: " + factura.getCliente_id() + 
								"\nTotal: " + factura.getTotal() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showBillOfSaleDetail(FacturaVentaDetalleDTO facturaDetalle) {
		if(null != facturaDetalle) {
			System.out.println("FacturaDetalleId: " + facturaDetalle.getId() + 
								"\nFacturaId: " + facturaDetalle.getFactura_id() + 
								"\nServicioId: " + facturaDetalle.getServicio_id() + 
								"\nCantidad: " + facturaDetalle.getCantidad() + 
								"\nSubTotal: " + facturaDetalle.getSubtotal() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showOrder(PedidoDTO pedido) {
		if(null != pedido) {
			System.out.println("PedidoId: " + pedido.getId() + 
								"\nClienteId: " + pedido.getId_cliente() + 
								"\nEstado: " + pedido.getEstado() + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}
	
	private static void showOrderDetail(PedidoDetalleDTO pedido) {
		DateFormat formateador = DateFormat.getDateInstance(DateFormat.SHORT);
		if(null != pedido) {
			System.out.println("PedidoDetalleId: " + pedido.getId() + 
								"\nPedidoId: " + pedido.getPedido_id() + 
								"\nServicioId: " + pedido.getServicio_id() + 
								"\nFecha de Aquisicion: " + formateador.format(pedido.getFecha()) + "\n");
		} else {
			System.out.println("Ocurrió un error");
		}
	}

}

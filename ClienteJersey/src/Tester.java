import java.text.DateFormat;
import java.util.Date;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.venta.FacturaVentaDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleDTO;
import com.fiuni.sd.dto.pedido.PedidoDTO;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleDTO;
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.servicio.ServicioDTO;

public class Tester {

	public static void main(String args[]) {
		
		ProductoManager productoM = new ProductoManager();
		ProveedorManager proveedorM = new ProveedorManager();
		FacturaCompraManager facturaCompraM = new FacturaCompraManager();
		FacturaCompraDetalleManager facturaCompraDetalleM = new FacturaCompraDetalleManager();
		ServicioManager servicioM = new ServicioManager();
		ClienteManager clienteM = new ClienteManager();
		FacturaVentaManager facturaVentaM = new FacturaVentaManager();
		FacturaVentaDetalleManager facturaVentaDetalleM = new FacturaVentaDetalleManager();
		PedidoManager pedidoM = new PedidoManager();
		PedidoDetalleManager pedidoDetalleM = new PedidoDetalleManager();
		
		//lectura/escritura
			//transacciones de lectura
		
			//transacciones de escritura
		
		//required
		//productoM.create("Router", 200000);
			//llamada directa   (Exito y Fallido)
				ProveedorDTO proveedor = proveedorM.create("Gabriel Godoy", "San Juan del Parana", "4532853", "0985941699");
				showProvider(proveedor);
				//ProveedorDTO proveedor = proveedorM.create("Lola", "Carmen del Paraná", "3546985", "0984569966");
				//showProvider(proveedor);
			//llamada desde otro metodo con transaccion (Exito y Fallido)
				/*FacturaCompraDTO facturaC = facturaCompraM.create("0001-02", 2, new Date(121, 10, 29));
				showInvoice(facturaC);
				facturaC = facturaCompraM.create("0001-03", 4, new Date(121, 10, 29));
				showInvoice(facturaC);*/
			//llamada desde otro metodo sin transaccion
				/*FacturaCompraDetalleDTO facturaCD = facturaCompraDetalleM.create(2, 1, 3);//facturaId, productoId, cantidad
				showInvoiceDetail(facturaCD);
				facturaCD = facturaCompraDetalleM.create(2, 8, 3);//facturaId, productoId, cantidad
				showInvoiceDetail(facturaCD);*/
		
		//supports
			//llamada directa
			//productoM.create("Router", 200000);
		
			//llamada desde otro metodo, con transaccion y sin transaccion
		
		//not_supports
			//llamada directa
			//servicioM.create("Internet de 5 mb/s", 200000);
			
			//llamada desde otro metodo, con transaccion y sin transaccion
	
		//requres_new
			//llamada directa
			//clienteM.create("1523654", "Pamela Suarez", "Encarnación", "pamela@gmail.com", "0975888999");
			
			//llamada desde otro metodo, con transaccion y sin transaccion
	
		//never
			//llamada directa
			
			//llamada desde otro metodo, con transaccion y sin transaccion
	
		//mandatory
			//llamada directa
			
			//llamada desde otro metodo, con transaccion y sin transaccion
	
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

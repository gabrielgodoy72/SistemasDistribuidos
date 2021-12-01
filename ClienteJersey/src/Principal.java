import java.text.DateFormat;
import java.util.Date;

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;

public class Principal {

	public static void main(String args[]) {
		
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
			/*boolean confirmacionBorrado = productoM.delete(5);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			ProductoResult productos = productoM.readAll(1);
			productos.getProductos().forEach((ProductoDTO p) -> showProduct(p));
		
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
			/*boolean confirmacionBorrado = proveedorM.delete(4);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			ProveedorResult proveedores = proveedorM.readAll(0);
			proveedores.getProveedores().forEach((ProveedorDTO p) -> showProvider(p));

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
			/*boolean confirmacionBorrado = facturaCompraM.delete(7);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			FacturaCompraResult facturasCompra = facturaCompraM.readAll(0);
			facturasCompra.getFacturasCompra().forEach((FacturaCompraDTO f) -> showInvoice(f));
		
//**********************************--------------------------------------****************************************
		FacturaCompraDetalleManager facturaCompraDetalleM = new FacturaCompraDetalleManager();
		FacturaCompraDetalleDTO facturaCompraDetalle;
		//CREATE
			/*facturaCompraDetalle = facturaCompraDetalleM.create(1, 1, 2);//facturaId, productoId, cantidad
			facturaCompraDetalle = facturaCompraDetalleM.create(1, 2, 3);
			facturaCompraDetalle = facturaCompraDetalleM.create(2, 3, 2);
			facturaCompraDetalle = facturaCompraDetalleM.create(2, 4, 4);
			facturaCompraDetalle = facturaCompraDetalleM.create(3, 1, 1);
			facturaCompraDetalle = facturaCompraDetalleM.create(3, 4, 3);*/
		//READ
			/*facturaCompraDetalle = facturaCompraDetalleM.read(1);
			showInvoiceDetail(facturaCompraDetalle);*/
		//UPDATE
			/*facturaCompraDetalle = facturaCompraDetalleM.update(1, 2, 2, 10);
			showInvoiceDetail(facturaCompraDetalle);*/
		//DELETE
			/*boolean confirmacionBorrado = facturaCompraM.delete(1);
			System.out.println(confirmacionBorrado);*/
		//READ ALL
			FacturaCompraDetalleResult facturasCompraDetalle = facturaCompraDetalleM.readAll(0);
			facturasCompraDetalle.getFacturasCompraDetalle().forEach((FacturaCompraDetalleDTO f) -> showInvoiceDetail(f));
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


}

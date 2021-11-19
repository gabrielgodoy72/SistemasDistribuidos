import com.sd.rest.facturaCompra.FacturaCompraResourceImpl
import com.sd.rest.producto.ProductoResourceImpl
import com.sd.rest.proveedor.ProveedorResourceImpl
import com.sd.rest.usuario.UsuarioResourceImpl
import com.sd.service.facturaCompra.FacturaCompraServiceImpl
import com.sd.service.producto.ProductoServiceImpl
import com.sd.service.proveedor.ProveedorServiceImpl
import com.sd.service.usuario.UsuarioServiceImpl

// Place your Spring DSL code here
beans = {
    //resources
    usuarioResource(UsuarioResourceImpl)
    productoResource(ProductoResourceImpl)
    proveedorResource(ProveedorResourceImpl)
    facturaCompraResource(FacturaCompraResourceImpl)

    //services
    usuarioService(UsuarioServiceImpl)
    productoService(ProductoServiceImpl)
    proveedorService(ProveedorServiceImpl)
    facturaCompraService(FacturaCompraServiceImpl)
}

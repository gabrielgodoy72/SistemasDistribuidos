import asia.grails.sample.UserPasswordEncoderListener
import com.sd.rest.cliente.ClienteResourceImpl
import com.sd.rest.facturaCompra.FacturaCompraResourceImpl
import com.sd.rest.producto.ProductoResourceImpl
import com.sd.rest.proveedor.ProveedorResourceImpl
import com.sd.rest.facturaCompraDetalle.FacturaCompraDetalleResourceImpl
import com.sd.rest.rol.RolResourceImpl
import com.sd.rest.servicio.ServicioResourceImpl
import com.sd.rest.usuario.UsuarioResourceImpl
import com.sd.service.cliente.ClienteServiceImpl
import com.sd.service.facturaCompra.FacturaCompraServiceImpl
import com.sd.service.facturaCompraDetalle.FacturaCompraDetalleServiceImpl
import com.sd.rest.login.AuthServiceImpl
import com.sd.rest.login.MyAuthenticationProvider
import com.sd.service.producto.ProductoServiceImpl
import com.sd.service.proveedor.ProveedorServiceImpl
import com.sd.service.rol.RolServiceImpl
import com.sd.service.servicio.ServicioServiceImpl
import com.sd.service.usuario.UsuarioServiceImpl

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    //resources
    usuarioResource(UsuarioResourceImpl)
    rolResource(RolResourceImpl)
    productoResource(ProductoResourceImpl)
    servicioResource(ServicioResourceImpl)
    proveedorResource(ProveedorResourceImpl)
    clienteResource(ClienteResourceImpl)
    facturaCompraResource(FacturaCompraResourceImpl)
    facturaCompraDetalleResource(FacturaCompraDetalleResourceImpl)
    myAuthenticationProvider(MyAuthenticationProvider)

    //services
    usuarioService(UsuarioServiceImpl)
    rolService(RolServiceImpl)
    productoService(ProductoServiceImpl)
    servicioService(ServicioServiceImpl)
    proveedorService(ProveedorServiceImpl)
    clienteService(ClienteServiceImpl)
    facturaCompraService(FacturaCompraServiceImpl)
    facturaCompraDetalleService(FacturaCompraDetalleServiceImpl)
    authService(AuthServiceImpl)
}

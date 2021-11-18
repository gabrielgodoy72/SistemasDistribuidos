import com.sd.rest.producto.ProductoResourceImpl
import com.sd.rest.usuario.UsuarioResourceImpl
import com.sd.service.producto.ProductoServiceImpl
import com.sd.service.usuario.UsuarioServiceImpl

// Place your Spring DSL code here
beans = {
    //resources
    usuarioResource(UsuarioResourceImpl)
    productoResource(ProductoResourceImpl)

    //services
    usuarioService(UsuarioServiceImpl)
    productoService(ProductoServiceImpl)
}

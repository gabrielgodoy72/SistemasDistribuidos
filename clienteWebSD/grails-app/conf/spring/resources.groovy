import com.sd.rest.usuario.UsuarioResourceImpl
import com.sd.service.usuario.UsuarioServiceImpl

// Place your Spring DSL code here
beans = {
    //resources
    usuarioResource(UsuarioResourceImpl)

    //services
    usuarioService(UsuarioServiceImpl)
}

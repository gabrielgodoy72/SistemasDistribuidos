package com.sd.rest.base;

import com.fiuni.sd.dto.base.BaseDTO;
import com.sd.rest.login.AuthServiceImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseResourceImpl<DTO extends BaseDTO> implements IBaseResource<DTO> {
    private final String _resourcePath;
    private final Class<DTO> _dtoClass;
    private final WebResource _webResource;
    @Autowired
    private AuthServiceImpl authService;

    private static final String BASE_URL = "http://localhost:3000";
    private static final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfVVNFUlwifV0iLCJ1c2VybmFtZSI6ImFuZHJlYUBlbWFpbC5jb20ifQ.emtWQNgWHFJFwfYKJicNmZuqOMAEIF9fOhwQ23vnBz8";

    public BaseResourceImpl(Class<DTO> dtoClass, String resourcePath) {
        _dtoClass = dtoClass;
        _resourcePath = BASE_URL + resourcePath;
        final Client jerseyClient = Client.create();
        _webResource = jerseyClient.resource(_resourcePath);
        //_webResource.header(HttpHeaders.AUTHORIZATION, TOKEN);
    }

    protected WebResource getWebResource() { return _webResource; }

    protected Class<DTO> getDtoClass() {
        return _dtoClass;
    }

    /* Este método se encarga de establecer un usuario y contraseña en la cabecera del request
     * del webResource, lo que le permite autenticarse para acceder a los recursos del webService
     * El web service esta configurado para requerir un tipo de autenticación básica la cual se
     * establece en este método
     * Este método se usa siempre antes de llamar al método getWebResource() en todas las clases ResourceImpl
     * menos las clases ResourceImpl de user y role, en donde authService provocaría un fallo
     * ya que authService requiere que el usuario este logueado para que pueda funcionar
     */
    public void setWebResourceBasicAuthFilter(){
        String u = authService.getUsername();
        String p = authService.getPassword();

        _webResource.addFilter(new HTTPBasicAuthFilter(u,p));
    }

}

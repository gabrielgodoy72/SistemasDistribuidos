package com.sd.rest.base;

import com.fiuni.sd.dto.base.BaseDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import grails.web.http.HttpHeaders;

public abstract class BaseResourceImpl<DTO extends BaseDTO> implements IBaseResource<DTO> {
    private final String _resourcePath;
    private final Class<DTO> _dtoClass;
    private final WebResource _webResource;

    private static final String BASE_URL = "http://localhost:3000";
    private static final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfVVNFUlwifV0iLCJ1c2VybmFtZSI6ImFuZHJlYUBlbWFpbC5jb20ifQ.emtWQNgWHFJFwfYKJicNmZuqOMAEIF9fOhwQ23vnBz8";

    public BaseResourceImpl(Class<DTO> dtoClass, String resourcePath) {
        _dtoClass = dtoClass;
        _resourcePath = BASE_URL + resourcePath;
        final Client jerseyClient = Client.create();
        _webResource = jerseyClient.resource(_resourcePath);
        //_webResource.header(HttpHeaders.AUTHORIZATION, TOKEN);
    }

    protected WebResource getWebResource() {

        return _webResource;
    }

    protected Class<DTO> getDtoClass() {
        return _dtoClass;
    }

    @Override
    public DTO save(DTO dto, String path) {
        return getWebResource().path("/" + path).entity(dto).post(getDtoClass());
    }

    @Override
    public DTO getById(Integer id, String path) {
        return getWebResource().path("/"  + path + "/"  + id).get(getDtoClass());
    }
}

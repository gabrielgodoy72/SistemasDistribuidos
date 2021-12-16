package com.sd.rest.cliente;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.sd.rest.base.IBaseResource;

public interface IClienteResource extends IBaseResource<ClienteDTO> {

    public ClienteResult getAll(Integer page);

}

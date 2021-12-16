package com.sd.service.cliente;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
import com.sd.beans.cliente.ClienteB;
import com.sd.beans.cliente.ClienteBResult;
import com.sd.rest.cliente.IClienteResource;
import com.sd.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("clienteService")
public class ClienteServiceImpl extends BaseServiceImpl<ClienteB, ClienteDTO> implements IClienteService {

    @Autowired
    private IClienteResource clienteResource;

    @Override
    public ClienteB save(ClienteB bean) {
        bean.setId(0);
        return convertDtoToBean(clienteResource.save(convertBeanToDto(bean)));
    }

    @Override
    public ClienteBResult getAll(Integer page) {
        final ClienteBResult clienteBResult = new ClienteBResult();
        final ClienteResult result = clienteResource.getAll(page);
        if(result.getClientes() == null) {
            clienteBResult.setClientes(Collections.emptyList());
        } else {
            List<ClienteB> list = new ArrayList<>();
            result.getClientes().forEach(cliente -> {
                ClienteB bean = convertDtoToBean(cliente);
                list.add(bean);
            });
            clienteBResult.setClientes(list);
            clienteBResult.setPage(result.getPage());
            clienteBResult.setTotalPages(result.getTotalPages());
            clienteBResult.setTotal(result.getTotal());
            clienteBResult.setHasPrev(result.get_hasPrev());
            clienteBResult.setHasNext(result.get_hasNext());
            clienteBResult.setPrevPage(result.getPrevPage());
            clienteBResult.setNextPage(result.getNextPage());
        }
        return clienteBResult;
    }

    @Override
    public ClienteB getById(Integer id) {
        return convertDtoToBean(clienteResource.getById(id));
    }

    @Override
    public ClienteB delete(Integer id) {
        return convertDtoToBean(clienteResource.delete(id));
    }

    @Override
    public ClienteB update(Integer id, ClienteB bean) {
        return convertDtoToBean(clienteResource.update(id, convertBeanToDto(bean)));
    }

    @Override
    protected ClienteB convertDtoToBean(ClienteDTO dto) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("nombre", String.valueOf(dto.getNombre()));
        params.put("direccion", String.valueOf(dto.getDireccion()));
        params.put("email", String.valueOf(dto.getEmail()));
        params.put("ci", String.valueOf(dto.getCi()));
        params.put("telefono", String.valueOf(dto.getTelefono()));
        return new ClienteB(params);
    }

    @Override
    protected ClienteDTO convertBeanToDto(ClienteB bean) {
        final ClienteDTO dto = new ClienteDTO();
        dto.setId(bean.getId());
        dto.setNombre(bean.getNombre());
        dto.setDireccion(bean.getDireccion());
        dto.setEmail(bean.getEmail());
        dto.setCi(bean.getCi());
        dto.setTelefono(bean.getTelefono());
        return dto;
    }
}
package com.sd.service.usuario;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.sd.beans.usuario.UsuarioB;
import com.sd.rest.usuario.IUsuarioResource;
import com.sd.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("usuarioService")
public class UsuarioServiceImpl extends BaseServiceImpl<UsuarioB, UsuarioDTO> implements IUsuarioService {

    @Autowired
    private IUsuarioResource usuarioResource;

    @Override
    public UsuarioB save(UsuarioB bean) {
        return convertDtoToBean(usuarioResource.save(convertBeanToDto(bean), "usuario"));
    }

    @Override
    public List<UsuarioB> getAll(Integer page) {
        final UsuarioResult result = usuarioResource.getAll(page);
        if(result.getUsers() == null) {
            return Collections.emptyList();
        }
        return result.getUsers().stream().map(this::convertDtoToBean).collect(Collectors.toList());
    }

    @Override
    public UsuarioB getById(Integer id) {
        return convertDtoToBean(usuarioResource.getById(id, "usuario"));
    }

    @Override
    public void delete(Integer id) {
        usuarioResource.delete(id, "usuario");
    }

    @Override
    public UsuarioB update(Integer id, UsuarioB bean) {
        return convertDtoToBean(usuarioResource.update(id, convertBeanToDto(bean), "usuario"));
    }

    @Override
    protected UsuarioB convertDtoToBean(UsuarioDTO dto) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("nombre", String.valueOf(dto.getNombre()));
        params.put("apellido", String.valueOf(dto.getApellido()));
        params.put("email", String.valueOf(dto.getEmail()));
        params.put("password", String.valueOf(dto.getPassword()));
        return new UsuarioB(params);
    }

    @Override
    protected UsuarioDTO convertBeanToDto(UsuarioB bean) {
        final UsuarioDTO dto = new UsuarioDTO();
        dto.setId(bean.getId());
        dto.setNombre(bean.getNombre());
        dto.setApellido(bean.getApellido());
        dto.setEmail(bean.getEmail());
        dto.setPassword(bean.getPassword());
        return dto;
    }
}

package com.sd.service.rol;

import com.fiuni.sd.dto.rol.RolDTO;
import com.fiuni.sd.dto.rol.RolResult;
import com.sd.beans.rol.RolB;
import com.sd.beans.rol.RolBResult;
import com.sd.rest.rol.IRolResource;
import com.sd.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("usuarioService")
public class RolServiceImpl extends BaseServiceImpl<RolB, RolDTO> implements IRolService {

    @Autowired
    private IRolResource rolResource;

    @Override
    public RolB save(RolB bean) {
        return convertDtoToBean(rolResource.save(convertBeanToDto(bean)));
    }

    @Override
    public RolBResult getAll(Integer page) {
        final RolBResult rolBResult = new RolBResult();
        final RolResult result = rolResource.getAll(page);
        if(result.getRoles() == null) {
            rolBResult.setRoles(Collections.emptyList());
        } else {
            List<RolB> list = new ArrayList<>();
            result.getRoles().forEach(rol -> {
                RolB bean = convertDtoToBean(rol);
                list.add(bean);
            });
            rolBResult.setRoles(list);
            rolBResult.setPage(result.getPage());
            rolBResult.setTotalPages(result.getTotalPages());
            rolBResult.setTotal(result.getTotal());
        }
        return rolBResult;
    }

    @Override
    public RolB getById(Integer id) {
        return convertDtoToBean(rolResource.getById(id));
    }

    @Override
    public RolB getRolByNombre(String nombre) {
        return convertDtoToBean(rolResource.getByNombre(nombre));
    }

    @Override
    public RolB getRolByDescripcion(String descripcion) {
        return convertDtoToBean(rolResource.getByDescripcion(descripcion));
    }

    @Override
    public void delete(Integer id) {
        rolResource.delete(id);
    }

    @Override
    public RolB update(Integer id, RolB bean) {
        return convertDtoToBean(rolResource.update(id, convertBeanToDto(bean)));
    }

    @Override
    protected RolB convertDtoToBean(RolDTO dto) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("nombre", String.valueOf(dto.getNombre()));
        params.put("descripcion", String.valueOf(dto.getDescripcion()));
        final RolB rolB = new RolB(params);
        return rolB;
    }

    @Override
    protected RolDTO convertBeanToDto(RolB bean) {
        final RolDTO dto = new RolDTO();
        dto.setId(bean.getId());
        dto.setNombre(bean.getNombre());
        dto.setDescripcion(bean.getDescripcion());
        return dto;
    }

}

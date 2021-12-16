package com.sd.service.servicio;

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.sd.beans.servicio.ServicioB;
import com.sd.beans.servicio.ServicioBResult;
import com.sd.rest.servicio.IServicioResource;
import com.sd.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("servicioService")
public class ServicioServiceImpl extends BaseServiceImpl<ServicioB, ServicioDTO> implements IServicioService {

    @Autowired
    private IServicioResource servicioResource;

    @Override
    public ServicioB save(ServicioB bean) {
        return convertDtoToBean(servicioResource.save(convertBeanToDto(bean)));
    }

    @Override
    public ServicioBResult getAll(Integer page) {
        final ServicioBResult servicioBResult = new ServicioBResult();
        final ServicioResult result = servicioResource.getAll(page);
        if(result.getServicios() == null) {
            servicioBResult.setServicios(Collections.emptyList());
        } else {
            List<ServicioB> list = new ArrayList<>();
            result.getServicios().forEach(servicio -> {
                ServicioB bean = convertDtoToBean(servicio);
                list.add(bean);
            });
            servicioBResult.setServicios(list);
            servicioBResult.setPage(result.getPage());
            servicioBResult.setTotalPages(result.getTotalPages());
            servicioBResult.setTotal(result.getTotal());
            servicioBResult.setHasPrev(result.get_hasPrev());
            servicioBResult.setHasNext(result.get_hasNext());
            servicioBResult.setPrevPage(result.getPrevPage());
            servicioBResult.setNextPage(result.getNextPage());
        }
        return servicioBResult;
    }

    @Override
    public ServicioB getById(Integer id) {
        return convertDtoToBean(servicioResource.getById(id));
    }

    @Override
    public ServicioB getByDescripcion(String descripcion) {
        return convertDtoToBean(servicioResource.getByDescripcion(descripcion));
    }

    @Override
    public ServicioB delete(Integer id) {
        return convertDtoToBean(servicioResource.delete(id));
    }

    @Override
    public ServicioB update(Integer id, ServicioB bean) {
        return convertDtoToBean(servicioResource.update(id, convertBeanToDto(bean)));
    }

    @Override
    protected ServicioB convertDtoToBean(ServicioDTO dto) {
        final Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(dto.getId()));
        params.put("descripcion", String.valueOf(dto.getDescripcion()));
        params.put("costo", String.valueOf(dto.getCosto()));
        return new ServicioB(params);
    }

    @Override
    protected ServicioDTO convertBeanToDto(ServicioB bean) {
        final ServicioDTO dto = new ServicioDTO();
        dto.setId(bean.getId());
        dto.setDescripcion(bean.getDescripcion());
        dto.setCosto(bean.getCosto());
        return dto;
    }
}

package com.fiuni.sd.service.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.employee.IEmployeeDao;
import com.fiuni.sd.domain.employee.EmployeeDomain;
import com.fiuni.sd.dto.employee.EmployeeDTO;
import com.fiuni.sd.dto.employee.EmployeeResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeDTO, EmployeeDomain, EmployeeResult> implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public EmployeeDTO save(EmployeeDTO dto) {
		final EmployeeDomain employeeDomain = convertDtoToDomain(dto);
		final EmployeeDomain employee = employeeDao.save(employeeDomain);
		return convertDomainToDto(employee);
	}

	@Override
	public EmployeeDTO getById(Integer id) {
		Optional<EmployeeDomain> result = employeeDao.findById(id);
        EmployeeDTO employee = null;
        if(result.isPresent()){
            employee = convertDomainToDto(result.get());
        } else {
            throw new RuntimeException("Did not find employee id: " + id);
        }
        return employee;
	}

	@Override
	public EmployeeResult getAll(Pageable pageable) {
		final List<EmployeeDTO> employees = new ArrayList<>();
		Page<EmployeeDomain> results = employeeDao.findAll(pageable);
		for (EmployeeDomain employeeDomain : results) {
			employees.add(convertDomainToDto(employeeDomain));
		}
		EmployeeResult employeeResult = new EmployeeResult();
		employeeResult.setEmployees(employees);
		return employeeResult;
	}
	
	@Override
    public void deleteById(int id) {
    	employeeDao.deleteById(id);
    }

	@Override
	protected EmployeeDTO convertDomainToDto(EmployeeDomain domain) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(domain.getId());
		dto.setFirstName(domain.getFirstName());
		dto.setLastName(domain.getLastName());
		dto.setEmail(domain.getEmail());
        return dto;
	}

	@Override
	protected EmployeeDomain convertDtoToDomain(EmployeeDTO dto) {
		final EmployeeDomain domain = new EmployeeDomain();
		domain.setId(dto.getId());
		domain.setFirstName(dto.getFirstName());
		domain.setLastName(dto.getLastName());
		domain.setEmail(dto.getEmail());
        return domain;
	}

}
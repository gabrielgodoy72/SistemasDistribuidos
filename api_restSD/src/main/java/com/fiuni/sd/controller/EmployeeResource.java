package com.fiuni.sd.controller;

import com.fiuni.sd.dto.employee.EmployeeDTO;
import com.fiuni.sd.dto.employee.EmployeeResult;
import com.fiuni.sd.service.employee.IEmployeeService;
import com.fiuni.sd.utils.Setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api")
public class EmployeeResource {

	@Autowired
    private IEmployeeService employeeService;
	
	@PostMapping(path = "employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDto) {
		if(employeeDto.getId() == null || employeeDto.getId() == 0) {
			EmployeeDTO newEmployee = employeeService.save(employeeDto);
			return ResponseEntity.ok(newEmployee);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "employee/{id}")
	public ResponseEntity<EmployeeDTO> readEmployee(@PathVariable(value = "id") Integer id) {
		try {
			EmployeeDTO employeeDto = employeeService.getById(id);
			return ResponseEntity.ok(employeeDto);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@GetMapping(path = "employee/page/{page_num}")
	public EmployeeResult readEmployees(@PathVariable(value = "page_num") Integer pageNum) {
		return employeeService.getAll(PageRequest.of(pageNum, Setting.PAGE_SIZE));
	}
	
	@PutMapping(path = "employee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDto) {
		if(employeeDto.getId() != null || employeeDto.getId() != 0) {
			try {
				employeeService.getById(employeeDto.getId());
				EmployeeDTO editedEmployee = employeeService.save(employeeDto);
		        return ResponseEntity.ok(editedEmployee);
			} catch (Exception ex) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("employee/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
        	return ResponseEntity.noContent().build();
        }
    }
	
}
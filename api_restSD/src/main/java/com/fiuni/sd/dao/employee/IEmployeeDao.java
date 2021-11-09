package com.fiuni.sd.dao.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fiuni.sd.domain.employee.EmployeeDomain;

@Repository
public interface IEmployeeDao extends JpaRepository<EmployeeDomain, Integer> {
	public Page<EmployeeDomain> findAll(Pageable pageable);
}
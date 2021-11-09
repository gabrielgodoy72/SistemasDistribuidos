package com.fiuni.sd.dao.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.credentials.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {
	public Page<Role> findAll(Pageable pageable);
}

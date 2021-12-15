package com.fiuni.sd.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.credenciales.RoleDomain;

@Repository
public interface IRolDao extends JpaRepository<RoleDomain, Integer> {

	public Page<RoleDomain> findAll(Pageable pageable);
	
	public Optional<RoleDomain> findByNombre(String nombre);

	public Optional<RoleDomain> findByDescripcion(String descripcion);

}

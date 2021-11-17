package com.fiuni.sd.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.servicio.ServicioDomain;

@Repository
public interface IServicioDao extends JpaRepository<ServicioDomain, Integer> {
	
	public Page<ServicioDomain> findAll(final Pageable pageable);
	
	public Optional<ServicioDomain> findByDescripcion(final String descripcion);
}
package com.fiuni.sd.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.proveedor.ProveedorDomain;

@Repository
public interface IProveedorDao extends JpaRepository<ProveedorDomain, Integer> {

	public Page<ProveedorDomain> findAll(Pageable pageable);

	public Optional<ProveedorDomain> findByRuc(String ruc);

	public Optional<ProveedorDomain> findByNombre(String nombre);

}

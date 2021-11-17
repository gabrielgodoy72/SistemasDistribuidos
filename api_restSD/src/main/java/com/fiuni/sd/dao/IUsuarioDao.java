package com.fiuni.sd.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.credenciales.UsuarioDomain;

@Repository
public interface IUsuarioDao extends JpaRepository<UsuarioDomain, Integer> {

	public Page<UsuarioDomain> findAll(Pageable pageable);

	public Optional<UsuarioDomain> findByEmail(String email);

}

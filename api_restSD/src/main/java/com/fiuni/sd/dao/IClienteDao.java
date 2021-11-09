package com.fiuni.sd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fiuni.sd.domain.cliente.ClienteDomain;

public interface IClienteDao extends JpaRepository<ClienteDomain, Integer> {
	public Page<ClienteDomain> findAll(Pageable pageable);
}

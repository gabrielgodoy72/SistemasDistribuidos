package com.fiuni.sd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.cliente.ClienteDomain;

@Repository
public interface IClienteDao extends JpaRepository<ClienteDomain, Integer> {
	public Page<ClienteDomain> findAll(Pageable pageable);
}

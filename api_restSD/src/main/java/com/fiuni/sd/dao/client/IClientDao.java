package com.fiuni.sd.dao.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fiuni.sd.domain.client.ClientDomain;

public interface IClientDao extends JpaRepository<ClientDomain, Integer> {
	public Page<ClientDomain> findAll(Pageable pageable);
}

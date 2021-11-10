package com.fiuni.sd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.producto.ProductoDomain;

@Repository
public interface IProductoDao extends JpaRepository<ProductoDomain, Integer> {
	public Page<ProductoDomain> findAll(Pageable pageable);
}
package com.fiuni.sd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fiuni.sd.domain.producto.ProductoDomain;

public interface IProductoDao extends JpaRepository<ProductoDomain, Integer> {
	public Page<ProductoDomain> findAll(Pageable pageable);
}
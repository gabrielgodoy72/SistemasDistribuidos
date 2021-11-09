package com.fiuni.sd.dao.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.credentials.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {
	public Page<User> findAll(Pageable pageable);
}

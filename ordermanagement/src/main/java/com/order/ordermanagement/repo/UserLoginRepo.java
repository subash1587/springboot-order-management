package com.order.ordermanagement.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermanagement.entity.UserLoginEntity;

public interface UserLoginRepo extends JpaRepository<UserLoginEntity, Integer>{
	
	UserLoginEntity findByUsernameAndPassword(String username, String password);

	UserLoginEntity findByToken(String token);

	UserLoginEntity findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
}

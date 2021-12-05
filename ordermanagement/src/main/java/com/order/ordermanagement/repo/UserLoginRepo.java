package com.order.ordermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermanagement.entity.UserLoginEntity;

public interface UserLoginRepo extends JpaRepository<UserLoginEntity, Integer>{
	
	UserLoginEntity findByUserNameAndPassword(String userName, String password);

	UserLoginEntity findByToken(String token);

	UserLoginEntity findByUserName(String userName);

	void deleteByUserName(String value);
}

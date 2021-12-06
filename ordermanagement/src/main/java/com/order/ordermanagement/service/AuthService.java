package com.order.ordermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.entity.UserLoginEntity;
import com.order.ordermanagement.model.UserLoginModel;
import com.order.ordermanagement.repo.UserLoginRepo;

@Service
public class AuthService {
	
	@Autowired
	UserLoginRepo userLoginRepo;

	public String login(UserLoginModel userLoginModel) {
		UserLoginEntity userLoginEntity = userLoginRepo.findByUsernameAndPassword(userLoginModel.getUsername(), userLoginModel.getPassword());
		return userLoginEntity.getToken();
	}

	public boolean validateToken(String token) {
		UserLoginEntity userLoginEntity = userLoginRepo.findByToken(token);
		if(userLoginEntity == null) {
			return false;
		}
		return true;
	}

}

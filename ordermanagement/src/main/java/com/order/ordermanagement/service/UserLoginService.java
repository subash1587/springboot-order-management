package com.order.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.UserError;
import com.order.ordermanagement.entity.UserLoginEntity;
import com.order.ordermanagement.mapper.UserLoginMapper;
import com.order.ordermanagement.model.UserLoginModel;
import com.order.ordermanagement.repo.UserLoginRepo;

@Service
public class UserLoginService {
	
	@Autowired
	UserLoginMapper userLoginMapper;
	
	@Autowired
	UserLoginRepo userLoginRepo;

	public void addUser(UserLoginModel userLoginModel) {
		
		UserLoginEntity userLoginEntity = userLoginRepo.findByUserName(userLoginModel.getUserName());
		if(userLoginEntity == null) {
			userLoginRepo.save(userLoginMapper.convertUserLoginModelToUserLoginEntity(userLoginModel));
		}else {
			throw new ApiException(UserError.USER_EXISTS);
		}
	}

	public List<UserLoginModel> getAllUser() {
		List<UserLoginEntity> userLoginEntityList = userLoginRepo.findAll();
		return userLoginMapper.convertUserLoginEntityListToUserLoginModelList(userLoginEntityList);
	}

	public UserLoginModel getUserByID(int id) {
		UserLoginEntity userLoginEntity = userLoginRepo.findById(id).orElseThrow();
		return userLoginMapper.convertUserLoginEntityToUserLoginModel(userLoginEntity);
	}

	public void deleteUserById(int id) {
		userLoginRepo.deleteById(id);
	}

	public void updateUser(int id, UserLoginModel userLoginModel) {
		UserLoginEntity userLoginEntity = userLoginRepo.findById(id).orElseThrow();
		if(!(userLoginEntity.getUserName().equals(userLoginModel.getUserName()))){
			userLoginEntity.setUserName(userLoginModel.getUserName());
		}
		if(!(userLoginEntity.getPassword().equals(userLoginModel.getPassword()))){
			userLoginEntity.setPassword(userLoginModel.getPassword());
		}
		if(!(userLoginEntity.getRole().equals(userLoginModel.getRole()))){
			userLoginEntity.setRole(userLoginModel.getRole());
		}
		userLoginRepo.save(userLoginEntity);
	}

}

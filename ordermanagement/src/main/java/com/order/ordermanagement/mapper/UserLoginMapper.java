package com.order.ordermanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.order.ordermanagement.entity.UserLoginEntity;
import com.order.ordermanagement.model.UserLoginModel;

@Component
public class UserLoginMapper {

	public UserLoginEntity convertUserLoginModelToUserLoginEntity(UserLoginModel userLoginModel) {
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		userLoginEntity.setUserName(userLoginModel.getUserName());
		userLoginEntity.setPassword(userLoginModel.getPassword());
		userLoginEntity.setRole(userLoginModel.getRole());
		userLoginEntity.setToken("a1b2c3d4e5");
		return userLoginEntity ;	
	}
	
	public UserLoginModel convertUserLoginEntityToUserLoginModel(UserLoginEntity userLoginEntity) {
		UserLoginModel userLoginModel = new UserLoginModel();
		userLoginModel.setId(userLoginEntity.getId());
		userLoginModel.setUserName(userLoginEntity.getUserName());
		userLoginModel.setPassword(userLoginEntity.getPassword());
		userLoginModel.setRole(userLoginEntity.getRole());
		return userLoginModel;	
	}

	public List<UserLoginModel> convertUserLoginEntityListToUserLoginModelList(
			List<UserLoginEntity> userLoginEntityList) {
		List<UserLoginModel> userLoginModelList = new ArrayList<>();
		for(UserLoginEntity userLoginEntity : userLoginEntityList) {
			userLoginModelList.add(convertUserLoginEntityToUserLoginModel(userLoginEntity));
		}
		return userLoginModelList;
	}
}

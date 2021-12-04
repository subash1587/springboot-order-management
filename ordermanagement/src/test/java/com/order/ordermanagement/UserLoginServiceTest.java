package com.order.ordermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.order.ordermanagement.entity.UserLoginEntity;
import com.order.ordermanagement.mapper.UserLoginMapper;
import com.order.ordermanagement.model.UserLoginModel;
import com.order.ordermanagement.repo.UserLoginRepo;
import com.order.ordermanagement.service.UserLoginService;

@ExtendWith(MockitoExtension.class)
public class UserLoginServiceTest {
	
	@InjectMocks
	UserLoginService userLoginService;
	
	@Mock
	UserLoginRepo userLoginRepo;
	
	@Mock
	UserLoginMapper userLoginMapper;
	
	@Test
	public void addUserTest() {
		UserLoginEntity user = new UserLoginEntity();
		user.setId(3);
		user.setUserName("guest");
		user.setPassword("guest");
		user.setRole("guest");
		user.setToken("aaa");
		
		UserLoginModel userModel = new UserLoginModel();
		userModel.setId(3);
		userModel.setUserName("guest");
		userModel.setPassword("guest");
		userModel.setRole("guest");
		userModel.setToken("aaa");
		
		Mockito.when(userLoginRepo.findByUserName(Mockito.any())).thenReturn(null);
		Mockito.when(userLoginMapper.convertUserLoginModelToUserLoginEntity(userModel)).thenReturn(user);
		
		userLoginService.addUser(userModel);
		verify(userLoginRepo, times(1)).save(user);
	}
	
	@Test
	public void getAllUserTest() {
		
		List<UserLoginEntity> userList = new ArrayList<>();
		UserLoginEntity user = new UserLoginEntity();
		user.setId(2);
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRole("admin");
		user.setToken("a1b2c3d4e5");
		userList.add(user);
		
		List<UserLoginModel> userModelList = new ArrayList<>();
		UserLoginModel userModel = new UserLoginModel();
		userModel.setId(2);
		userModel.setUserName("admin");
		userModel.setPassword("admin");
		userModel.setRole("admin");
		userModel.setToken("a1b2c3d4e5");
		userModelList.add(userModel);
		
		Mockito.when(userLoginRepo.findAll()).thenReturn(userList);
		Mockito.when(userLoginMapper.convertUserLoginEntityListToUserLoginModelList(userList)).thenReturn(userModelList);
		
		userLoginService.getAllUser();
		
		assertEquals(1, userModelList.size());
	}
	
	@Test
	public void getUserByIdTest() {
		UserLoginEntity user = new UserLoginEntity();
		user.setId(2);
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRole("admin");
		user.setToken("a1b2c3d4e5");
		
		UserLoginModel userModel = new UserLoginModel();
		userModel.setId(2);
		userModel.setUserName("admin");
		userModel.setPassword("admin");
		userModel.setRole("admin");
		userModel.setToken("a1b2c3d4e5");
		
		Mockito.when(userLoginRepo.findById(2)).thenReturn(Optional.of(user));
		Mockito.when(userLoginMapper.convertUserLoginEntityToUserLoginModel(user)).thenReturn(userModel);
		
		userLoginService.getUserByID(2);
		assertEquals("admin",userModel.getUserName());
		assertEquals("admin",userModel.getPassword());
		assertEquals("admin",userModel.getRole());
		assertEquals("a1b2c3d4e5",userModel.getToken());

	}
	
	@Test
	public void updateUserTest() {
		UserLoginEntity user = new UserLoginEntity();
		user.setId(2);
		user.setUserName("admin1");
		user.setPassword("admin");
		user.setRole("admin");
		user.setToken("a1b2c3d4e5");
		
		UserLoginModel userModel = new UserLoginModel();
		userModel.setId(2);
		userModel.setUserName("admin");
		userModel.setPassword("admin");
		userModel.setRole("admin");
		userModel.setToken("a1b2c3d4e5");
		
		Mockito.when(userLoginRepo.findByUserName(Mockito.any())).thenReturn(null);
		Mockito.when(userLoginMapper.convertUserLoginModelToUserLoginEntity(userModel)).thenReturn(user);
		
		userLoginService.updateUser(2,userModel);
		verify(userLoginRepo, times(1)).save(user);
	}
	
	@Test
	public void deleteUserTest() {
		UserLoginEntity user = new UserLoginEntity();
		user.setId(2);
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRole("admin");
		user.setToken("a1b2c3d4e5");
		
		Mockito.when(userLoginRepo.findById(2)).thenReturn(Optional.of(user));
		verify(userLoginRepo, times(1)).deleteById(2);
	}

}

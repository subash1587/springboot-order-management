package com.order.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.UserLoginModel;
import com.order.ordermanagement.service.AuthService;

@RestController
public class AuthController {

	@Autowired
	AuthService authService;
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserLoginModel userLoginModel){
		String token = authService.login(userLoginModel);
		return ResponseEntity.ok(token);
	}
}

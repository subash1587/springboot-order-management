package com.order.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.UserLoginModel;
import com.order.ordermanagement.service.UserLoginService;

@RestController
public class UserLoginController {

	@Autowired
	UserLoginService userLoginService;
	
	@RequestMapping(path="/user", method=RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody UserLoginModel userLoginModel){
		userLoginService.addUser(userLoginModel);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public ResponseEntity<?> getAllUser(){
		return ResponseEntity.ok(userLoginService.getAllUser());
	}
	
	@RequestMapping(path="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable("id") int id){
		return ResponseEntity.ok(userLoginService.getUserByID(id));
	}
	
	@RequestMapping(path="/user/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UserLoginModel userLoginModel){
		userLoginService.updateUser(id, userLoginModel);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/user", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserById(@RequestParam("id")int id){
		userLoginService.deleteUserById(id);
		return ResponseEntity.ok().build();
	}
}

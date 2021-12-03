package com.order.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(path="/customer", method=RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestBody CustomerModel customerModel){
		customerService.addCustomer(customerModel);
		return ResponseEntity.accepted().build();
	}
	
	@RequestMapping(path="/customer", method=RequestMethod.GET)
	public ResponseEntity<?> getAllCustomers(){
		List<CustomerModel> customerModelList = customerService.getAllCustomers();
		return ResponseEntity.ok(customerModelList);
	}
	
	@RequestMapping(path="/customer/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getCustomerById(@PathVariable("id") int id){
		CustomerModel customerModel = customerService.getCustomerById(id);
		return ResponseEntity.ok(customerModel);
	}

	@RequestMapping(path="/customer?address=city&value=abc", method=RequestMethod.GET)
	public ResponseEntity<?> getCustomerByAddress(@RequestParam("address") String address, @RequestParam("value") String value){
		List<CustomerModel> customerModelList = customerService.sortCustomer(address, value);
		return ResponseEntity.ok(customerModelList);
	}

	@RequestMapping(path="/customer/total-customer-per-city", method=RequestMethod.GET)
	public ResponseEntity<?> getCustomerCount(){
		return ResponseEntity.ok(customerService.getCustomerCount());
	}

	@RequestMapping(path="/customer?sort_by=name&order_by=asc", method=RequestMethod.GET)
	public ResponseEntity<?> sortCustomer(@RequestParam("sort_by") String sortBy, @RequestParam("order_by") String orderBy){
		List<CustomerModel> customerModelList = customerService.sortCustomer(sortBy, orderBy);
		return ResponseEntity.ok(customerModelList);
	}

	@RequestMapping(path="/customer/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerModel customerModel){
		customerService.updateCustomer(id, customerModel);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/customer/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id){
		customerService.deleteCustomer(id);
		return ResponseEntity.ok().build();
	}
	
}

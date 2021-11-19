package com.order.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.model.custom.CustomerCountPerCity;
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
	public ResponseEntity<?> getCustomer(@PathVariable("id") int id){
		CustomerModel customerModel = customerService.getCustomer(id);
		return ResponseEntity.ok(customerModel);
	}
	
	@RequestMapping(path="/customer/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerModel customerModel){
		customerService.updateCustomer(id, customerModel);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/customer/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id){
		customerService.deleteCustomer(id);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/customer/search/{address}", method=RequestMethod.GET)
	public ResponseEntity<?> getCustomerByAddress(@PathVariable("address") String address){
		List<CustomerModel> customerModelList = customerService.getCustomerByAddress(address);
		return ResponseEntity.ok(customerModelList);
	}
	
	@RequestMapping(path="/customer/address/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateCustomerAddress(@PathVariable("id") int id, @RequestBody CustomerModel customerModel){
		customerService.updateCustomerAddress(id, customerModel);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/customer/sort", method=RequestMethod.GET)
	public ResponseEntity<?> sortCustomerByName(){
		List<CustomerModel> customerModelList = customerService.sortCustomerByName();
		return ResponseEntity.ok(customerModelList);
	}
	
	@RequestMapping(path="/customer/sort/namesize", method=RequestMethod.GET)
	public ResponseEntity<?> sortCustomerByNameLength(){
		List<CustomerModel> customerModelList = customerService.sortCustomerByNameLength();
		return ResponseEntity.ok(customerModelList);
	}
	
	@RequestMapping(path="/customer/count", method=RequestMethod.GET)
	public ResponseEntity<?> getCustomerCount(){
		return ResponseEntity.ok(customerService.getCustomerCount());
	}

}

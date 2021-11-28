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

import com.order.ordermanagement.model.CustomerAddressModel;
import com.order.ordermanagement.service.CustomerAddressService;

@RestController
public class CustomerAddressController {

	@Autowired
	CustomerAddressService customerAddressService;
	
	@RequestMapping(path="/customer-address", method=RequestMethod.POST)
	public ResponseEntity<?> addCustomerAddress(@RequestBody CustomerAddressModel customerAddressModel){
		customerAddressService.addCustomerAddress(customerAddressModel);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/customer-address", method=RequestMethod.GET)
	public ResponseEntity<?> getAllCustomerAddress(){
		List<CustomerAddressModel> customerAddressList = customerAddressService.getAllCustomerAddress();
		return ResponseEntity.ok(customerAddressList);
	}

	@RequestMapping(path="/customer-address/{customerId}", method=RequestMethod.GET)
	public ResponseEntity<?> getCustomerAddress(@PathVariable("customerId") int customerId){
		List<CustomerAddressModel> customerAddressList = customerAddressService.getCustomerAddress(customerId);
		return ResponseEntity.ok(customerAddressList);
	}
	
	@RequestMapping(path="/customer-address/{customerId}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateCustomerAddress(@PathVariable("customerId") int customerId, @RequestParam String addressType, @RequestBody CustomerAddressModel customerAddressModel){
		customerAddressService.updateCustomerAddress(customerId, addressType, customerAddressModel);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/customer-address/{customerId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomerAddressByType(@PathVariable("customerId") int customerId, @RequestParam String addressType){
		customerAddressService.deleteCustomerAddressByType(customerId, addressType);
		return ResponseEntity.ok().build();
	}
	
//	@RequestMapping(path="/customer-address/{customerId}", method=RequestMethod.DELETE)
//	public ResponseEntity<?> deleteCustomerAddressByCustomer(@PathVariable("customerId") int customerId){
//		customerAddressService.deleteCustomerAddressByCustomer(customerId);
//		return ResponseEntity.ok().build();
//	}
}

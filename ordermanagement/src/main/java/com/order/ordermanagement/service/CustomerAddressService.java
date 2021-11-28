package com.order.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.entity.CustomerAddressEntity;
import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.mapper.CustomerAddressMapper;
import com.order.ordermanagement.model.CustomerAddressModel;
import com.order.ordermanagement.repo.CustomerAddressRepo;
import com.order.ordermanagement.repo.CustomerRepo;

@Service
public class CustomerAddressService {
	
	@Autowired
	CustomerAddressMapper customerAddressMapper;
	
	@Autowired
	CustomerAddressRepo customerAddressRepo;
	
	@Autowired
	CustomerRepo customerRepo;

	public void addCustomerAddress(CustomerAddressModel customerAddressModel) {
		customerAddressRepo.save(customerAddressMapper.convertCustomerAddressModelToCustomerAddressEntity(customerAddressModel));
	}

	public List<CustomerAddressModel> getAllCustomerAddress() {
		List<CustomerAddressEntity> customerAddressList = customerAddressRepo.findAll();
		return customerAddressMapper.convertCustomerAddressEntityListToCustomerAddressModelList(customerAddressList);
	}

	public List<CustomerAddressModel> getCustomerAddress(int customerId) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(customerId);
		List<CustomerAddressEntity> customerAddressList = customerAddressRepo.findByCustomerEntity(customerEntity);
		if(customerAddressList.size() == 0) {
			throw new ApiException(404,"NotFound","No address is found for the customer","Validation Error");
		}
		return customerAddressMapper.convertCustomerAddressEntityListToCustomerAddressModelList(customerAddressList);
	}
	
	public void updateCustomerAddress(int customerId, String addressType, CustomerAddressModel customerAddressModel) {
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(404,"NotFound","Customer is not found","Validation Error"));
		//CustomerEntity customerEntity = new CustomerEntity();
		//customerEntity.setId(customerId);
		CustomerAddressEntity customerAddressEntity	= customerAddressRepo.findByCustomerEntityAndAddressType(customerEntity, addressType);
		if(customerAddressEntity.getId() > 0) {
			customerAddressRepo.save(customerAddressMapper.mapCustomerAddressModelToCustomerAddressEntity(customerAddressModel, customerAddressEntity));
		}else {
			throw new ApiException(404,"NotFound","Customer Address type is not found","Validation Error");
		}
	}

	public void deleteCustomerAddressByType(int customerId, String addressType) {
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(404,"NotFound","Customer is not found","Validation Error"));
		CustomerAddressEntity customerAddressEntity	= customerAddressRepo.findByCustomerEntityAndAddressType(customerEntity, addressType);
		if(customerAddressEntity.getId() > 0) {
			customerAddressRepo.deleteById(customerAddressEntity.getId());
		}else {
			throw new ApiException(404,"NotFound","Customer Address type is not found","Validation Error");
		}
	}

	public void deleteCustomerAddressByCustomer(int customerId) {
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(404,"NotFound","Customer is not found","Validation Error"));
		List<CustomerAddressEntity> customerAddressList	= customerAddressRepo.findByCustomerEntity(customerEntity);
		if(customerAddressList.size() > 0) {
			customerAddressRepo.deleteById(customerAddressList.get(0).getId());
		}else {
			throw new ApiException(404,"NotFound","No address is found for the customer","Validation Error");
		}		
	}
}

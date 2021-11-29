package com.order.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.CustomerAddressError;
import com.order.ordermanagement.common.exception.CustomerError;
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
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(CustomerError.CUSTOMER_NOT_FOUND));
		List<CustomerAddressEntity> customerAddressList = customerAddressRepo.findByCustomerEntity(customerEntity);
		if(customerAddressList.size() == 0) {
			throw new ApiException(CustomerAddressError.CUSTOMER_ADDRESS_NOT_FOUND);
		}
		return customerAddressMapper.convertCustomerAddressEntityListToCustomerAddressModelList(customerAddressList);
	}
	
	public void updateCustomerAddress(int customerId, String addressType, CustomerAddressModel customerAddressModel) {
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(CustomerError.CUSTOMER_NOT_FOUND));
		CustomerAddressEntity customerAddressEntity	= customerAddressRepo.findByCustomerEntityAndAddressType(customerEntity, addressType);
		if(customerAddressEntity.getId() > 0) {
			customerAddressRepo.save(customerAddressMapper.mapCustomerAddressModelToCustomerAddressEntity(customerAddressModel, customerAddressEntity));
		}else {
			throw new ApiException(CustomerAddressError.CUSTOMER_ADDRESS_TYPE_NOT_FOUND);
		}
	}

	public void deleteCustomerAddress(int customerId, String addressType) {
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(CustomerError.CUSTOMER_NOT_FOUND));
		if(addressType == null) {
			List<CustomerAddressEntity> customerAddressList	= customerAddressRepo.findByCustomerEntity(customerEntity);
			if(customerAddressList.size() > 0) {
				customerAddressRepo.deleteByCustomerId(customerEntity.getId());
			}else {
				throw new ApiException(CustomerAddressError.CUSTOMER_ADDRESS_NOT_FOUND);
			}
		}else {
			CustomerAddressEntity customerAddressEntity	= customerAddressRepo.findByCustomerEntityAndAddressType(customerEntity, addressType);
			if(customerAddressEntity != null) {
				customerAddressRepo.deleteById(customerAddressEntity.getId());
			}else {
				throw new ApiException(CustomerAddressError.CUSTOMER_ADDRESS_TYPE_NOT_FOUND);
			}
		}
	}

	public void deleteCustomerAddressByCustomer(int customerId) {
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(CustomerError.CUSTOMER_NOT_FOUND));
		List<CustomerAddressEntity> customerAddressList	= customerAddressRepo.findByCustomerEntity(customerEntity);
		if(customerAddressList.size() > 0) {
			customerAddressRepo.deleteById(customerAddressList.get(0).getId());
		}else {
			throw new ApiException(CustomerAddressError.CUSTOMER_ADDRESS_NOT_FOUND);
		}		
	}
}

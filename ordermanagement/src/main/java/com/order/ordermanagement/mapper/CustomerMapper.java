package com.order.ordermanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.model.CustomerModel;

@Component
public class CustomerMapper {

	public CustomerEntity convertCustomerModelToCustomerEntity(CustomerModel customerModel) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(customerModel.getId());
		customerEntity.setName(customerModel.getName());
		customerEntity.setEmail(customerModel.getEmail());
		return customerEntity;
	}
	
	public CustomerModel convertCustomerEntityToCustomerModel(CustomerEntity customerEntity) {
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(customerEntity.getId());
		customerModel.setName(customerEntity.getName());
		customerModel.setEmail(customerEntity.getEmail());
		return customerModel;
	}

	public CustomerEntity mapCustomerModelToCustomerEntity(CustomerEntity customerEntity, CustomerModel customerModel) {
		if(customerModel.getName() != null) {
			customerEntity.setName(customerModel.getName());
		}
		if(customerModel.getEmail() != null) {
			customerEntity.setEmail(customerModel.getEmail());
		}
		return customerEntity;
	}
	
	
	public List<CustomerModel> convertCustomerEntityListToCustomerModelList(List<CustomerEntity> customerEntityList) {
		List<CustomerModel> customerModelList = new ArrayList<>();
		for(CustomerEntity customerEntity : customerEntityList) {
			customerModelList.add(convertCustomerEntityToCustomerModel(customerEntity));
		}
		return customerModelList;
	}
}

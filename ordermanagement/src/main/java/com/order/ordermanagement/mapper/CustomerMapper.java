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
		customerEntity.setAddress(customerModel.getAddress());
		return customerEntity;
	}
	
	public CustomerModel convertCustomerEntityToCustomerModel(CustomerEntity customerEntity) {
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(customerEntity.getId());
		customerModel.setName(customerEntity.getName());
		customerModel.setAddress(customerEntity.getAddress());
		return customerModel;
	}

	public CustomerEntity mapCustomerModelToCustomerEntity(CustomerEntity customerEntity, CustomerModel customerModel) {
		customerEntity.setAddress(customerModel.getAddress());
		customerEntity.setName(customerModel.getName());
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

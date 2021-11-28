package com.order.ordermanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.order.ordermanagement.entity.CustomerAddressEntity;
import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.model.CustomerAddressModel;

@Component
public class CustomerAddressMapper {

	public CustomerAddressEntity convertCustomerAddressModelToCustomerAddressEntity(CustomerAddressModel customerAddressModel) {
		CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
		customerAddressEntity.setAddressType(customerAddressModel.getAddressType());
		customerAddressEntity.setAddressLine1(customerAddressModel.getAddressLine1());
		customerAddressEntity.setAddressLine2(customerAddressModel.getAddressLine2());
		customerAddressEntity.setAddressLine3(customerAddressModel.getAddressLine3());
		customerAddressEntity.setCity(customerAddressModel.getCity());
		customerAddressEntity.setState(customerAddressModel.getState());
		customerAddressEntity.setPincode(customerAddressModel.getPincode());
		customerAddressEntity.setDefaultAddressInd(customerAddressModel.getDefaultAddressInd());
		CustomerEntity customer = new CustomerEntity();
		customer.setId(customerAddressModel.getCustomerId());
		customerAddressEntity.setCustomerEntity(customer);
		return customerAddressEntity;
	}
	
	public CustomerAddressModel convertCustomerAddressEntityToCustomerAddressModel(CustomerAddressEntity customerAddressEntity) {
		CustomerAddressModel customerAddressModel = new CustomerAddressModel();
		customerAddressModel.setId(customerAddressEntity.getId());
		customerAddressModel.setAddressType(customerAddressEntity.getAddressType());
		customerAddressModel.setAddressLine1(customerAddressEntity.getAddressLine1());
		customerAddressModel.setAddressLine2(customerAddressEntity.getAddressLine2());
		customerAddressModel.setAddressLine3(customerAddressEntity.getAddressLine3());
		customerAddressModel.setCity(customerAddressEntity.getCity());
		customerAddressModel.setState(customerAddressEntity.getState());
		customerAddressModel.setPincode(customerAddressEntity.getPincode());
		customerAddressModel.setDefaultAddressInd(customerAddressEntity.getDefaultAddressInd());
		customerAddressModel.setCustomerId(customerAddressEntity.getCustomerEntity().getId());
		return customerAddressModel;
	}

	public List<CustomerAddressModel> convertCustomerAddressEntityListToCustomerAddressModelList(
			List<CustomerAddressEntity> customerAddressEntityList) {
		List<CustomerAddressModel> customerAddressModelList = new ArrayList<>();
		for(CustomerAddressEntity customerAddress : customerAddressEntityList) {
			customerAddressModelList.add(convertCustomerAddressEntityToCustomerAddressModel(customerAddress));
		}
		return customerAddressModelList;
	}

	public CustomerAddressEntity mapCustomerAddressModelToCustomerAddressEntity(CustomerAddressModel customerAddressModel,
			CustomerAddressEntity customerAddressEntity) {
		customerAddressEntity.setAddressLine1(customerAddressModel.getAddressLine1());
		customerAddressEntity.setAddressLine2(customerAddressModel.getAddressLine2());
		customerAddressEntity.setAddressLine3(customerAddressModel.getAddressLine3());
		customerAddressEntity.setCity(customerAddressModel.getCity());
		customerAddressEntity.setState(customerAddressModel.getState());
		customerAddressEntity.setPincode(customerAddressModel.getPincode());
		customerAddressEntity.setDefaultAddressInd(customerAddressModel.getDefaultAddressInd());
		return customerAddressEntity;
	}
}

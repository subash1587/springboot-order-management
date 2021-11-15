package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.mapper.CustomerMapper;
import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	CustomerMapper customerMapper;
	
	public void addCustomer(CustomerModel customerModel) {
		CustomerEntity customerEntity = customerMapper.convertCustomerModelToCustomerEntity(customerModel);
		customerRepo.save(customerEntity);
	}

	public List<CustomerModel> getAllCustomers() {
		List<CustomerEntity> customerEntityList = customerRepo.findAll();
		List<CustomerModel> customerModelList = new ArrayList<>();
		for(CustomerEntity customerEntity : customerEntityList) {
			customerModelList.add(customerMapper.convertCustomerEntityToCustomerModel(customerEntity));
		}
		return customerModelList;
	}

	public CustomerModel getCustomer(int id) {
		CustomerEntity customerEntity = customerRepo.getById(id);
		return customerMapper.convertCustomerEntityToCustomerModel(customerEntity);
	}

	public void updateCustomer(int id, CustomerModel customerModel) {
		CustomerEntity customerEntity = customerRepo.getById(id);
		CustomerEntity updateCustomerEntity = customerMapper.mapCustomerModelToCustomerEntity(customerEntity, customerModel);
		customerRepo.save(updateCustomerEntity);
	}

	public void deleteCustomer(int id) {
		customerRepo.deleteById(id);
	}

	public List<CustomerModel> getCustomerByAddress(String address) {
		List<CustomerEntity> customerEntityList = customerRepo.findAllByAddress(address);
		List<CustomerModel> customerModelList = new ArrayList<>();
		for(CustomerEntity customerEntity : customerEntityList) {
			customerModelList.add(customerMapper.convertCustomerEntityToCustomerModel(customerEntity));
		}
		return customerModelList;
	}

	public void updateCustomerAddress(int id, CustomerModel customerModel) {
		CustomerEntity customerEntity = customerRepo.getById(id);
		customerEntity.setAddress(customerModel.getAddress());
		customerRepo.save(customerEntity);
	}

}

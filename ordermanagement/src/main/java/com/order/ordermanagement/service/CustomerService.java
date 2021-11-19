package com.order.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.mapper.CustomerMapper;
import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.model.custom.CustomerCountPerCity;
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
		return customerMapper.convertCustomerEntityListToCustomerModelList(customerEntityList);
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
		return customerMapper.convertCustomerEntityListToCustomerModelList(customerEntityList);
	}

	public void updateCustomerAddress(int id, CustomerModel customerModel) {
		CustomerEntity customerEntity = customerRepo.getById(id);
		customerEntity.setAddress(customerModel.getAddress());
		customerRepo.save(customerEntity);
	}

	public List<CustomerModel> sortCustomerByName() {
		List<CustomerEntity> customerEntityList = customerRepo.findAll(Sort.by(Sort.Direction.DESC, "name"));
		return customerMapper.convertCustomerEntityListToCustomerModelList(customerEntityList);
	}

	public List<CustomerModel> sortCustomerByNameLength() {
		List<CustomerEntity> customerEntityList = customerRepo.findAllCustomers(JpaSort.unsafe("LENGTH(name)"));
		return customerMapper.convertCustomerEntityListToCustomerModelList(customerEntityList);
	}
	
	public List<CustomerCountPerCity> getCustomerCount() {
		List<CustomerCountPerCity> customerCountList = customerRepo.findCustomerCountPerCity();
		return customerCountList;
	}
}

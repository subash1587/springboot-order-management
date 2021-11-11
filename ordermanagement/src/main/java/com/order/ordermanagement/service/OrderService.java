package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.mapper.CustomerMapper;
import com.order.ordermanagement.mapper.OrderMapper;
import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.repo.CustomerRepo;
import com.order.ordermanagement.repo.OrderRepo;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	CustomerMapper customerMapper;
	
	public void addOrder(OrderModel orderModel) {
		OrderEntity orderEntity = orderMapper.convertOrderModelToOrderEntity(orderModel);
		CustomerModel customerModel = orderModel.getCustomerModel();
		CustomerEntity customerEntity = customerRepo.getById(customerModel.getId());
		if(customerEntity == null) {
			customerRepo.save(customerMapper.convertCustomerModelToCustomerEntity(customerModel));
		}
		orderRepo.save(orderEntity);
	}
	
	public List<OrderModel> getAllOrder() {
		List<OrderEntity> orderEntityList = orderRepo.findAll();
		List<OrderModel> orderModelList = new ArrayList<>();
		for(OrderEntity orderEntity : orderEntityList) {
			orderModelList.add(orderMapper.convertOrderEntityToOrderModel(orderEntity ));
		}
		return orderModelList;
	}

	public OrderModel searchOrder(int orderId) {
		OrderEntity orderEntity = orderRepo.getById(orderId);
		OrderModel orderModel = orderMapper.convertOrderEntityToOrderModel(orderEntity);
		return orderModel;
	}
}

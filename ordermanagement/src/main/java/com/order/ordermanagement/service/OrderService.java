package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.mapper.CustomerMapper;
import com.order.ordermanagement.mapper.OrderItemMapper;
import com.order.ordermanagement.mapper.OrderMapper;
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
	
	@Autowired
	OrderItemMapper orderItemMapper;
	
	public void addOrder(OrderModel orderModel) {
		OrderEntity orderEntity = orderMapper.convertOrderModelToOrderEntity(orderModel);
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

	public List<OrderModel> getTopOrders() {
		return null;
	}

	public List<OrderModel> getOrdersByCustomer(int customerId) {
		return null;
	}

}

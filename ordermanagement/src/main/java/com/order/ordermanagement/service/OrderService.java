package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.common.exception.AppException;
import com.order.ordermanagement.common.exception.OrderError;
import com.order.ordermanagement.entity.CustomerEntity;
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
		return orderMapper.convertOrderEntityListToOrderModelList(orderEntityList);
	}

	public OrderModel searchOrder(int orderId) {
		OrderEntity orderEntity = orderRepo.findById(orderId).orElseThrow(()-> new AppException(OrderError.ORDER_NOT_FOUND));
		return orderMapper.convertOrderEntityToOrderModel(orderEntity);
	}
	
	public List<OrderModel> getOrdersByCustomer(int customerId) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(customerId);
		List<OrderEntity> orderEntityList = orderRepo.findAllByCustomerEntity(customerEntity);
		return orderMapper.convertOrderEntityListToOrderModelList(orderEntityList);
	}

	public List<OrderModel> getTopOrdersBySaleValue() {
		List<OrderModel> orderModelList = new ArrayList<>();
		int[] topOrder = orderRepo.findTopOrders(2);
		for(int i=0; i < topOrder.length; i++) {
			OrderEntity orderEntity = orderRepo.findById(topOrder[i]).orElseThrow(null);
			OrderModel orderModel = orderMapper.convertOrderEntityToOrderModel(orderEntity);
			orderModelList.add(orderModel);
		}
		return orderModelList;
	}

	public List<OrderModel> getOrdersWithPagination(int index) {
		Page<OrderEntity> orderEntityList = orderRepo.findOrdersWithPagination(PageRequest.of(index, 5));
		List<OrderModel> orderModelList = new ArrayList<>();
		for(OrderEntity order : orderEntityList) {
			OrderModel orderModel = orderMapper.convertOrderEntityToOrderModel(order);
			orderModelList.add(orderModel);
		}
		return orderModelList;
	}

	public void updateOrderDelivery(int id) {
		OrderEntity orderEntity = orderRepo.findById(id).orElseThrow(null);
		OrderEntity updatedOrder = orderMapper.updateOrderEntity(orderEntity);
		orderRepo.save(updatedOrder);
	}

}

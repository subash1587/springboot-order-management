package com.order.ordermanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.OrderError;
import com.order.ordermanagement.model.custom.OrderStatus;
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
		OrderEntity orderEntity = orderRepo.findById(orderId)
				.orElseThrow(()-> new ApiException(OrderError.ORDER_NOT_FOUND));
		return orderMapper.convertOrderEntityToOrderModel(orderEntity);
	}
	
	public List<OrderModel> getOrdersByCustomer(int customerId) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(customerId);
		List<OrderEntity> orderEntityList = orderRepo.findAllByCustomerEntity(customerEntity);
		if(orderEntityList.size() == 0) {
			throw new ApiException(OrderError.ORDER_NOT_FOUND_CUSTOMER);
		}
		return orderMapper.convertOrderEntityListToOrderModelList(orderEntityList);
	}

	public List<OrderModel> getTopOrdersBySaleValue() {
		List<OrderModel> orderModelList = new ArrayList<>();
		int[] topOrder = orderRepo.findTopOrders(2);
		for(int i=0; i < topOrder.length; i++) {
			OrderEntity orderEntity = orderRepo.findById(topOrder[i])
					.orElseThrow(()-> new ApiException(OrderError.ORDER_NOT_FOUND));
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

	public void updateOrder(int id, OrderStatus status) {
		OrderEntity orderEntity = orderRepo.findById(id)
				.orElseThrow(()-> new ApiException(OrderError.ORDER_NOT_FOUND));
		String orderStatus = orderEntity.getStatus();
		switch(status) {
			case ORDERED:
				throw new ApiException(OrderError.ORDER_STATUS_ERROR);
			case ACCEPTED:
				if(orderStatus.equals("ordered")) {
					orderEntity.setStatus(OrderStatus.ACCEPTED);
					orderEntity.setAcceptedDate(LocalDate.now());
				}else {
					throw new ApiException(OrderError.ORDER_STATUS_ERROR);
				}
				break;
			case PACKAGED:
				if(orderStatus.equals("accepted")) {
					orderEntity.setStatus(OrderStatus.PACKAGED);
					orderEntity.setPackagedDate(LocalDate.now());
				}else {
					throw new ApiException(OrderError.ORDER_STATUS_ERROR);
				}
				break;
			case CANCELLED:
				if(!(orderStatus.equals("shipped")||orderStatus.equals("delivered"))) {
					orderEntity.setStatus(OrderStatus.CANCELLED);
					orderEntity.setCancelledDate(LocalDate.now());
				}else {
					throw new ApiException(OrderError.ORDER_STATUS_ERROR);
				}
				break;
			case SHIPPED:
				if(orderStatus.equals("packaged")) {
					orderEntity.setStatus(OrderStatus.SHIPPED);
					orderEntity.setShippedDate(LocalDate.now());
				}else {
					throw new ApiException(OrderError.ORDER_STATUS_ERROR);
				}
				break;
			case DELIVERED:
				if(orderStatus.equals("shipped")) {
					orderEntity.setStatus(OrderStatus.DELIVERED);
					orderEntity.setActualDeliveryDate(LocalDate.now());
				}else {
					throw new ApiException(OrderError.ORDER_STATUS_ERROR);
				}
				break;
			default:
				throw new ApiException(OrderError.ORDER_STATUS_INVALID);
		}
		orderRepo.save(orderEntity);
	}
}

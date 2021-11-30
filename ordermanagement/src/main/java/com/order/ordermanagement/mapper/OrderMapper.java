package com.order.ordermanagement.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.CustomerError;
import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.entity.OrderItemEntity;
import com.order.ordermanagement.model.OrderItemModel;
import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.model.custom.OrderStatus;
import com.order.ordermanagement.repo.CustomerRepo;

@Component
public class OrderMapper {
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	OrderItemMapper orderItemMapper;
	
	@Autowired
	CustomerRepo customerRepo;
	
	public OrderModel convertOrderEntityToOrderModel(OrderEntity orderEntity){
		OrderModel orderModel = new OrderModel();
		CustomerEntity customerEntity = orderEntity.getCustomerEntity();
		List<OrderItemModel> orderItemModelList = orderItemMapper.convertOrderEntityToOrderItemModel(orderEntity);
		orderModel.setId(orderEntity.getId());
		orderModel.setCustomerId(customerEntity.getId());
		orderModel.setOrderItemList(orderItemModelList);
		orderModel.setStatus(orderEntity.getStatus());
		orderModel.setOrderDate(orderEntity.getOrderDate());
		orderModel.setAcceptedDate(orderEntity.getAcceptedDate());
		orderModel.setPackagedDate(orderEntity.getPackagedDate());
		orderModel.setShippedDate(orderEntity.getShippedDate());
		orderModel.setEstimatedDeliveryDate(orderEntity.getEstimatedDeliveryDate());
		orderModel.setActualDeliveryDate(orderEntity.getActualDeliveryDate());
		orderModel.setCancelledDate(orderEntity.getCancelledDate());
		return orderModel;
	}
	
	public OrderEntity convertOrderModelToOrderEntity(OrderModel orderModel) {
		int customerId= orderModel.getCustomerId();
		CustomerEntity customerEntity = customerRepo.findById(customerId)
				.orElseThrow(()-> new ApiException(CustomerError.CUSTOMER_NOT_FOUND));
		OrderEntity orderEntity = new OrderEntity();
		List<OrderItemEntity> orderItemEntityList = orderItemMapper.convertOrderModelToOrderItemEntity(orderModel, orderEntity);
		orderEntity.setCustomerEntity(customerEntity);		
		orderEntity.setOrderItemList(orderItemEntityList);
		orderEntity.setOrderDate(LocalDate.now());
		orderEntity.setEstimatedDeliveryDate(LocalDate.now().plusDays(3));
		orderEntity.setStatus(OrderStatus.ORDERED);
		return orderEntity;
	}

	public List<OrderModel> convertOrderEntityListToOrderModelList(List<OrderEntity> orderEntityList) {
		List<OrderModel> orderModelList = new ArrayList<>();
		for(OrderEntity orderEntity : orderEntityList) {
			orderModelList.add(convertOrderEntityToOrderModel(orderEntity));
		}
		return orderModelList;
	}

}

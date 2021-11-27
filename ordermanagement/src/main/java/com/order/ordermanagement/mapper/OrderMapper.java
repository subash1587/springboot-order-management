package com.order.ordermanagement.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.entity.OrderItemEntity;
import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.model.OrderItemModel;
import com.order.ordermanagement.model.OrderModel;
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
		CustomerModel customerModel = customerMapper.convertCustomerEntityToCustomerModel(customerEntity);
		List<OrderItemModel> orderItemModelList = orderItemMapper.convertOrderEntityToOrderItemModel(orderEntity);
		orderModel.setId(orderEntity.getId());
		orderModel.setCustomerModel(customerModel);
		orderModel.setOrderItemList(orderItemModelList);
		orderModel.setStatus(orderEntity.getStatus());
		orderModel.setOrderDate(orderEntity.getOrderDate());
		orderModel.setShippedDate(orderEntity.getShippedDate());
		orderModel.setEstimatedDeliveryDate(orderEntity.getEstimatedDeliveryDate());
		orderModel.setActualDeliveryDate(orderEntity.getActualDeliveryDate());
		orderModel.setCancelledDate(orderEntity.getCancelledDate());
		return orderModel;
	}
	
	public OrderEntity convertOrderModelToOrderEntity(OrderModel orderModel) {
		CustomerModel customerModel = orderModel.getCustomerModel();
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity = customerRepo.findById(customerModel.getId())
				.orElseThrow(()-> new ApiException(404,"Not Found","Customer is not found","Validation Error"));
		customerEntity = customerMapper.convertCustomerModelToCustomerEntity(customerModel);
		OrderEntity orderEntity = new OrderEntity();
		List<OrderItemEntity> orderItemEntityList = orderItemMapper.convertOrderModelToOrderItemEntity(orderModel, orderEntity);
		orderEntity.setCustomerEntity(customerEntity);		
		orderEntity.setOrderItemList(orderItemEntityList);
		orderEntity.setOrderDate(LocalDate.now());
		orderEntity.setEstimatedDeliveryDate(LocalDate.now().plusDays(3));
		orderEntity.setStatus("ordered");
		return orderEntity;
	}

	public OrderEntity updateOrderEntity(OrderEntity orderEntity, String status) {
		orderEntity.setStatus(status);
		if(status.equals("shipped")) {
			orderEntity.setShippedDate(LocalDate.now());
		}else if(status.equals("delivered")) {
			orderEntity.setActualDeliveryDate(LocalDate.now());
		}else if(status.equals("cancelled")) {
			orderEntity.setCancelledDate(LocalDate.now());
		}
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

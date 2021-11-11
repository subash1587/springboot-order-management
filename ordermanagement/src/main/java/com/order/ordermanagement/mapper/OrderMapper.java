package com.order.ordermanagement.mapper;

import org.springframework.stereotype.Component;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.model.OrderModel;

@Component
public class OrderMapper {
	
	public OrderModel convertOrderEntityToOrderModel(OrderEntity orderEntity){
		OrderModel orderModel = new OrderModel();
		CustomerModel customerModel = new CustomerModel();
		CustomerEntity customerEntity = orderEntity.getCustomerEntity();
		customerModel.setId(customerEntity.getId());
		customerModel.setName(customerEntity.getName());
		customerModel.setAddress(customerEntity.getAddress());
		orderModel.setId(orderEntity.getId());
		orderModel.setCustomerModel(customerModel);
		return orderModel;
	}
	
	public OrderEntity convertOrderModelToOrderEntity(OrderModel orderModel) {
		OrderEntity orderEntity = new OrderEntity();
		CustomerEntity customerEntity = new CustomerEntity();
		CustomerModel customerModel = orderModel.getCustomerModel();
		customerEntity.setId(customerModel.getId());
		customerEntity.setName(customerModel.getName());
		customerEntity.setAddress(customerModel.getAddress());
		orderEntity.setId(orderModel.getId());
		orderEntity.setCustomerEntity(customerEntity);
		return orderEntity;
	}

}

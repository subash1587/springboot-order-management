package com.order.ordermanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.entity.OrderItemEntity;
import com.order.ordermanagement.model.CustomerModel;
import com.order.ordermanagement.model.OrderItemModel;
import com.order.ordermanagement.model.OrderModel;

@Component
public class OrderMapper {
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	OrderItemMapper orderItemMapper;
	
	public OrderModel convertOrderEntityToOrderModel(OrderEntity orderEntity){
		OrderModel orderModel = new OrderModel();
		CustomerEntity customerEntity = orderEntity.getCustomerEntity();
		CustomerModel customerModel = customerMapper.convertCustomerEntityToCustomerModel(customerEntity);
		List<OrderItemEntity> orderItemList = orderEntity.getOrderItemList();
		List<OrderItemModel> orderItemModelList = orderItemMapper.convertOrderEntityToOrderItemModel(orderEntity);
		orderModel.setId(orderEntity.getId());
		orderModel.setCustomerModel(customerModel);
		orderModel.setOrderItemList(orderItemModelList);
		return orderModel;
	}
	
	public OrderEntity convertOrderModelToOrderEntity(OrderModel orderModel) {
		CustomerModel customerModel = orderModel.getCustomerModel();	
		CustomerEntity customerEntity = customerMapper.convertCustomerModelToCustomerEntity(customerModel);
		OrderEntity orderEntity = new OrderEntity();
		List<OrderItemEntity> orderItemEntityList = orderItemMapper.convertOrderModelToOrderItemEntity(orderModel, orderEntity);
		orderEntity.setCustomerEntity(customerEntity);		
		orderEntity.setOrderItemList(orderItemEntityList);
		return orderEntity;
	}

}

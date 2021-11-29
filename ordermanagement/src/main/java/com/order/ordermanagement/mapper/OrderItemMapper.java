package com.order.ordermanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.ItemError;
import com.order.ordermanagement.entity.ItemEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.entity.OrderItemEntity;
import com.order.ordermanagement.model.OrderItemModel;
import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.repo.ItemRepo;

@Component
public class OrderItemMapper {
	
	@Autowired
	ItemRepo itemRepo;

	public List<OrderItemEntity> convertOrderModelToOrderItemEntity(OrderModel orderModel, OrderEntity orderEntity) {
		List<OrderItemModel> orderItemModelList =  orderModel.getOrderItemList();
		List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
		for(OrderItemModel orderItem : orderItemModelList) {
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			ItemEntity item = itemRepo.findById(orderItem.getItemId())
					.orElseThrow(()-> new ApiException(ItemError.ITEM_NOT_FOUND));
			orderItemEntity.setOrder(orderEntity);
			orderItemEntity.setItem(item);
			orderItemEntity.setItemCount(orderItem.getItemCount());
			orderItemEntityList.add(orderItemEntity);
		}
		return orderItemEntityList;
	}

	public List<OrderItemModel> convertOrderEntityToOrderItemModel(OrderEntity orderEntity) {
		List<OrderItemModel> orderItemModelList = new ArrayList<>();
		List<OrderItemEntity> orderItemEntityList = orderEntity.getOrderItemList();
		for(OrderItemEntity orderItem : orderItemEntityList) {
			OrderItemModel orderItemModel = new OrderItemModel();
			orderItemModel.setId(orderItem.getId());
			orderItemModel.setItemId(orderItem.getItem().getId());
			orderItemModel.setOrderId(orderItem.getOrder().getId());
			orderItemModel.setItemCount(orderItem.getItemCount());
			orderItemModelList.add(orderItemModel);
		}
		return orderItemModelList;
	}
}

package com.order.ordermanagement.mapper;

import org.springframework.stereotype.Component;

import com.order.ordermanagement.entity.ItemEntity;
import com.order.ordermanagement.model.ItemModel;

@Component
public class ItemMapper {

	public ItemEntity convertItemModelToItemEntity(ItemModel itemModel) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(itemModel.getId());
		itemEntity.setName(itemModel.getName());
		itemEntity.setPrice(itemModel.getPrice());
		return itemEntity;
	}
	
	public ItemModel convertItemEntityToItemModel(ItemEntity itemEntity) {
		ItemModel itemModel = new ItemModel();
		itemModel.setId(itemEntity.getId());
		itemModel.setName(itemEntity.getName());
		itemModel.setPrice(itemEntity.getPrice());
		return itemModel;
	}

	public ItemEntity mapItemModelToItemEntity(ItemModel itemModel, ItemEntity itemEntity) {
		itemEntity.setName(itemModel.getName());
		itemEntity.setPrice(itemModel.getPrice());
		return itemEntity;
	}
}

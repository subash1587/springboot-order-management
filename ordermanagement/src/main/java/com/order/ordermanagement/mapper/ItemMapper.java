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
		itemEntity.setCategory(itemModel.getCategory());
		itemEntity.setRating(itemModel.getRating());
		return itemEntity;
	}
	
	public ItemModel convertItemEntityToItemModel(ItemEntity itemEntity) {
		ItemModel itemModel = new ItemModel();
		itemModel.setId(itemEntity.getId());
		itemModel.setName(itemEntity.getName());
		itemModel.setPrice(itemEntity.getPrice());
		itemModel.setCategory(itemEntity.getCategory());
		itemModel.setRating(itemEntity.getRating());
		return itemModel;
	}

	public ItemEntity mapItemModelToItemEntity(ItemModel itemModel, ItemEntity itemEntity) {
		if(itemModel.getName() != null) {
			itemEntity.setName(itemModel.getName());
		}
		if (itemModel.getPrice() >= 0) {
			itemEntity.setPrice(itemModel.getPrice());
		}
		if (itemModel.getCategory() != null) {
			itemEntity.setCategory(itemModel.getCategory());
		}
		if(itemModel.getRating() >= 0) {
			itemEntity.setRating(itemModel.getRating());
		}
		return itemEntity;
	}
}

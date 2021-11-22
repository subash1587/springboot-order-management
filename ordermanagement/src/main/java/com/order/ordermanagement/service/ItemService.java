package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.entity.ItemEntity;
import com.order.ordermanagement.mapper.ItemMapper;
import com.order.ordermanagement.model.ItemModel;
import com.order.ordermanagement.repo.ItemRepo;

@Service
public class ItemService {

	@Autowired
	ItemRepo itemRepo;
	
	@Autowired
	ItemMapper itemMapper;
	
	public void addItem(ItemModel itemModel) {
		ItemEntity itemEntity = itemMapper.convertItemModelToItemEntity(itemModel);
		itemRepo.save(itemEntity);		
	}

	public List<ItemModel> getAllItems() {
		List<ItemEntity> itemEntityList = itemRepo.findAll();
		List<ItemModel> itemModelList = new ArrayList<>();
		for(ItemEntity itemEntity : itemEntityList) {
			itemModelList.add(itemMapper.convertItemEntityToItemModel(itemEntity));
		}
		return itemModelList;
	}

	public ItemModel getItem(int itemId) {
		ItemEntity itemEntity = itemRepo.getById(itemId);
		ItemModel itemModel = itemMapper.convertItemEntityToItemModel(itemEntity);
		return itemModel;
	}
	
	public List<ItemModel> sortItemByPrice() {
		Page<ItemEntity> itemEntityList = itemRepo.findAll(PageRequest.of(0, 10, Sort.by(Direction.DESC, "price")));
		List<ItemModel> itemModelList = new ArrayList<>();
		for(ItemEntity itemEntity : itemEntityList) {
			itemModelList.add(itemMapper.convertItemEntityToItemModel(itemEntity));
		}
		return itemModelList;
	}

	public List<ItemModel> getItemsWithPagination(int index) {
		Page<ItemEntity> itemEntityList = itemRepo.findItemWithPagination(PageRequest.of(index, 5, Sort.by(Direction.ASC, "price")));
		List<ItemModel> itemModelList = new ArrayList<>();
		for(ItemEntity itemEntity : itemEntityList) {
			itemModelList.add(itemMapper.convertItemEntityToItemModel(itemEntity));
		}
		return itemModelList;
	}
	
	public ItemModel getItemByNameAndId(String itemName, int itemId) {
		ItemEntity itemEntity = itemRepo.findItemByNameAndID(itemName, itemId);
		ItemModel itemModel = itemMapper.convertItemEntityToItemModel(itemEntity);
		return itemModel;
	}
	
	public void updateItem(int itemId, ItemModel itemModel) {
		ItemEntity itemEntity = itemRepo.findById(itemId).orElseThrow(null);
	  	ItemEntity updatedItemEntity = itemMapper.mapItemModelToItemEntity(itemModel,itemEntity);
	  	itemRepo.save(updatedItemEntity);	  
	}
	 
	public List<ItemModel> getItemsWithHigherPrice(double price) {
		List<ItemEntity> itemEntityList = itemRepo.findItemsWithPrice(price);
		List<ItemModel> itemModelList = new ArrayList<>();
		for(ItemEntity itemEntity : itemEntityList) {
			itemModelList.add(itemMapper.convertItemEntityToItemModel(itemEntity));
		}
		return itemModelList;
	}
	
	public List<ItemModel> getItemsByNameList() {
		List<String> names = Arrays.asList("item1","item2","item3");
		List<ItemEntity> itemEntityList = itemRepo.findByNameList(names);
		List<ItemModel> itemModelList = new ArrayList<>();
		for(ItemEntity itemEntity : itemEntityList) {
			itemModelList.add(itemMapper.convertItemEntityToItemModel(itemEntity));
		}
		return itemModelList;
	}
	
	public void updateItemName(int id, String name) {
		itemRepo.updateItemName(name, id);
	}
	
	public void insertItem(ItemModel itemModel) {
		itemRepo.insertItem(itemModel.getName(), itemModel.getPrice());
	}
	
	public void deleteItem(int itemId) {
		itemRepo.deleteById(itemId);
	}

	public List<ItemModel> searchItemsWithFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}

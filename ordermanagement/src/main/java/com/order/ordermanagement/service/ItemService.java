package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

	public List<ItemModel> getItems(String sortBy, String orderBy, Integer index) {
		List<ItemEntity> itemEntityList = new ArrayList<>();
		Page<ItemEntity> itemEntityPage = null;
		if(sortBy == null && orderBy == null) {
			if (index == null) {
				itemEntityList = itemRepo.findAll();
			} else {
				itemEntityPage = itemRepo.findAll(PageRequest.of(index, 10));
				for(ItemEntity itemEntity : itemEntityPage) {
					itemEntityList.add(itemEntity);
				}
			}
		} else {
			if (index == null) {
				itemEntityList = itemRepo.sortItems(sortBy);
			} else {
				itemEntityPage = itemRepo.findAll(PageRequest.of(index, 10, Sort.by(Direction.DESC, sortBy)));
				for(ItemEntity itemEntity : itemEntityPage) {
					itemEntityList.add(itemEntity);
				}
			}
		}
		return itemMapper.convertItemEntityListToItemModelList(itemEntityList);
	}

	public ItemModel getItem(int itemId) {
		ItemEntity itemEntity = itemRepo.getById(itemId);
		return itemMapper.convertItemEntityToItemModel(itemEntity);
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
		return itemMapper.convertItemEntityToItemModel(itemEntity);
	}
	
	public void updateItem(int itemId, ItemModel itemModel) {
		ItemEntity itemEntity = itemRepo.findById(itemId).orElseThrow(null);
	  	ItemEntity updatedItemEntity = itemMapper.mapItemModelToItemEntity(itemModel,itemEntity);
	  	itemRepo.save(updatedItemEntity);	  
	}
	 
	public List<ItemModel> getItemsWithHigherPrice(double price) {
		List<ItemEntity> itemEntityList = itemRepo.findItemsWithPrice(price);
		return itemMapper.convertItemEntityListToItemModelList(itemEntityList);
	}
	
	public List<ItemModel> getItemsByNameList() {
		List<String> names = Arrays.asList("Cashew 100g","Dark Fantacy");
		List<ItemEntity> itemEntityList = itemRepo.findByNameList(names);
		return itemMapper.convertItemEntityListToItemModelList(itemEntityList);
	}

	public List<ItemModel> searchItemsWithFilter(Map<String, String> filterMap) {
		List<ItemEntity> itemEntityList = itemRepo.findItem(filterMap);
		return itemMapper.convertItemEntityListToItemModelList(itemEntityList);
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

}

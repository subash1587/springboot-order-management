package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.common.exception.ApiException;
import com.order.ordermanagement.common.exception.ItemError;
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

	public List<ItemModel> getItems(String sortBy, String orderBy, Map<String, String> filterMap, Integer index) {
		List<ItemEntity> itemEntityList = new ArrayList<>();
		Page<ItemEntity> itemEntityPage = null;
		
		if(sortBy == null && orderBy == null) {
			if (index == null && filterMap == null) {
				itemEntityList = itemRepo.findAll();
			} else if(index != null && filterMap == null){
				itemEntityPage = itemRepo.findAll(PageRequest.of(index, 10));
				for(ItemEntity itemEntity : itemEntityPage) {
					itemEntityList.add(itemEntity);
				}
			} else {
				itemEntityList = itemRepo.findItem(filterMap);
			} 
		} else {
			if (index == null) {
				itemEntityList = sortItemsWithoutPagination(sortBy, orderBy);
			} else {
				itemEntityList = sortItemsWithPagination(sortBy, orderBy, index);
			}
		}
		return itemMapper.convertItemEntityListToItemModelList(itemEntityList);
	}

	public List<ItemEntity> sortItemsWithoutPagination(String sortBy, String orderBy) {
		List<ItemEntity> itemEntityList = new ArrayList<>();
		switch(sortBy) {
		case "name":
			if (orderBy != null && orderBy.equalsIgnoreCase("desc")) {
				itemEntityList = itemRepo.findAllByOrderByNameDesc();
			}else if(orderBy == null || orderBy.equalsIgnoreCase("asc")){
				itemEntityList = itemRepo.findAllByOrderByNameAsc();
			}else {
				throw new ApiException(ItemError.ITEM_BAD_REQUEST);
			}
			break;
		case "price":
			if (orderBy != null && orderBy.equalsIgnoreCase("desc")) {
				itemEntityList = itemRepo.findAllByOrderByPriceDesc();
			}else if(orderBy == null || orderBy.equalsIgnoreCase("asc")) {
				itemEntityList = itemRepo.findAllByOrderByPriceAsc();
			}else {
				throw new ApiException(ItemError.ITEM_BAD_REQUEST);
			}
			break;
		case "category":
			if (orderBy != null && orderBy.equalsIgnoreCase("desc")) {
				itemEntityList = itemRepo.findAllByOrderByCategoryDesc();
			}else if(orderBy == null || orderBy.equalsIgnoreCase("asc")){
				itemEntityList = itemRepo.findAllByOrderByCategoryAsc();
			}else {
				throw new ApiException(ItemError.ITEM_BAD_REQUEST);
			}
			break;
		case "rating":
			if (orderBy != null && orderBy.equalsIgnoreCase("desc")) {
				itemEntityList = itemRepo.findAllByOrderByRatingDesc();
			}else if(orderBy == null || orderBy.equalsIgnoreCase("asc")){
				itemEntityList = itemRepo.findAllByOrderByRatingAsc();
			}else {
				throw new ApiException(ItemError.ITEM_BAD_REQUEST);
			}
			break;
		default:
			throw new ApiException(ItemError.ITEM_INVALID_SORT_PARAMETER);
		}
		return itemEntityList;
	}

	public List<ItemEntity> sortItemsWithPagination(String sortBy, String orderBy, Integer index) {
		List<ItemEntity> itemEntityList = new ArrayList<>();
		Page<ItemEntity> itemEntityPage = null;
		if (orderBy != null && orderBy.equalsIgnoreCase("desc")) {
			itemEntityPage = itemRepo.findAll(PageRequest.of(index, 5, Sort.by(Sort.Direction.DESC, sortBy)));
		}else if(orderBy == null || orderBy.equalsIgnoreCase("asc")){
			itemEntityPage = itemRepo.findAll(PageRequest.of(index, 5, Sort.by(Sort.Direction.ASC, sortBy)));
		}else {
			throw new ApiException(ItemError.ITEM_BAD_REQUEST);
		}
		for(ItemEntity itemEntity : itemEntityPage) {
			itemEntityList.add(itemEntity);
		}
		return itemEntityList;
	}
	
	public ItemModel getItem(int itemId) {
		ItemEntity itemEntity = itemRepo.findById(itemId).orElseThrow(()->new ApiException(ItemError.ITEM_NOT_FOUND));
		return itemMapper.convertItemEntityToItemModel(itemEntity);
	}
	
	public List<ItemModel> getItemsByNameList(List<String> names) {
		List<ItemEntity> itemEntityList = itemRepo.findByNameList(names);
		if(itemEntityList == null) {
			throw new ApiException(ItemError.ITEM_NOT_FOUND);
		}
		return itemMapper.convertItemEntityListToItemModelList(itemEntityList);
	}
	
	public void updateItem(int itemId, String itemName, ItemModel itemModel) {
		ItemEntity itemEntity = itemRepo.findById(itemId).orElseThrow(()->new ApiException(ItemError.ITEM_NOT_FOUND));
		if(itemName == null) {
		  	ItemEntity updatedItemEntity = itemMapper.mapItemModelToItemEntity(itemModel,itemEntity);
		  	itemRepo.save(updatedItemEntity);	  
		} else {
			itemRepo.updateItemName(itemName, itemId);
		}
	}

	public void deleteItem(int itemId) {
		itemRepo.findById(itemId).orElseThrow(()->new ApiException(ItemError.ITEM_NOT_FOUND));
		itemRepo.deleteById(itemId);
	}

}

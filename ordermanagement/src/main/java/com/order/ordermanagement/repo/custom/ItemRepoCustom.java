package com.order.ordermanagement.repo.custom;

import java.util.List;
import java.util.Map;

import com.order.ordermanagement.entity.ItemEntity;

public interface ItemRepoCustom {

	List<ItemEntity> findItem(Map<String, String> filters);
}

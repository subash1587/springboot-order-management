package com.order.ordermanagement.repo.custom;

import java.util.List;

import com.order.ordermanagement.entity.ItemEntity;

public interface ItemRepoCustom {

	List<ItemEntity> findItem(List<String> filters);
}

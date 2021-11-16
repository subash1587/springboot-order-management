package com.order.ordermanagement.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.ordermanagement.entity.ItemEntity;

public interface ItemRepo extends JpaRepository<ItemEntity, Integer>{

	@Query(value="select * from item", nativeQuery=true)
	Page<ItemEntity> findItemWithPagination(PageRequest pageRequest);
	
	@Query(value="select i from ItemEntity i where i.name = ?1 and i.id = ?2")
	ItemEntity findItemByNameAndID(String name, int id);
	
}

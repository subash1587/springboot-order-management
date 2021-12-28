package com.order.ordermanagement.repo;


import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.ordermanagement.entity.ItemEntity;
import com.order.ordermanagement.repo.custom.ItemRepoCustom;

public interface ItemRepo extends JpaRepository<ItemEntity, Integer>, ItemRepoCustom{

	@Query(value="select i from ItemEntity i where i.name in(:names)")
	List<ItemEntity> findByNameList(@Param("names") Collection<String> names);
	
	@Modifying
	@Query(value="update ItemEntity i set i.name = :name where i.id = :id")
	@Transactional
	void updateItemName(@Param("name") String name, @Param("id") int id);
	
	List<ItemEntity> findAllByOrderByNameAsc();
	
	List<ItemEntity> findAllByOrderByNameDesc();

	List<ItemEntity> findAllByOrderByPriceDesc();

	List<ItemEntity> findAllByOrderByPriceAsc();

	List<ItemEntity> findAllByOrderByCategoryDesc();

	List<ItemEntity> findAllByOrderByCategoryAsc();

	List<ItemEntity> findAllByOrderByRatingDesc();

	List<ItemEntity> findAllByOrderByRatingAsc();

}

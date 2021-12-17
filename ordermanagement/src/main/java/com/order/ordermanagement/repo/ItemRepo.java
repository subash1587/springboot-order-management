package com.order.ordermanagement.repo;


import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.ordermanagement.entity.ItemEntity;
import com.order.ordermanagement.repo.custom.ItemRepoCustom;

public interface ItemRepo extends JpaRepository<ItemEntity, Integer>, ItemRepoCustom{

	@Query(value="select * from item", nativeQuery=true)
	Page<ItemEntity> findItemWithPagination(Pageable pageable);
	
	@Query(value="select i from ItemEntity i where i.name = ?1 and i.id = ?2")
	ItemEntity findItemByNameAndID(String name, int id);
	
	@Query(value="select * from item where price > :price", nativeQuery=true)
	List<ItemEntity> findItemsWithPrice(@Param("price") double price);
	
	@Query(value="select i from ItemEntity i where i.name in(:names)")
	List<ItemEntity> findByNameList(@Param("names") Collection<String> names);
	
	@Modifying
	@Query(value="update ItemEntity i set i.name = :name where i.id = :id")
	@Transactional
	void updateItemName(@Param("name") String name, @Param("id") int id);
	
	@Modifying
	@Query(value="insert into item (name,price) values(:name, :price)", nativeQuery=true)
	@Transactional
	void insertItem(@Param("name") String name, @Param("price") double price);

	@Query(value="select * from item order by :sortBy asc", nativeQuery=true)
	List<ItemEntity> sortItems(@Param("sortBy") String sortBy);
	
}

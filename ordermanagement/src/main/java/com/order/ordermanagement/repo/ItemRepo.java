package com.order.ordermanagement.repo;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermanagement.entity.ItemEntity;

public interface ItemRepo extends JpaRepository<ItemEntity, Integer>{

	//public List<ItemEntity> findTopBy(Pageable pageable) ;
}

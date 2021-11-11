package com.order.ordermanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermanagement.entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{

	List<CustomerEntity> findAllByAddress(String address);

}

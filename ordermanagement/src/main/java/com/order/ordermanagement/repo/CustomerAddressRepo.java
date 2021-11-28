package com.order.ordermanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermanagement.entity.CustomerAddressEntity;
import com.order.ordermanagement.entity.CustomerEntity;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddressEntity, Integer>{

	List<CustomerAddressEntity> findByCustomerEntity(CustomerEntity customerEntity);

	CustomerAddressEntity findByCustomerEntityAndAddressType(CustomerEntity customerEntity, String addressType);

}

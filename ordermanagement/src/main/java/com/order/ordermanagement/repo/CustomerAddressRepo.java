package com.order.ordermanagement.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.ordermanagement.entity.CustomerAddressEntity;
import com.order.ordermanagement.entity.CustomerEntity;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddressEntity, Integer>{

	List<CustomerAddressEntity> findByCustomerEntity(CustomerEntity customerEntity);

	CustomerAddressEntity findByCustomerEntityAndAddressType(CustomerEntity customerEntity, String addressType);

	@Modifying
	@Transactional
	@Query(value="delete from customer_address where customer_id = :customerId", nativeQuery=true)
	void deleteByCustomerId(@Param("customerId") int customerId);

}

package com.order.ordermanagement.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.model.custom.CustomerCountPerCity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{

	@Query(value="select u from CustomerEntity u")
	List<CustomerEntity> findAllCustomers(Sort sort);
	
	@Query(value="select ca.city as city, count(c.id) as total\r\n"
			+ "from customer c, customer_address ca\r\n"
			+ "where c.id = ca.customer_id\r\n"
			+ "group by ca.city", nativeQuery=true)
	List<CustomerCountPerCity> findCustomerCountPerCity();

}

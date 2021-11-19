package com.order.ordermanagement.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.model.custom.CustomerCountPerCity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{

	List<CustomerEntity> findAllByAddress(String address);
	
	@Query(value="select u from CustomerEntity u")
	List<CustomerEntity> findAllCustomers(Sort sort);
	
	@Query("select c.address as city, count(c.id) as totalCustomer "
			+ "from CustomerEntity c group by c.address")
	List<CustomerCountPerCity> findCustomerCountPerCity();

}

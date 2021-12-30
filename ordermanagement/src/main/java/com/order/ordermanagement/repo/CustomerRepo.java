package com.order.ordermanagement.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.model.custom.CustomerCountPerCity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{

	@Query(value="select u from CustomerEntity u")
	List<CustomerEntity> findAllCustomers(Sort sort);
	
	@Query(value="select ca.city as city, count(c.id) as totalCustomer\r\n"
			+ "from customer c, customer_address ca\r\n"
			+ "where c.id = ca.customer_id\r\n"
			+ "group by ca.city", nativeQuery=true)
	List<CustomerCountPerCity> findCustomerCountPerCity();

	@Query(value="select distinct c from CustomerEntity c join CustomerAddressEntity a "
			+ "on c.id = a.customerEntity where a.city = :city")
	List<CustomerEntity> findCustomerByCity(@Param("city") String city);

	@Query(value="select distinct c from CustomerEntity c join CustomerAddressEntity a "
			+ "on c.id = a.customerEntity where a.state = :state")
	List<CustomerEntity> findCustomerByState(@Param("state") String state);

	List<CustomerEntity> findCustomerByName(String name);

	List<CustomerEntity> findCustomerByEmail(String email);

}

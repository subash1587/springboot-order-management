package com.order.ordermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.mapper.OrderMapper;
import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.model.custom.OrderStatus;
import com.order.ordermanagement.repo.OrderRepo;
import com.order.ordermanagement.service.OrderService;

@ExtendWith(MockitoExtension.class)                         
public class OrderServiceTest {
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	OrderRepo orderRepo;
	
	@Mock
	OrderMapper orderMapper;
	
	@Test
	public void getOrdersByCustomerTest() {
		
		List<OrderEntity> mockOrderList = new ArrayList<>();
		List<OrderModel> orderModelList = new ArrayList<>();

		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(7);
		
		OrderEntity order = new OrderEntity();
		order.setId(31);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.SHIPPED);
		order.setOrderDate(LocalDate.of(2021, 11, 27));
		order.setAcceptedDate(LocalDate.of(2021, 11, 29));
		order.setPackagedDate(LocalDate.of(2021, 11, 29));
		order.setShippedDate(LocalDate.of(2021, 11, 29));
		order.setEstimatedDeliveryDate(LocalDate.of(2021, 11, 30));
		order.setActualDeliveryDate(null);
		order.setCancelledDate(null);
		mockOrderList.add(order);
		
		OrderModel orderModel = new OrderModel();
		orderModel.setId(31);
		orderModel.setCustomerId(7);
		orderModel.setStatus(OrderStatus.SHIPPED);
		orderModel.setOrderDate(LocalDate.of(2021, 11, 27));
		orderModel.setAcceptedDate(LocalDate.of(2021, 11, 29));
		orderModel.setPackagedDate(LocalDate.of(2021, 11, 29));
		orderModel.setShippedDate(LocalDate.of(2021, 11, 29));
		orderModel.setEstimatedDeliveryDate(LocalDate.of(2021, 11, 30));
		orderModel.setActualDeliveryDate(null);
		orderModel.setCancelledDate(null);
		orderModelList.add(orderModel);
			
		Mockito.when(orderMapper.convertOrderEntityListToOrderModelList(mockOrderList)).thenReturn(orderModelList);
		Mockito.when(orderRepo.findAllByCustomerEntity(Mockito.any())).thenReturn(mockOrderList);
		List<OrderModel> orderList = orderService.getOrdersByCustomer(7);

		assertEquals(1,orderList.size());
		assertEquals(OrderStatus.SHIPPED, orderList.get(0).getStatus());
		assertEquals(LocalDate.of(2021, 11, 29), orderList.get(0).getShippedDate());
	}	
}

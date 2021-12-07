package com.order.ordermanagement;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.order.ordermanagement.common.exception.ApiException;
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
	
	@Test
	public void updateOrderOrderedToAcceptedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(8);
		OrderEntity order = new OrderEntity();
		order.setId(33);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ORDERED);
		order.setOrderDate(LocalDate.of(2021, 11, 27));
		order.setEstimatedDeliveryDate(LocalDate.of(2021, 11, 30));
				
		Mockito.when(orderRepo.findById(33)).thenReturn(Optional.of(order));
		orderService.updateOrder(33, OrderStatus.ACCEPTED);
		
		verify(orderRepo, times(1)).save(order);
		assertEquals(OrderStatus.ACCEPTED, order.getStatus());
		assertEquals(order.getAcceptedDate(), LocalDate.now());
	}
	
	@Test
	public void updateOrderAcceptedToPackagedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(2);
		OrderEntity order = new OrderEntity();
		order.setId(36);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ACCEPTED);
		order.setAcceptedDate(LocalDate.of(2021, 11, 28));
				
		Mockito.when(orderRepo.findById(36)).thenReturn(Optional.of(order));
		orderService.updateOrder(36, OrderStatus.PACKAGED);
		
		verify(orderRepo, times(1)).save(order);
		assertEquals(OrderStatus.PACKAGED, order.getStatus());
		assertEquals(order.getPackagedDate(), LocalDate.now());
	}
	
	@Test
	public void updateOrderPackagedToShippedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(30);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.PACKAGED);
		order.setPackagedDate(LocalDate.of(2021, 11, 29));
				
		Mockito.when(orderRepo.findById(30)).thenReturn(Optional.of(order));
		orderService.updateOrder(30, OrderStatus.SHIPPED);
		
		verify(orderRepo, times(1)).save(order);
		assertEquals(OrderStatus.SHIPPED, order.getStatus());
		assertEquals(order.getShippedDate(), LocalDate.now());
	}
	
	@Test
	public void updateOrderShippedToDeliveredTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(7);
		OrderEntity order = new OrderEntity();
		order.setId(31);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.SHIPPED);
		order.setShippedDate(LocalDate.of(2021, 11, 29));
		order.setEstimatedDeliveryDate(LocalDate.of(2021, 11, 30));
				
		Mockito.when(orderRepo.findById(31)).thenReturn(Optional.of(order));
		orderService.updateOrder(31, OrderStatus.DELIVERED);
		
		verify(orderRepo, times(1)).save(order);
		assertEquals(OrderStatus.DELIVERED, order.getStatus());
		assertEquals(order.getActualDeliveryDate(), LocalDate.now());
	}
	
	@Test
	public void updateOrderOrderedToPackagedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(8);
		OrderEntity order = new OrderEntity();
		order.setId(33);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ORDERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(33)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(33, OrderStatus.PACKAGED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderOrderedToShippedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(8);
		OrderEntity order = new OrderEntity();
		order.setId(33);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ORDERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(33)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(33, OrderStatus.SHIPPED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderOrderedToDeliveredTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(8);
		OrderEntity order = new OrderEntity();
		order.setId(33);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ORDERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(33)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(33, OrderStatus.DELIVERED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderAcceptedToOrderedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(2);
		OrderEntity order = new OrderEntity();
		order.setId(36);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ACCEPTED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(36)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(36, OrderStatus.ORDERED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderAcceptedToShippedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(2);
		OrderEntity order = new OrderEntity();
		order.setId(36);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ACCEPTED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(36)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(36, OrderStatus.SHIPPED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderAcceptedToDeliveredTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(2);
		OrderEntity order = new OrderEntity();
		order.setId(36);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.ACCEPTED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(36)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(36, OrderStatus.DELIVERED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderPackagedToOrderedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(30);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.PACKAGED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(30)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(30, OrderStatus.ORDERED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderPackagedToAcceptedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(30);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.PACKAGED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(30)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(30, OrderStatus.ACCEPTED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderPackagedToDeliveredTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(30);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.PACKAGED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(30)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(30, OrderStatus.DELIVERED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderPackagedToCancelledTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(30);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.PACKAGED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(30)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(30, OrderStatus.CANCELLED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderShippedToOrderedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(7);
		OrderEntity order = new OrderEntity();
		order.setId(31);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.SHIPPED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(31)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(31, OrderStatus.ORDERED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderShippedToAcceptedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(7);
		OrderEntity order = new OrderEntity();
		order.setId(31);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.SHIPPED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(31)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(31, OrderStatus.ACCEPTED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderShippedToPackagedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(7);
		OrderEntity order = new OrderEntity();
		order.setId(31);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.SHIPPED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(31)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(31, OrderStatus.PACKAGED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderShippedToCancelledTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(7);
		OrderEntity order = new OrderEntity();
		order.setId(31);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.SHIPPED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(31)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(31, OrderStatus.CANCELLED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderDeliveredToOrderedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(24);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.DELIVERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(24)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(24, OrderStatus.ORDERED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderDeliveredToAcceptedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(24);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.DELIVERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(24)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(24, OrderStatus.ACCEPTED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderDeliveredToPackagedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(24);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.DELIVERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(24)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(24, OrderStatus.PACKAGED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderDeliveredToShippedTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(5);
		OrderEntity order = new OrderEntity();
		order.setId(24);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.DELIVERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(24)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(24, OrderStatus.SHIPPED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateOrderDeliveredToCancelledTest() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(6);
		OrderEntity order = new OrderEntity();
		order.setId(14);
		order.setCustomerEntity(customerEntity);
		order.setStatus(OrderStatus.DELIVERED);
		
		String expectedMessage = "Status cannot be updated";
		Mockito.when(orderRepo.findById(14)).thenReturn(Optional.of(order));
		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			orderService.updateOrder(14, OrderStatus.CANCELLED);
		});		
		assertTrue(exception.getErrorMessage().equals(expectedMessage));
	}

}

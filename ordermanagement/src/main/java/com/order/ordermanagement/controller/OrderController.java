package com.order.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.model.custom.OrderStatus;
import com.order.ordermanagement.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(path="/order", method=RequestMethod.POST)
	public ResponseEntity<?> placeOrder(@RequestBody OrderModel orderModel) {
		orderService.addOrder(orderModel);
		return ResponseEntity.accepted().build();
	}
	
	@RequestMapping(path="/order",method=RequestMethod.GET)
	public ResponseEntity<?> getOrders(@RequestParam(name="sort_by",required=false) String sortBy, @RequestParam(name="order_by",required=false) String orderBy) {
		List<OrderModel> orderList = orderService.getOrders(sortBy, orderBy);
		return ResponseEntity.ok(orderList);
	}
	
	@RequestMapping(path="/order/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> searchOrder(@PathVariable int id) {
		OrderModel orderModel = orderService.searchOrder(id);
		return ResponseEntity.ok(orderModel);
	}
	
	@RequestMapping(path="/customer/{id}/order", method=RequestMethod.GET)
	public ResponseEntity<?> getOrdersByCustomer(@PathVariable("id") int id){
		List<OrderModel> orderList = orderService.getOrdersByCustomer(id);
		return ResponseEntity.ok(orderList);
	}
	
	@RequestMapping(path="/order/sort", method=RequestMethod.GET)
	public ResponseEntity<?> getTopOrdersBySaleValue(){
		List<OrderModel> orderList = orderService.getTopOrdersBySaleValue();
		return ResponseEntity.ok(orderList);
	}

	@RequestMapping(path="/order/page/{index}", method=RequestMethod.GET)
	public ResponseEntity<?> getOrdersWithPagination(@PathVariable("index") int index){
		List<OrderModel> orderList = orderService.getOrdersWithPagination(index);
		return ResponseEntity.ok(orderList);
	}
	
	@RequestMapping(path="/order/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestParam("status") OrderStatus status){
		orderService.updateOrder(id, status);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/order/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteOrder(@PathVariable int id){
		orderService.deleteOrder(id);
		return ResponseEntity.ok().build();
	}
}

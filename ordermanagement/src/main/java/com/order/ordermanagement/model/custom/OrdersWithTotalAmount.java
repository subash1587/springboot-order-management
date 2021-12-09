package com.order.ordermanagement.model.custom;

import com.order.ordermanagement.entity.CustomerEntity;

public class OrdersWithTotalAmount {

	private int orderId;
	private CustomerEntity customerEntity;
	private double totalAmount;
	
	public OrdersWithTotalAmount(int orderId, CustomerEntity customerEntity, double totalAmount) {
		this.orderId = orderId;
		this.customerEntity = customerEntity;
		this.totalAmount = totalAmount;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}
	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}

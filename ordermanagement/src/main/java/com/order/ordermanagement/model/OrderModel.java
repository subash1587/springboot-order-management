package com.order.ordermanagement.model;

import java.time.LocalDate;
import java.util.List;

public class OrderModel {

	private int id;
	private LocalDate orderDate;
	private LocalDate estimatedDeliveryDate;
	private LocalDate actualDeliveryDate;
	private boolean isDelivered;
	private CustomerModel customerModel;
	private List<OrderItemModel> orderItemList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CustomerModel getCustomerModel() {
		return customerModel;
	}
	public void setCustomerModel(CustomerModel customerModel) {
		this.customerModel = customerModel;
	}
	public List<OrderItemModel> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItemModel> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDate getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}
	public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}
	public LocalDate getActualDeliveryDate() {
		return actualDeliveryDate;
	}
	public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	public boolean getIsDelivered() {
		return isDelivered;
	}
	public void setIsDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
		
}

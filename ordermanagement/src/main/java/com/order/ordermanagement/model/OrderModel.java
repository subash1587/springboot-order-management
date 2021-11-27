package com.order.ordermanagement.model;

import java.time.LocalDate;
import java.util.List;

public class OrderModel {

	private int id;
	private String status;
	private LocalDate orderDate;
	private LocalDate shippedDate;
	private LocalDate estimatedDeliveryDate;
	private LocalDate actualDeliveryDate;
	private LocalDate cancelledDate;
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
	public LocalDate getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(LocalDate cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
}

package com.order.ordermanagement.model;

import java.time.LocalDate;
import java.util.List;

import com.order.ordermanagement.model.custom.OrderStatus;

public class OrderModel {

	private int id;
	private OrderStatus status;
	private LocalDate orderDate;
	private LocalDate acceptedDate;
	private LocalDate packagedDate;
	private LocalDate shippedDate;
	private LocalDate estimatedDeliveryDate;
	private LocalDate actualDeliveryDate;
	private LocalDate cancelledDate;
	private int customerId;
	private List<OrderItemModel> orderItemList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public LocalDate getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(LocalDate cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	public LocalDate getAcceptedDate() {
		return acceptedDate;
	}
	public void setAcceptedDate(LocalDate acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	public LocalDate getPackagedDate() {
		return packagedDate;
	}
	public void setPackagedDate(LocalDate packagedDate) {
		this.packagedDate = packagedDate;
	}
}

package com.order.ordermanagement.model;

import java.util.Date;
import java.util.List;

public class OrderModel {

	private int id;
	private Date orderDate;
	private Date deliveryDate;
	private int delivered;
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getDelivered() {
		return delivered;
	}
	public void setDelivered(char delivered) {
		this.delivered = delivered;
	}
		
}

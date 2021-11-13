package com.order.ordermanagement.model;

import java.util.List;

public class OrderModel {

	private int id;
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
		
}

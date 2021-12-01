package com.order.ordermanagement.model.custom;

public enum OrderStatus {

	ORDERED("ordered"),
	ACCEPTED("accepted"),
	PACKAGED("packaged"),
	SHIPPED("shipped"),
	DELIVERED("delivered"),
	CANCELLED("cancelled");
	
	private String status;
	
	private OrderStatus(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

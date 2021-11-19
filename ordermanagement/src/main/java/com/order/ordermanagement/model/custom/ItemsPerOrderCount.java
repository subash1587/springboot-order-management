package com.order.ordermanagement.model.custom;

import com.order.ordermanagement.entity.OrderEntity;

public class ItemsPerOrderCount {
	
	private int orderId;
	private long itemCount;
	
	public ItemsPerOrderCount(OrderEntity order,long itemCount) {
		this.orderId = order.getId();
		this.itemCount = itemCount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}
	
}

package com.order.ordermanagement.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_order")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(name="order_date")
	private LocalDate orderDate;
	@Column(name="estimated_delivery_date")
	private LocalDate estimatedDeliveryDate;
	@Column(name="actual_delivery_date")
	private LocalDate actualDeliveryDate;
	@Column(name="is_delivered")
	private boolean isDelivered;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CustomerEntity customerEntity;
	
	@OneToMany(mappedBy="order",cascade = CascadeType.ALL)
	private List<OrderItemEntity> orderItemList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}
	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}
	public List<OrderItemEntity> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItemEntity> orderItemList) {
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

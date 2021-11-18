package com.order.ordermanagement.entity;

import java.util.Date;
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

	@Column
	private Date orderDate;
	@Column
	private Date deliveryDate;
	@Column
	private char delivered;
	
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
	public char getDelivered() {
		return delivered;
	}
	public void setDelivered(char delivered) {
		this.delivered = delivered;
	}
	
}

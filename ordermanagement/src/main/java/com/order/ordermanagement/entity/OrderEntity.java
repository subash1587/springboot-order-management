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

import com.order.ordermanagement.model.custom.OrderStatus;

@Entity
@Table(name="user_order")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String status;
	@Column(name="order_date")
	private LocalDate orderDate;
	@Column(name="accepted_date")
	private LocalDate acceptedDate;
	@Column(name="packaged_date")
	private LocalDate packagedDate;
	@Column(name="shipped_date")
	private LocalDate shippedDate;
	@Column(name="estimated_delivery_date")
	private LocalDate estimatedDeliveryDate;
	@Column(name="actual_delivery_date")
	private LocalDate actualDeliveryDate;
	@Column(name="cancelled_date")
	private LocalDate cancelledDate;
	
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
	public void setStatus(OrderStatus status) {
		this.status = status.getStatus();
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

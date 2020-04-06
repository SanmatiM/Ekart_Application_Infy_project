package com.infy.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.infy.model.OrderStatus;
import com.infy.model.PaymentThrough;


@Entity
@Table(name="EK_ORDER")
public class OrderEntity {
	
	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;

	@Column(name="ORDER_NUMBER")
	private Integer orderNumber;
	
	@Column(name="DATE_OF_ORDER")
	private LocalDateTime dateOfOrder;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PRODUCT_ID")
	private ProductEntity productEntity;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="TOTAL_PRICE")
	private Double totalPrice;
	
	@Column(name="ADDRESS_ID")
	private Integer addressId;
	
	@Column(name="ORDER_STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Column(name="PAYMENT_THROUGH")
	@Enumerated(EnumType.STRING)
	private PaymentThrough paymentThrough;
	
	@Column(name="DATE_OF_DELIVERY")
	private LocalDateTime dateOfDelivery;
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDateTime getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(LocalDateTime dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public PaymentThrough getPaymentThrough() {
		return paymentThrough;
	}

	public void setPaymentThrough(PaymentThrough paymentThrough) {
		this.paymentThrough = paymentThrough;
	}

	public LocalDateTime getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(LocalDateTime dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}
	
}

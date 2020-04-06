package com.infy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="EK_CUSTOMER_CART")
public class CustomerCartEntity {
	
	@Id
	@Column(name="CART_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cartId;
	
	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="PRODUCT_ID")
	private ProductEntity productEntity;
	
	@Column(name="QUANTITY")
	private Integer quantity;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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
	
	

}

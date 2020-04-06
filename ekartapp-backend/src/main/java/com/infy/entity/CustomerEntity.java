package com.infy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="EK_CUSTOMER")
public class CustomerEntity {
	@Id
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="PHONE_NUMBER")
	private String phoneNumber;


	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_EMAIL_ID")
	private List<AddressEntity> addressEntities;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_EMAIL_ID")
	private List<CustomerCartEntity> customerCarts;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_EMAIL_ID")
	private List<OrderEntity> orders;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<AddressEntity> getAddressEntities() {
		return addressEntities;
	}

	public void setAddressEntities(List<AddressEntity> addressEntities) {
		this.addressEntities = addressEntities;
	}

	public List<CustomerCartEntity> getCustomerCarts() {
		return customerCarts;
	}

	public void setCustomerCarts(List<CustomerCartEntity> customerCarts) {
		this.customerCarts = customerCarts;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}
	
	
}

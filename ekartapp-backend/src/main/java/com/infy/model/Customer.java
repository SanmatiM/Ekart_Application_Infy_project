package com.infy.model;

import java.util.List;

public class Customer {
	
	private String emailId;
	private String name;
	private String password;
	private String phoneNumber;
	private List<Address> addresses;
	private List<CustomerCart> customerCarts;
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
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
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<CustomerCart> getCustomerCarts() {
		return customerCarts;
	}
	public void setCustomerCarts(List<CustomerCart> customerCarts) {
		this.customerCarts = customerCarts;
	}
	
}

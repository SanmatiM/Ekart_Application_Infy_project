package com.infy.dao;

import com.infy.model.Address;
import com.infy.model.Customer;

public interface CustomerDAO {
	public Boolean checkCustomerEmailId(String emailId);
	public String registerCustomer(Customer customer);
	public String authenticateCustomer(String emailId, String password);
	public Customer getCustomer(String emailId);
	
	public Integer addShippingAddress(String customerEmailId, Address address);
	public void updateShippingAddress(Address address);
	public void deleteShippingAddress(String customerEmailId, Integer addressId);
	public Address getShippingAddress(Integer addressId) throws Exception;
	
}

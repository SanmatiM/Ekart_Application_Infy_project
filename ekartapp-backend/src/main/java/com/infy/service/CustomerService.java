package com.infy.service;

import com.infy.model.Address;
import com.infy.model.Customer;


public interface CustomerService {
	
		public Customer authenticateCustomer(String emailId, String password) throws Exception;
		public String registerNewCustomer(Customer customer) throws Exception ;
		public Integer addShippingAddress(String customerEmailId, Address address) throws Exception;
		
		public void updateShippingAddress(Address address) throws Exception;
		
		public void deleteShippingAddress(String customerEmailId, Integer addressId) throws Exception;
		
		public Address getShippingAddress(Integer addressId) throws Exception;
	}



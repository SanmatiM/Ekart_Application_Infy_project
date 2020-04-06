package com.infy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.Validator.CustomerValidator;
import com.infy.dao.CustomerDAO;
import com.infy.model.Address;
import com.infy.model.Customer;

@Service( value = "customerService" )
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public Customer authenticateCustomer(String emailId, String password)
			throws Exception{

		Customer customer = null;
		String email= customerDAO.authenticateCustomer(emailId.toLowerCase(), password);
		if(email!=null){
//				System.out.println(email);
				customer  = customerDAO.getCustomer(email);
		}
		else
			throw new Exception ("CustomerService.INVALID_CREDENTIALS");
		
		return customer;
		
	}
	
	@Override
	public String registerNewCustomer(Customer customer)
			throws Exception {
		
		String registeredWithEmailId = null;

		CustomerValidator.validateCustomer(customer);
		Boolean emailAvailable = customerDAO.checkCustomerEmailId(customer.getEmailId().toLowerCase());
		if(emailAvailable){
			
				String emailIdToDB = customer.getEmailId().toLowerCase();

				customer.setEmailId(emailIdToDB);

				registeredWithEmailId = customerDAO.registerCustomer(customer);

		}
		else{
			throw new Exception("CustomerService.EMAIL_ID_ALREADY_IN_USE");
		}


		return registeredWithEmailId;
	}

	@Override
	public Integer addShippingAddress(String customerEmailId, Address address)
			throws Exception {
		
		CustomerValidator.validateAddress(address);
		Integer newAddressID = customerDAO.addShippingAddress(customerEmailId, address);
		
		return newAddressID; 
	}
	
	@Override
	public void updateShippingAddress(Address address) throws Exception {
		
		CustomerValidator.validateAddress(address);
		customerDAO.updateShippingAddress(address);
		
	}
	

	@Override
	public void deleteShippingAddress(String customerEmailId, Integer addressId)
			throws Exception {
		
		customerDAO.deleteShippingAddress(customerEmailId, addressId );
	}

	 @Override
	   public Address getShippingAddress(Integer addressId) throws Exception {  
	       return customerDAO.getShippingAddress(addressId);
	       
	   }
}
	

package com.infy.Validator;

import com.infy.model.Address;
import com.infy.model.Customer;

public class CustomerValidator {
	public static void validateCustomer(Customer customer) throws Exception {
		
		if(!validateEmailId(customer.getEmailId()))
			throw new Exception("CustomerValidator.INVALID_EMAIL_FORMAT");
		
		if(!validatePhoneNumber(customer.getPhoneNumber()))
			throw new Exception("CustomerValidator.INVALID_PHONE_NUMBER");
		
		if(!validateName(customer.getName()))
			throw new Exception("CustomerValidator.INVALID_NAME");
		
		if(!validatePassword(customer.getPassword()))
			throw new Exception("CustomerValidator.INVALID_PASSWORD_FORMAT");
			
	}

	public static void validateAddress(Address address) throws Exception{
			
			if ( ! validateAddressLine1(address.getAddressLine1()) )
				throw new Exception("CustomerValidator.INVALID_ADDRESS_LINE_1");
			
			if ( ! validateCity(address.getCity()) )
				throw new Exception("CustomerValidator.INVALID_CITY");
			
			if ( ! validateState(address.getState() ) )
				throw new Exception("CustomerValidator.INVALID_STATE");
			
			if (! validateContactNumber(address.getContactNumber()))
				throw new Exception("CustomerValidator.INVALID_CONTACT_NUMBER");
			
			if (! validatePIN(address.getPin()))
				throw new Exception("CustomerValidator.INVALID_PIN");
			
	
		}
	
	
	

	public static Boolean validateName(String name){
		if(!name.matches("[ ]*") && name.matches("([A-Za-z])+([ ][A-Za-z]+)*"))
			return true;
		return false;
	}
	
	public static Boolean validateEmailId(String emailId) {
		if(emailId.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			return true;
		
		return false;
	}
	
	public static Boolean validatePassword(String password) {
		if (password.length()>=6 && password.length()<=20)
			if(password.matches(".*[A-Z]+.*"))
				if(password.matches(".*[a-z]+.*"))
					if(password.matches(".*[0-9]+.*"))
						if(password.matches(".*[^a-zA-Z-0-9].*"))
							return true;
		
		return false;
	}
	
	public static Boolean validatePhoneNumber(String phoneNumber) {
		if(phoneNumber.matches("[0-9]{10}")) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateCity(String city){
		if(city.matches("[A-za-z]+"))
			return true;
		return false;
		
	}
	
	public static Boolean validateState(String state){
		if(state.matches("[A-za-z]+[A-za-z ]*"))
			return true;
		return false;
		
	}
	
	public static Boolean validateAddressLine1(String addressLine1){
		if(addressLine1.matches("[A-Za-z]+[]*[0-9]*[#]*"))
			return true;
		return false;
		
	}
	

	public static Boolean validatePIN(String pin){
		if(pin.matches("[A-Za-z0-9]{6,10}"))
			return true;
		return false;
	} 
	
	private static boolean validateContactNumber(String contactNumber) {
		if(contactNumber.matches("[0-9]{10}")) {
			return true;
		}
		return false;
	}

}

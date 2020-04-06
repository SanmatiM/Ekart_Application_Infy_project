package com.infy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.model.Address;
import com.infy.model.Customer;
import com.infy.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping(value="CustomerAPI")
public class CustomerAPI {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private Environment environment;
	
//	static Logger logger = LogManager.getLogger(CustomerAPI.class.getName());
	
	@PostMapping(value = "customerLogin")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) throws Exception {
		ResponseEntity<Customer> response=null;
		try {
//			logger.info("CUSTOMER TRYING TO LOGIN, VALIDATING CREDENTIALS. CUSTOMER EMAIL ID: "+customer.getEmailId());
			
			Customer customerfromDB = customerService.authenticateCustomer(customer.getEmailId(), customer.getPassword());
			
//			System.out.println(environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS"));
//			logger.info("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL : "+customerfromDB.getEmailId());
			
			response= new ResponseEntity<Customer>(customerfromDB, HttpStatus.OK);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
		return response;
	}
	
	@PostMapping(value = "registerCustomer")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) throws Exception {
		ResponseEntity<String> response=null;
		try
		{
//			logger.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: "+customer.getEmailId());
			
			String registeredWithEmailID = customerService.registerNewCustomer(customer);
			registeredWithEmailID = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS")+registeredWithEmailID;
			response= new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
			
		}
		catch (Exception e){
			if(e.getMessage().contains("Validator")){
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}
		return response;
	}
	@PostMapping(value = "addNewShippingAddress/{customerEmailId:.+}")
	public ResponseEntity<String> addNewShippingAddress(@RequestBody Address address, @PathVariable("customerEmailId") String customerEmailId) throws Exception {
		int addressId;
		
		try
		{
			addressId = customerService.addShippingAddress(customerEmailId,address);
			String message=environment.getProperty("CustomerAPI.NEW_SHIPPING_ADDRESS_ADDED_SUCCESS");
			String toReturn = message+addressId;
			
			toReturn = toReturn.trim();
			return new ResponseEntity<String>(toReturn, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}	}
	
	@PostMapping(value = "updateShippingAddress")
	public ResponseEntity<String> updateShippingAddress(@RequestBody Address address) throws Exception {
		try
		{
			customerService.updateShippingAddress(address);
			String modificationSuccessMsg=environment.getProperty("CustomerAPI.UPDATE_ADDRESS_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "deleteShippingAddress/{addressId}/{customerEmailId:.+}")
	public ResponseEntity<String> deleteShippingAddress(@PathVariable("addressId") Integer addressId, @PathVariable("customerEmailId") String customerEmailId) throws Exception {

		try
		{
			customerService.deleteShippingAddress(customerEmailId, addressId);
			String modificationSuccessMsg = environment.getProperty("CustomerAPI.CUSTOMER_ADDRESS_DELETED_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
			
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	 @GetMapping(value = "getShippingAddress/{addressId}")
	   public ResponseEntity<Address> getShippingAddress(@PathVariable("addressId") Integer addressId) throws Exception {
	 
	       try
	       {
	           Address address = customerService.getShippingAddress(addressId);
	           return new ResponseEntity<Address>(address, HttpStatus.OK);
	           
	       }
	       catch (Exception e) {
	           
	           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
	       }
	   }
}
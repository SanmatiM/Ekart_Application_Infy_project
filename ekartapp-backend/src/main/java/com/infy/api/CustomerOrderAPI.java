package com.infy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.service.CustomerOrderService;

@CrossOrigin
@RestController
@RequestMapping(value="CustomerOrderAPI")
public class CustomerOrderAPI {
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping(value = "placeOrder")
	public ResponseEntity<String> placeOrder(@RequestBody Integer cartId) throws Exception {
		ResponseEntity<String> response=null;
		try {
//			logger.info("CUSTOMER TRYING TO LOGIN, VALIDATING CREDENTIALS. CUSTOMER EMAIL ID: "+customer.getEmailId());
			
			Integer orderId=customerOrderService.placeOrder(cartId);
			
			String message=environment.getProperty("CustomerOrderAPI.PLACED_ORDER")+orderId;
//			System.out.println(environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS"));
//			logger.info("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL : "+customerfromDB.getEmailId());
			
			response= new ResponseEntity<String>(message, HttpStatus.OK);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
		return response;
	}
}

package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EkartappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartappApplication.class, args);
	}

}

//package com.infy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.env.Environment;
//
//import com.infy.model.Customer;
//import com.infy.service.CustomerService;
//@SpringBootApplication
//public class EkartappApplication implements CommandLineRunner{
//
//		@Autowired
//		CustomerService customerService;
//		
//		@Autowired
//		Environment environment;
//		
//	public static void main(String[] args) {
//		SpringApplication.run(EkartappApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
////		authenticateCustomer();
//		registerNewCustomer();
//	}
//
//	 void registerNewCustomer() {
//		// TODO Auto-generated method stub
//		 try {
//			 Customer customer=new Customer();
//			 	customer.setName("sannu");
//			 	customer.setPhoneNumber("1234567890");
//				customer.setEmailId("sannu@infosys.com");
//				customer.setPassword("San@123");
//				customerService.registerNewCustomer(customer);
//				
//				System.out.println("register success");
//
//			} catch (Exception e) {
//				System.out.println("error");
//
//			}
//		
//	}
//
//	void authenticateCustomer() {
//		// TODO Auto-generated method stub
//		try {
//
//			String email="tom@infosys.com";
//			String pwd="Tom@12345";
//			customerService.authenticateCustomer(email, pwd);
//			
//			System.out.println("login success");
//
//		} catch (Exception e) {
//			System.out.println("error");
//
//		}
//		
//	}
//
//}

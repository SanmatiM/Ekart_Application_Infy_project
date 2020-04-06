package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.CustomerProductDAO;
import com.infy.model.Product;

@Service(value = "customerProductService")
@Transactional
public class CustomerProductServiceImpl implements CustomerProductService {

	@Autowired
	private CustomerProductDAO customerProductDAO;
	
	@Override
	public List<Product> getAllProducts() throws Exception {

		List<Product> products =null;
		products = customerProductDAO.getAllProducts();
		return products;	
		
	}
}

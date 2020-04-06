package com.infy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dao.CustomerOrderDAO;

@Service( value = "customerOrderService" )
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
	
	@Autowired
	private CustomerOrderDAO customerOrderDAO;

	@Override
	public Integer placeOrder(Integer cartId) throws Exception {
		Integer orderId=customerOrderDAO.placeOrder(cartId);
		if(orderId==null) {
			throw new Exception("CustomerOrderService.PLACE_ORDER_UNSUCCESSFULL");
		}
		return orderId;
	}
	
	
}

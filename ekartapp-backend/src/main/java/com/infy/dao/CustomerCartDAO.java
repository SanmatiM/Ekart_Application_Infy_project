package com.infy.dao;

import java.util.List;

import com.infy.model.CustomerCart;
import com.infy.model.Product;

public interface CustomerCartDAO {

	public void addProductToCart(String customerEmailId, CustomerCart customerCart);
	public List<CustomerCart> getCustomerCarts(String customerEmailId);
	public void modifyQuantityOfProductInCart(Integer cartId, Integer quantity);
	public void deleteProductFromCart(String customerEmailId, Integer cartId);
	public Product getProductById(Integer productId);
}

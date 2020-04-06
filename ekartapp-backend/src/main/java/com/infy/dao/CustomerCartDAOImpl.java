package com.infy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.CustomerCartEntity;
import com.infy.entity.CustomerEntity;
import com.infy.entity.ProductEntity;
import com.infy.model.CustomerCart;
import com.infy.model.Product;

@Repository(value = "customerCartDAO")
public class CustomerCartDAOImpl implements CustomerCartDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void addProductToCart(String customerEmailId, CustomerCart customerCart) {

		CustomerCartEntity cartEntity = new CustomerCartEntity();
		
		ProductEntity productEntity = entityManager.find(ProductEntity.class, customerCart.getProduct().getProductId());
		
		cartEntity.setProductEntity(productEntity);
		cartEntity.setQuantity(customerCart.getQuantity());
		
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		customerEntity.getCustomerCarts().add(cartEntity);
		
		entityManager.persist(customerEntity);
	}

	@Override
	public List<CustomerCart> getCustomerCarts(String customerEmailId) {
		
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		List<CustomerCartEntity> cartEntities = customerEntity.getCustomerCarts(); 
		
		List<CustomerCart> listCustomerCart = new ArrayList<>();
		for (CustomerCartEntity customerCartEntity : cartEntities) {
			CustomerCart cart = new CustomerCart();
			cart.setCartId(customerCartEntity.getCartId());
			cart.setQuantity(customerCartEntity.getQuantity());
			ProductEntity productEntity = customerCartEntity.getProductEntity();
			
			Product product = new Product();
			product.setBrand(productEntity.getBrand());
			product.setCategory(productEntity.getCategory());
			product.setDescription(productEntity.getDescription());
			product.setDiscount(productEntity.getDiscount());
			product.setName(productEntity.getName());
			product.setPrice(productEntity.getPrice());
			product.setProductId(productEntity.getProductId());
			product.setQuantity(productEntity.getQuantity());
			
			cart.setProduct(product);
			
			listCustomerCart.add(cart);
		}
		return listCustomerCart;
	}

	@Override
	public void modifyQuantityOfProductInCart(Integer cartId, Integer quantity) {
		
		CustomerCartEntity cartEntity = entityManager.find(CustomerCartEntity.class, cartId);
		
		cartEntity.setQuantity(quantity);
		
	}

	@Override
	public void deleteProductFromCart(String customerEmailId, Integer cartId) {

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		List<CustomerCartEntity> carts = customerEntity.getCustomerCarts();
		CustomerCartEntity cartEntityToRemove = null;
		
		for (CustomerCartEntity customerCartEntity : carts) {
			if(cartId.equals(customerCartEntity.getCartId()))
			{
				cartEntityToRemove = customerCartEntity;
			}
		}
		carts.remove(cartEntityToRemove);
		
		CustomerCartEntity cartEntity = entityManager.find(CustomerCartEntity.class, cartId);
		
		entityManager.remove(cartEntity);
		
	}
	
	@Override
	public Product getProductById(Integer productId) {
		
		Product product =null;
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
		System.out.println("DAO");
		if (productEntity!=null){
			product = new Product();
			product.setBrand(productEntity.getBrand());
			product.setCategory(productEntity.getCategory());
			product.setDescription(productEntity.getDescription());
			product.setDiscount(productEntity.getDiscount());
			product.setName(productEntity.getName());
			product.setPrice(productEntity.getPrice());
			product.setProductId(productEntity.getProductId());
			product.setQuantity(productEntity.getQuantity());
			
		}
		return product;
	}

}

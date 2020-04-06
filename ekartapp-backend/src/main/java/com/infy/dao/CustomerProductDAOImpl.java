package com.infy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.ProductEntity;
import com.infy.model.Product;
@Repository(value = "customerProductDAO")
public class CustomerProductDAOImpl implements CustomerProductDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Product> getAllProducts() {

		Query query = entityManager.createQuery("select p from ProductEntity p");
		
		List<ProductEntity> productEntityList= query.getResultList();
		
		List<Product> listOfProducts = new ArrayList<Product>();
		for (ProductEntity productEntity : productEntityList) {
			Product product = new Product();
			product.setBrand(productEntity.getBrand());
			product.setCategory(productEntity.getCategory());
			product.setDescription(productEntity.getDescription());
			product.setName(productEntity.getName());
			product.setPrice(productEntity.getPrice());
			product.setProductId(productEntity.getProductId());
			product.setQuantity(productEntity.getQuantity());
			product.setDiscount(productEntity.getDiscount());

			listOfProducts.add(product);
		}
		return listOfProducts;
	}
}

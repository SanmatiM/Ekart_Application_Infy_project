package com.infy.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.infy.entity.CustomerCartEntity;
import com.infy.entity.CustomerEntity;
import com.infy.entity.OrderEntity;
import com.infy.entity.ProductEntity;
import com.infy.model.OrderStatus;
import com.infy.model.PaymentThrough;

@Repository(value = "customerOrderDao")
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Integer placeOrder(Integer cartId) {
		CustomerCartEntity cartEntity=entityManager.find(CustomerCartEntity.class, cartId);
		Query query=entityManager.createQuery("select c from customerentity c where c.customerCarts.cartId=:cartId");
		query.setParameter("cartId", cartId);
		List<CustomerEntity> customerEntities=query.getResultList();
		OrderEntity orderEntity=new OrderEntity();
		if(!customerEntities.isEmpty()) {
			
			Query query1=entityManager.createQuery("select max(o.orderNumber) from OrderEntity o");
			Object orderNum=query1.getSingleResult();
			orderEntity.setOrderNumber((Integer) orderNum+1);
			orderEntity.setOrderStatus(OrderStatus.CONFIRMED);
			orderEntity.setPaymentThrough(PaymentThrough.CASH_ON_DELIVERY);
			orderEntity.setQuantity(cartEntity.getQuantity());
			orderEntity.setDateOfOrder(LocalDateTime.now());
			orderEntity.setDateOfDelivery(LocalDateTime.now().plusDays(7));
			ProductEntity productEntity=new ProductEntity();
			productEntity.setBrand(cartEntity.getProductEntity().getBrand());
			productEntity.setCategory(cartEntity.getProductEntity().getCategory());
			productEntity.setDescription(cartEntity.getProductEntity().getDescription());
			productEntity.setDiscount(cartEntity.getProductEntity().getDiscount());
			productEntity.setName(cartEntity.getProductEntity().getName());
			productEntity.setPrice(cartEntity.getProductEntity().getPrice());
			productEntity.setProductId(cartEntity.getProductEntity().getProductId());
			productEntity.setQuantity(cartEntity.getProductEntity().getQuantity());
			orderEntity.setProductEntity(productEntity);
			orderEntity.setTotalPrice(cartEntity.getQuantity()*productEntity.getPrice()*(1-productEntity.getDiscount()*0.01));
			
		}
		entityManager.persist(orderEntity);
		
		return orderEntity.getOrderId();
	}
	

		

	

	
}

package com.RevBookStore.dao;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.orders;



@Repository
public class SellerDao implements SellerDaoInterface {

	@Autowired
	private SessionFactory sf;
	
	public boolean addProduct(Long userId, Products product) {
		
		System.out.println(userId);
		System.out.println(product.getName());
		Session ss = sf.openSession(); // for hiberate initialization
		Transaction et = ss.getTransaction(); ///transaction initialization
		 et.begin();  // begin the transaction
		
		ss.persist(product); // hibernate
		
		et.commit();  // commit the transaction
		return true;
	}

	@Override
	public List<Products> viewProducts(Long userId) {
		userId = 0l;
		Session ss = sf.openSession();
		Query q = ss.createQuery("from com.RevBookStore.entity.Products p where p.sellerId =: userId");
		q.setParameter("userId", userId);
		List<Products> list = q.getResultList();
		return list;
	}

	@Override
	public boolean editProduct(Products products , long p_id) {
		Transaction tx = null;
		
		Session ss = sf.openSession();
		tx = ss.beginTransaction();
		System.out.println(p_id);
		System.out.println(products.getName());
		System.out.println(products.getDescription());
		System.out.println(products.getCategory());
		System.out.println(products.getDiscount_price());
		System.out.println(products.getQuantity());
		
		Query q = ss.createQuery( "UPDATE Products p SET p.name = :name, p.description = :description, p.discount_price = :discountPrice, p.category = :category, p.quantity = :quantity WHERE p.id = :productId");
		q.setParameter("name", products.getName());
		q.setParameter("description", products.getDescription());
		q.setParameter("category", products.getCategory());
		q.setParameter("discountPrice", products.getDiscount_price());
		q.setParameter("quantity", products.getQuantity());
		q.setParameter("productId",p_id );
		q.executeUpdate();
		tx.commit();
		
		return true;
	}

	@Override
	public boolean deleteProducts(Long productId, long sellerId) {
		System.out.println(productId);
		Session ss = sf.openSession();
		Transaction et = ss.getTransaction();
		et.begin();
		
		Products product = ss.get(Products.class, productId);
		
		if(product != null)
		{
			ss.delete(product);
		}else
		{
			 System.out.println("Product not found with ID: " + productId);
			 return false;
		}
		et.commit();
		return true;
	}

	@Override
	public List<orders> getOrders() {
		Session ss = sf.openSession();
		Query q = ss.createQuery("from com.RevBookStore.entity.orders o");
		List<orders> list = q.getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Products> getProductsBySellerId(Long sellerId) {
		System.out.println(sellerId);
	    List<Products> products = new ArrayList<>();
	    try (Session session = sf.openSession()) {
	        TypedQuery<Products> query = session.createQuery("FROM Products WHERE sellerId = :sellerId", Products.class);
	        query.setParameter("sellerId", sellerId);
	        products = query.getResultList();
	        System.out.println(products.size());
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	    return products;
	}

	
	
	@Override
	@Transactional
	public List<Review> ReviewProducts(List<Long> productIds) {
	    List<Review> reviews = new ArrayList<>();
	    try (Session session = sf.openSession()) {
	        TypedQuery<Review> query = session.createQuery("FROM Review r WHERE r.product.id IN :productIds", Review.class);
	        query.setParameter("productIds", productIds);
	        reviews = query.getResultList();
	        System.out.println(reviews.size());
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	    return reviews;
	}

	@Override
	public boolean updateStatus(long sellerId, orders order) {
		System.out.println(sellerId);
		System.out.println(order.getProduct_Id());
		System.out.println(order.getStatus());
		System.out.println(order.getOrder_Id());
		
		Session ss = sf.openSession();
		Query q = ss.createQuery("UPDATE orders o SET o.status = :status WHERE o.orderId = :orderId AND o.productId = :pid AND EXISTS (SELECT 1 FROM Products p WHERE p.id =: o.productId AND p.sellerId = :sellerId)");
		
		q.setParameter("status", order.getStatus());
		q.setParameter("orderId", order.getOrder_Id());
		q.setParameter("pid", order.getProduct_Id());
		q.setParameter("productId", order.getProduct_Id());
		q.setParameter("sellerId",sellerId);
		return true;
	}


	

}

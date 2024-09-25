package com.RevBookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.RevBookStore.dao.SellerDaoInterface;
import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.orders;

@Service
public class SellerService implements SellerServiceInterface {
	
	@Autowired
	private SellerDaoInterface sellerdaoInterface;
	
	
	public boolean addProduct(Long userId,Products product) {
		return sellerdaoInterface.addProduct(userId,product);
		
	}


	@Override
	public List<Products> viewProducts(Long userId) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.viewProducts(userId);
	}


	@Override
	public boolean editProduct(Products product,long p_id) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.editProduct(product,p_id);
	}


	@Override
	public boolean deleteProductById(Long productId, long sellerId) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.deleteProducts(productId,sellerId);
	}


	@Override
	public List<orders> getOrders() {
		// TODO Auto-generated method stub
		return sellerdaoInterface.getOrders();
	}
	
	@Override
	public List<Products> getProductsBySellerId(Long sellerId) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.getProductsBySellerId(sellerId);
	}


	@Override
	public List<Review> getReviewsByProductId(List<Long> productIds) {
		// TODO Auto-generated method stub
		return sellerdaoInterface.ReviewProducts(productIds);
		
	}


	@Override
	public boolean updateStatus(long sellerId, orders order) {
		return sellerdaoInterface.updateStatus(sellerId,order);
	}
}

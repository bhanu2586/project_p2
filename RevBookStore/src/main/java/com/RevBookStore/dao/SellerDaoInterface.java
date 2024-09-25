package com.RevBookStore.dao;

import java.util.List;

import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.orders;

public interface SellerDaoInterface {

	boolean addProduct(Long userId, Products product);

	List<Products> viewProducts(Long userId);

	boolean editProduct(Products product, long p_id);

	boolean deleteProducts(Long productId, long sellerId);

	List<orders> getOrders();

	List<Products> getProductsBySellerId(Long sellerId);

	List<Review> ReviewProducts(List<Long> productIds);

	boolean updateStatus(long sellerId, orders order);

}

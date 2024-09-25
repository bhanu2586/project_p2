package com.RevBookStore.service;

import java.util.List;

import com.RevBookStore.entity.Favorite;
import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.ShoppingCart;
import com.RevBookStore.entity.User;
import com.RevBookStore.entity.orders;


public interface CustomerServiceInterface {

	List<ShoppingCart> viewCartProducts(Long customerId);

	ShoppingCart findCartByCustomerId(Long customerId);

	boolean removeProductFromCart(Long customerId, Long productId);

	List<Products> viewProducts();

	Products viewProductDeatils(Long id);

	int updateQuantity(Long productId, Long customerId,String quantity);

	boolean addProductToFavorite(Long productId, Long customerId);

	List<Favorite> viewFavorite(Long customerId);

	boolean removeFromFavorite(Long customerId, Long productId);

	boolean addProductsToCarts(Long productId, Long customerId);

	int submitreview(Review rev);

	User checkout( Long customerId);

	List<ShoppingCart> getcartdetails(Long customerId);

	int saveorder(orders order);

}

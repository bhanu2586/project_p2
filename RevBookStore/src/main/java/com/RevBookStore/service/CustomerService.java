package com.RevBookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RevBookStore.dao.CustomerDaoInterface;
import com.RevBookStore.entity.Favorite;
import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.ShoppingCart;
import com.RevBookStore.entity.User;
import com.RevBookStore.entity.orders;

@Service
public class CustomerService  implements CustomerServiceInterface{

	@Autowired
	private CustomerDaoInterface cdao;
	@Override
	public List<ShoppingCart> viewCartProducts(Long customerId) {
		return cdao.viewCartProducts(customerId);
	}
	@Override
	public ShoppingCart findCartByCustomerId(Long customerId) {
		return cdao.findCartByCustomerId(customerId);
	}
	@Override
	public boolean removeProductFromCart(Long customerId, Long productId) {
		return cdao.removeProductFromCart(customerId,productId);
	}
	@Override
	public List<Products> viewProducts() {
		return cdao.viewProducts();
	}
	@Override
	public Products viewProductDeatils(Long id) {
		return cdao.viewProductDeatils(id);
	}
	@Override
	public int updateQuantity(Long productId, Long customerId,String quantity) {
		return cdao.updateQuantity(productId,customerId,quantity);
	}
	@Override
	public boolean addProductToFavorite(Long productId, Long customerId) {
		return cdao.addProductToFavorite(productId,customerId);
	}
	@Override
	public List<Favorite> viewFavorite(Long customerId) {
		return cdao.viewFavorite(customerId);
	}
	@Override
	public boolean removeFromFavorite(Long customerId, Long productId) {
		return cdao.removeFromFavorite(customerId,productId);
	}
	@Override
	public boolean addProductsToCarts(Long productId, Long customerId) {
		return cdao.addProductsToCarts(productId,customerId);
	}
	@Override
	public int submitreview(Review rev) {
		return cdao.submitreview(rev);
	}
	@Override
	public User checkout(Long customerId) {
		return cdao.checkout(customerId);
	}
	@Override
	public List<ShoppingCart> getcartdetails(Long customerId) {
		// TODO Auto-generated method stub
		return cdao.getcartdetails(customerId);
	}
	@Override
	public int saveorder(orders order) {
		// TODO Auto-generated method stub
		return cdao.saveorder(order);
	}
	
}

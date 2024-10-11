package com.onlineshop.service;

import java.util.List;

import com.onlineshop.dao.CustomerDAOInterface;
import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Orders;
import com.onlineshop.entity.Product;
import com.onlineshop.entity.ShopingCart;
import com.onlineshop.entity.complaint;
import com.onlineshop.entity.review;
import com.onlineshop.utility.DaoFactory;

public class CustomerService implements CustomerServiceInterface {

	public int addCustomerService(Customer customer) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.addCustomerDAO(customer);
	}

	public int addProductService(Product product) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.addProductDAO(product);
	}

	public int addtoCartService(ShopingCart sCart) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.addtoCartDAO(sCart);
	}

	public int addOrderService(Orders order) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.addOrderDAO(order);
	}

	public void deleteFromCartService(Object attribute) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		cDao.deleteFromCartDAO(attribute);
		
	}

	public int findMaxOrderService() {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.findMaxOrderDAO();
	}

	public List<Orders> findTotalProductService(Object attribute, int order_no) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.findTotalProductDAO(attribute,order_no);
	}

	public int updateOrderStatusService(String orderId) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.updateOrderStatusDAO(orderId);
	}

	public int updateProductService(String attribute, int productId,int quantity) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.updateProductDAO(attribute,productId,quantity);
	}

	public String adminLoginService(String email, String pass) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.adminLoginDAO(email,pass);
	}

	public Customer customerLoginService(String email, String pass) {
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		return cDao.customerLoginDAO(email,pass);
	}

	public int addReview(review rev) {
		// TODO Auto-generated method stub
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		
		return cDao.reviewDAO(rev);
	}

	public int subcomservice(complaint com) {
		// TODO Auto-generated method stub
		CustomerDAOInterface cDao=DaoFactory.getObject("customer");
		
		return cDao.complaintDAO(com);
	}

}

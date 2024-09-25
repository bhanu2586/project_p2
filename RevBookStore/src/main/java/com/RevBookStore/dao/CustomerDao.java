package com.RevBookStore.dao;

import java.util.List;


import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.RevBookStore.entity.Favorite;
import com.RevBookStore.entity.Products;
import com.RevBookStore.entity.Review;
import com.RevBookStore.entity.ShoppingCart;
import com.RevBookStore.entity.User;
import com.RevBookStore.entity.orders;

@Repository
public class CustomerDao implements CustomerDaoInterface{

	@Autowired
	private SessionFactory sf;
	@Override
	public List<ShoppingCart> viewCartProducts(Long customerId) {
		System.out.println(customerId);
		Session ss = sf.openSession();
		Query q = ss.createQuery("from com.RevBookStore.entity.ShoppingCart sc where sc.customerId=: Id");
		q.setParameter("Id", customerId);
		List<ShoppingCart> list = q.getResultList();
		System.out.println(list);
		return list;
	}
	@Override
	public ShoppingCart findCartByCustomerId(Long customerId) {
		try {
			Session ss = sf.openSession();
			 ShoppingCart c =ss.createQuery("select c from ShoppingCart c where c.customerId = :customerId",
					ShoppingCart.class).setParameter("customerId", customerId).getSingleResult();
			 System.out.println(c);
			 return c;
		} catch (NoResultException e) {
			return null; // No cart found
		}
	}
	@Override
	public boolean removeProductFromCart(Long customerId, Long productId) {
		boolean b = false;
		System.out.println(customerId);
		System.out.println(productId);
		System.out.println(productId);
		Session ss = sf.openSession();
		Transaction et=ss.getTransaction();
		et.begin();

		/* ShoppingCart products = findCartByCustomerId(customerId); */

		/*
		 * if (products == null) { return b; } else {
		 */
			Query query = ss.createQuery(
					"delete from  ShoppingCart ci where ci.customerId = :customerId and ci.id = :productId");
			query.setParameter("customerId", customerId);
			query.setParameter("productId", productId);
			int i =query.executeUpdate();
			if(i>0) {
			b = true;
			}
			et.commit();
		return b;
	}
	@Override
	public List<Products> viewProducts() {
		Session ss = sf.openSession();
		Query q = ss.createQuery("from com.RevBookStore.entity.Products p");
		List<Products> list = q.getResultList();
		return list;
	}
	@Override
	public Products viewProductDeatils(Long id) {
		
		System.out.println(id);
		/* id=15L; */
		Session ss = sf.openSession();
		Transaction et=ss.getTransaction();
		et.begin();
		Query q = ss.createQuery("from com.RevBookStore.entity.Products p where p.id=:id");
		q.setParameter("id", id);
		
		Products product = (Products) q.getSingleResult();
		System.out.println(product.getName());
		return product;
	}
	@Override
	public int updateQuantity(Long productId, Long customerId,String quantity) {
		// TODO Auto-generated method stub
		System.out.println(productId);
		System.out.println(quantity);
		Session ss = sf.openSession();
		Transaction et = ss.getTransaction();
		et.begin();
		int query = ss.createQuery("update ShoppingCart sc set sc.quantity =: quantity  where sc.productId =: productId and sc.customerId =: customerId").setParameter("quantity", quantity).setParameter("productId", productId).setParameter("customerId", customerId).executeUpdate();
		et.commit();
		return query;
	}
	@Override
	@Transactional
	public boolean addProductToFavorite(Long productId, Long customerId) {
		// TODO Auto-generated method stub
		try {
			Session ss = sf.openSession();
			Transaction et=ss.getTransaction();
			et.begin();
			long count = isProductAlreadyFavorite(customerId, productId); 

		if (count ==0) {
				
				System.out.println(customerId);
				Favorite favo = new Favorite();
				Query q = ss.createQuery("from com.RevBookStore.entity.Products p where p.id=: productId");
				q.setParameter("productId",productId );
				Products product = (Products) q.getSingleResult();
				System.out.println(product.getName());
				favo.setProductId(product.getId());
				favo.setCustomerId(customerId);
				favo.setProductName(product.getName());
				favo.setPrice(product.getPrice());
				favo.setDiscounted_price(product.getDiscount_price());
				System.out.println(favo.getId());
				ss.persist(favo);
				et.commit();
				

			} 

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public long isProductAlreadyFavorite(Long customerId, Long productId) {
	    Session session = null;
	    Long count = 0L;

	    try {
	        session = sf.openSession();
	        Query query = session.createQuery(
	                "select count(id) from Favorite f where f.customerId = :customerId and f.productId = :productId", Favorite.class);
	        query.setParameter("customerId", customerId);
	        query.setParameter("productId", productId);
	        count = (long) query.getSingleResult();
	        System.out.println("c"+count);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return count; // Return true if count is greater than 0
	}
	@Override
	public List<Favorite> viewFavorite(Long customerId) {
		// TODO Auto-generated method stub
		Session ss = sf.openSession();
		System.out.println(customerId);
		List<Favorite> favo = ss.createQuery("select f from Favorite f where f.customerId = :customerId").setParameter("customerId", customerId).getResultList();
		return favo;
	}
	
	public boolean removeFromFavorite(Long customerId, Long productId) {
		// TODO Auto-generated method stub
		Session ss = sf.openSession();
		System.out.println(customerId);
		System.out.println(productId);
		
		boolean b = false;
		Transaction et = ss.getTransaction();
		et.begin();
		Query query= ss.createQuery("delete from Favorite f where f.customerId = :customerId and f.id = :productId");
		query.setParameter("customerId", customerId);
		query.setParameter("productId",productId);
		int i =query.executeUpdate();
		if(i>0)
		{
			b=true;
		}
		et.commit();
		return b;
	}
	@Override
	public boolean addProductsToCarts(Long productId, Long customerId) {
		try 
        {
			System.out.println(productId);
			System.out.println(customerId);
            Session ss = sf.openSession();
            Transaction et = ss.getTransaction();
            et.begin();
            Query q = ss.createQuery("from com.RevBookStore.entity.Products p where p.id=: productId ");
            q.setParameter("productId", productId);
            Products product = (Products) q.getSingleResult();
            ShoppingCart sc=new ShoppingCart();
            sc.setProductId(productId);
            sc.setCustomerId(customerId);
            sc.setName(product.getName());
            sc.setDescription(product.getDescription());
            sc.setQuantity("1");
            sc.setPrice(product.getDiscount_price());
            ss.persist(sc);
            et.commit();
            return true;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
	}
	@Override
	public int submitreview(Review rev) {
		Session ss=sf.openSession();  //for hibernate
		
		System.out.println(rev.getProductId());
		System.out.println(rev.getReviewText());
		System.out.println(rev.getUserId());
		
		Transaction et=ss.getTransaction(); 
		et.begin();
		
		ss.save(rev);
		
		
		
		 et.commit(); 
		
		return 1;
	}
	public User checkout(Long customerId) {
		Session ss=sf.openSession(); 
		Transaction et = ss.getTransaction();
		et.begin();
		
		Query q = ss.createQuery("from com.RevBookStore.entity.User where userId=:customerId");
		q.setParameter("customerId",customerId );
		User user =(User) q.getSingleResult();
		return user;
	
	
	}



	
	public List<ShoppingCart> getcartdetails(Long customerId) {
		// TODO Auto-generated method stub
		Session ss=sf.openSession(); 
		Transaction et = ss.getTransaction();
		et.begin();
		Query query = ss.createQuery("from com.RevBookStore.entity.ShoppingCart where customerId=:customerId");
		query.setParameter("customerId", customerId);
		List<ShoppingCart> ll = query.getResultList();
		
		return ll;
	}
	@Override
	public int saveorder(orders order) {
		// TODO Auto-generated method stub
		Session ss=sf.openSession(); 
		Transaction et = ss.getTransaction();
		et.begin();
		 ss.persist(order);
		 et.commit();
		
		
		return 1;
	}
}

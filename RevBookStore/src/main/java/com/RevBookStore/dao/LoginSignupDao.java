package com.RevBookStore.dao;



import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.RevBookStore.entity.User;
@Repository
public class LoginSignupDao implements LoginSignupDaoInterface {

	@Autowired
	private SessionFactory sf;
	@Override
	public boolean signup(User user) {
		Session ss = sf.openSession();
		Transaction et = ss.getTransaction();
		et.begin();
		
		ss.save(user);
		
		et.commit();
		return true;
	}
	@Override
	public User login(User user) {
		
		Session ss = sf.openSession();
		Transaction et =ss.getTransaction();
		et.begin();
		Query q =ss.createQuery("FROM User u WHERE u.email = :email AND u.password = :password");
		q.setParameter("email", user.getEmail());
		q.setParameter("password", user.getPassword());
		
		
		User users = (User) q.getSingleResult();
		System.out.println(users.getUserType());
		return users;
	}

}

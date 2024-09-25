package com.RevBookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RevBookStore.dao.LoginSignupDaoInterface;
import com.RevBookStore.entity.User;
@Service
public class LoginSignupService implements LoginSignupServiceInterface {
	@Autowired
	private LoginSignupDaoInterface ldao;
	
	@Override
	public boolean signup(User user) {
		return ldao.signup(user);
	}

	@Override
	public User login(User user) {
	
		return ldao.login(user);
	}

}

package com.RevBookStore.dao;

import com.RevBookStore.entity.User;

public interface LoginSignupDaoInterface {

	boolean signup(User user);

	User login(User user);

}

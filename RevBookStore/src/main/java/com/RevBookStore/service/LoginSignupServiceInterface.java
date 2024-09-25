package com.RevBookStore.service;

import com.RevBookStore.entity.User;

public interface LoginSignupServiceInterface {

	boolean signup(User user);

	User login(User user);

}

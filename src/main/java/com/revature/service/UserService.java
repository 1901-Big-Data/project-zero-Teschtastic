package com.revature.service;

import java.util.Optional;

import com.revature.dao.UserDao;
import com.revature.dao.UserOracle;
import com.revature.model.User;

public class UserService {
	private static UserService userService;
	final static UserDao userDao = UserOracle.getDao();
	
	private UserService() {
		
	}
	
	public static UserService getService() {
		if(userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	
	public Optional<Boolean> login(User user) throws Exception {
		return null;
	}
	
	public Optional<User> createUser(User user){
		return userDao.createUser(user);
	}
}

package com.revature.service;

import java.util.Optional;
import java.util.Scanner;

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
	
	public Optional<User> login(User user, Scanner scan) throws Exception {
		return userDao.login(user, scan);
	}
	
	public Optional<User> createUser(Scanner scan){
		return userDao.createUser(scan);
	}
}

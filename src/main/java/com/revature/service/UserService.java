package com.revature.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.dao.UserOracle;
import com.revature.model.Account;
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
	
	public Optional<User> login(Scanner scan) throws NoSuchElementException {
		return userDao.login(scan);
	}
	
	public Optional<Boolean> createUser(Scanner scan) {
		return userDao.createUser(scan);
	}
	
	public Optional<Boolean> deleteUser(Scanner scan) {
		return userDao.deleteUser(scan);
	}

	public Optional<List<User>> viewUsers(User user, Account account, Scanner scan) {
		return userDao.viewUsers(user, account, scan);
	}
}

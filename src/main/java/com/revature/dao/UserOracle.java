package com.revature.dao;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;

import com.revature.util.ConnectionUtil;
import com.revature.model.User;

public class UserOracle implements UserDao {
	private static UserOracle instance;
	
	private UserOracle() {
		
	}
	
	public static UserDao getDao() {
		if(instance == null) {
			instance = new UserOracle();
		}
		return instance;
	}

	@Override
	public Optional<User> createUser(User user) {
		Connection con = ConnectionUtil.getConnection();
		Scanner scan = new Scanner(System.in);
		
		if(con == null) {
			scan.close();
			return Optional.empty();
		}
		
		String username = "", passwordI = "", passwordII = "";
		System.out.print("Enter your username: ");
		username = scan.next();
		
		do {
			System.out.print("Enter your password: ");
			passwordI = scan.next();
			System.out.print("Re-Enter your password: ");
			passwordII = scan.next();
		} while(!passwordI.equals(passwordII));

		System.out.println(username + " " + passwordI);
		user.setUsername(username);
		user.setPassword(passwordI);
		
		username = null; passwordI = null; passwordII = null;
		scan.close();
		
		return Optional.of(user);
	}

	@Override
	public Optional<Boolean> login(User user) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		Scanner scan = new Scanner(System.in);
		
		if(con == null) {
			scan.close();
			return Optional.empty();
		}
		
		String username = "", pass = "";
		
		System.out.print("Enter your username: ");
		username = scan.next();
		System.out.print("Enter your password: ");
		pass = scan.next();
		
		if(user.getUsername().equals(username) && user.getPassword().equals(pass)) {
			scan.close();
			return Optional.of(true);
		} else if (user.getUsername().equals(username) && !user.getPassword().equals(pass)) {
			System.out.println("Incorrect password");
			scan.close();
			return Optional.of(false);
		}
		
		System.out.println("User does not exist");
		user = null; pass = null;
		scan.close();
		return Optional.of(false);
	}

}

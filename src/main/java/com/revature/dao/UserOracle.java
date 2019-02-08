package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
//import java.util.NoSuchElementException;
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
	public Optional<User> createUser(Scanner scan) {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		try {
			String username = "", passwordI = "", passwordII = "";
			System.out.print("Enter your username: ");
			username = scan.next();
		
			do {
				System.out.print("Enter your password: ");
				passwordI = scan.next();
				System.out.print("Re-Enter your password: ");
				passwordII = scan.next();
			} while(!passwordI.equals(passwordII));
		
			String sql = "call addUser(?,?,?,?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, passwordI);
			cs.setInt(3, 0);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			
			Integer id = cs.getInt(4);
			
			User user = new User(id,username, passwordI, false);

			System.out.println(user.getUsername() + " has been created.");
			return Optional.of(user);
			
		} catch (SQLException e) {
			return Optional.empty();
		
		}
	}

	// if user doesn't exist throw exception to user
	// empty optional for sqlexception
	// 
	@Override
	public Optional<User> login(User user, Scanner scan) throws Exception {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String username = "", pass = "";
		
		System.out.print("Enter your username: ");
		username = scan.next();
		System.out.print("Enter your password: ");
		pass = scan.next();
		
		if(user.getUsername().equals(username) && user.getPassword().equals(pass)) {
				
				return Optional.of(user);
		} else {
			System.out.println("Could not log in.");
			return Optional.empty();
		}
		
		//user = null; pass = null;
		//return Optional.empty();
	}

	@Override
	public Optional<Boolean> deleteUser(User user, Scanner scan) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.NoSuchElementException;
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

	// maybe make this return a boolean
	@Override
	public Optional<Boolean> createUser(Scanner scan) {
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
			
			return Optional.of(true);
		} catch (SQLException e) {
			return Optional.of(false);
		
		}
	}

	// if user doesn't exist throw exception to user
	// empty optional for sqlexception
	// 
	@Override
	public Optional<User> login(Scanner scan) throws NoSuchElementException {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String username = "", pass = "";
		
		System.out.print("Enter your username: ");
		username = scan.next();
		System.out.print("Enter your password: ");
		pass = scan.next();
		
		
		try {
			String sql = "call login(?,?,?,?,?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, pass);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.registerOutParameter(5, Types.INTEGER);
			cs.execute();
			
			Integer success = cs.getInt(3);
			Integer id = cs.getInt(4);
			Integer admin = cs.getInt(5);
			
			try {
				if (success == 0) {
					//throw new NoSuchElementException("Invalid username / password combination");
					System.out.println("Invalid username / password combination");
				} else {
					User user = new User(id, username, "", (admin > 0) ? 1 : 0);
					
					return Optional.of(user);
				}
			} catch(NoSuchElementException e) {
				
				
			}
			return Optional.empty();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<Boolean> deleteUser(Scanner scan) {
Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String username = "", pass = "";
		
		System.out.print("Enter your username: ");
		username = scan.next();
		System.out.print("Enter your password: ");
		pass = scan.next();
		
		try {
		
			String sql = "call deleteUser(?,?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, pass);
			cs.execute();
			
			return Optional.of(true);
		} catch (SQLException e) {
			return Optional.of(false);
		
		}
	}

}

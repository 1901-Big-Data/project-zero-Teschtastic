package com.revature.dao;

import java.sql.Connection;
import java.util.Optional;

import com.revature.util.ConnectionUtil;

public class UserOracle implements UserDao {
	private static UserOracle instance;
	
	private UserOracle() {
		
	}
	
	public UserDao getDao() {
		if(instance == null) {
			instance = new UserOracle();
		}
		return instance;
	}
	
	@Override
	public Optional<Boolean> login() throws Exception {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		
		
		return null;
	}

}

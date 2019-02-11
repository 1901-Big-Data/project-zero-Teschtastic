package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class AccountOracle implements AccountDao {
	private static AccountOracle instance;
	private static final Logger log = LogManager.getLogger(AccountOracle.class);
	
	private AccountOracle() {
		
	}
	
	public static AccountDao getDao() {
		if(instance == null) {
			instance = new AccountOracle();
		}
		return instance;
	}

	@Override
	public Optional<Account> createAccount(Scanner scan, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Boolean> viewAccount(User user, Account account, Scanner scan) {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String username = "";
		
		username = user.getUsername();
		
		
		try {

			log.error("Trying");
			String sql = "call viewAccount(?,?,?)";
			log.error("Still Trying");
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.registerOutParameter(3, Types.DOUBLE);
			cs.execute();
			log.error("continued tries");
			
			//String type = cs.getString(2);
			Double bal = cs.getDouble(3);
			
			//account.setAccountType(type);
			account.setBalance(bal);
			log.error("Last try");
			
			System.out.println("The account of " + username + "'s balance is: " + bal);
			
			return Optional.of(true);
		} catch (SQLException e) {
			log.error("Couldn't get balance " + e);
			return Optional.of(false);
		
		}
	}

	@Override
	public Optional<Boolean> depositInto(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Boolean> withdrawFrom(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}

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
	public Optional<Boolean> createAccount(Scanner scan, User user) {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String accType, startBal;
		
		//do {
			System.out.print("Enter account type (savings or checking): ");
			accType = scan.next();
			accType = accType.toLowerCase();
		//} while(!accType.equals("savings") || !accType.equals("checking"));
		
		do {
			System.out.print("Enter starting balance: ");
			startBal = scan.next();
		} while(Double.parseDouble(startBal) < 0.0);
		
		try {
			
			String sql = "call addAccount(?,?,?,?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, user.getUsername());
			cs.setDouble(2, Double.parseDouble(startBal));
			cs.setString(3, accType);
			cs.execute();
			
			return Optional.of(true);
		} catch (SQLException e) {
			return Optional.of(false);
		
		}
	}

	@Override
	public Optional<Boolean> deleteAccount(Scanner scan, User user) {
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
		
			String sql = "call deleteAccount(?,?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, pass);
			cs.execute();
			
			return Optional.of(true);
		} catch (SQLException e) {
			return Optional.of(false);
		
		}
	}

	@Override
	public Optional<Account> viewAccount(User user, Account account, Scanner scan) {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String username = "";
		
		username = user.getUsername();
		
		
		try {

			//log.error("Trying");
			String sql = "call viewBalance(?,?,?)";
			//log.error("Still Trying");
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, username);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.DOUBLE);
			cs.execute();
			//log.error("continued tries");
			
			String type = cs.getString(2);
			Double bal = cs.getDouble(3);
			
			account.setAccountType(type);
			account.setBalance(bal);
			account.setAccountUsername(username);
			//log.error("Last try");
			
			return Optional.of(account);
		} catch (SQLException e) {
			log.error("Couldn't get balance " + e);
			return Optional.empty();
		
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

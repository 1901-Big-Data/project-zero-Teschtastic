package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
		int id = 0;
		
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
			cs.setInt(4, id);
			cs.execute();
			
			return Optional.of(true);
		} catch (SQLException e) {
			log.error("No accounty wounty" + e);
			return Optional.of(false);
		
		}
	}

	@Override
	public Optional<Boolean> deleteAccount(Scanner scan, User user, Account account) {
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
			if(account.getBalance() <= 0.0 || user.getIsAdmin() > 0) {
				String sql = "call deleteAccount(?,?)";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, username);
				cs.setString(2, pass);
				cs.execute();
			
				return Optional.of(true);
			} else {
				System.out.println("Can't delete account with a non-zero balance.\n");
				return Optional.of(false);
				
			}
		} catch (SQLException e) {
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

	@Override
	public Optional<List<Account>> viewAccounts(User user, Account account, Scanner acan) {
		Connection connect = ConnectionUtil.getConnection();
			
		if (connect == null) {
			return Optional.empty();			
		}
		try {
			String sql = "select * from accounts where acc_user = ? order by account_id";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ResultSet rs = ps.executeQuery();

			//log.error("Error in query");
			List<Account> accountList = new ArrayList<Account>();
			while (rs.next()) {
				//log.error("Error in accounts adding");
				account.setAccountID(rs.getInt("account_id"));
				account.setAccountType(rs.getString("type_account"));
				account.setBalance(rs.getDouble("account_bal"));
				account.setAccountUsername(rs.getString("acc_user"));
				accountList.add(account);
			}
			
			return Optional.of(accountList);
		} catch (Exception e) {
			System.out.println("Couldn't fetch accounts.");
			return Optional.empty();
		}
	}
}

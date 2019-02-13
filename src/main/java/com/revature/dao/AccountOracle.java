package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
			log.error("Database Error");
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
		Integer id;
		Double bal;
		
		System.out.print("Enter your username: ");
		username = scan.next();
		System.out.print("Enter your password: ");
		pass = scan.next();
		System.out.print("Enter your account id: ");
		id = scan.nextInt();
		
		try {
			String sql1 = "call getAccount(?,?,?)";
			CallableStatement cs1 = con.prepareCall(sql1);
			cs1.setString(1, username);
			cs1.setInt(2, id);
			cs1.registerOutParameter(3, Types.DOUBLE);
			//log.error("Error deleting " + cs1);
			cs1.execute();
			
			bal = cs1.getDouble(3);
			
			account.setBalance(bal);
			
			//log.error("Error deleting " + account.getBalance());
			
			if(account.getBalance() <= 0.0 || user.getIsAdmin() > 0) {
				String sql2 = "call deleteAccount(?,?,?)";
				CallableStatement cs = con.prepareCall(sql2);
				cs.setString(1, username);
				cs.setString(2, pass);
				cs.setInt(3, id);
				//log.error("Error deleting " + cs);
				cs.execute();
			
				return Optional.of(true);
			} else {
				System.out.println("Can't delete account with a non-zero balance.\n");
				return Optional.of(false);
				
			}
		} catch (SQLException e) {
			log.error("Database Error");
			return Optional.of(false);
		
		}
	}

	@Override
	public Optional<Boolean> depositInto(Account account, Scanner scan) {
		Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String username = "", id = "";
		Double bal;
		
		System.out.print("Enter your username: ");
		username = scan.next();
		System.out.print("Enter your account id: ");
		id = scan.next();
		System.out.print("Enter amount to deposit: ");
		bal = scan.nextDouble();
		
		if(bal < 0.0)
			bal = bal * -1;
		
		try {
			
				String sql = "call changeBalance(?,?,?)";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, username);
				cs.setDouble(2, bal);
				cs.setString(3, id);
				cs.execute();
			
				return Optional.of(true);
		} catch (SQLException e) {
			log.error("Database Error");
			return Optional.of(false);
		
		}
	}

	@Override
	public Optional<Boolean> withdrawFrom(Account account, Scanner scan) {
Connection con = ConnectionUtil.getConnection();
		
		if(con == null) {
			return Optional.empty();
		}
		
		String username = "", id = "";
		Double bal, bal1;
		
		System.out.print("Enter your username: ");
		username = scan.next();
		System.out.print("Enter your account id: ");
		id = scan.next();
		System.out.print("Enter amount to withdraw: ");
		bal = scan.nextDouble();
		
		try {
			String sql1 = "select account_bal from accounts where acc_user = ? and account_id = ?";
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1, username);
			ps.setInt(2, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();

			//log.error("sql error");
			rs.next();
			bal1 = rs.getDouble("account_bal");
			

			//log.error("account error" + bal1);
			if(Double.compare(bal1, bal) > 0.0) {
				bal = bal * -1;
				//log.error("account error :O");
				String sql = "call changeBalance(?,?,?)";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, username);
				cs.setDouble(2, bal);
				cs.setString(3, id);
				cs.execute();
				return Optional.of(true);
			} else {
				return Optional.of(false);
			}
		} catch (SQLException e) {
			log.error("Database Error" + e);
			return Optional.of(false);
		
		}
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
				System.out.println(account.toString());
				accountList.add(account);
			}
			
			return Optional.of(accountList);
		} catch (Exception e) {
			log.error("Database Error");
			return Optional.empty();
		}
	}
}

package com.revature.service;

import java.util.Optional;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountOracle;
import com.revature.model.Account;
import com.revature.model.User;

public class AccountService {
	private static AccountService service;
	final static AccountDao accountDao = AccountOracle.getDao();
	
	private AccountService() {
		
	}
	
	public static AccountService getService() {
		if(service == null) {
			service = new AccountService();
		}
		return service;
	}

	public Optional<Boolean> createAccount(Scanner scan, User user) {
		return accountDao.createAccount(scan, user);
	}
	
	public Optional<Boolean> deleteAccount(Scanner scan, User user) {
		return accountDao.deleteAccount(scan, user);
	}
	
	public Optional<Account> viewAccount(User user, Account account, Scanner scan) {
		return accountDao.viewAccount(user, account, scan);
	}
	
	public Optional<Boolean> depositInto(Account account) {
		return accountDao.depositInto(account);
	}
	
	public Optional<Boolean> withdrawFrom(Account account) {
		return accountDao.withdrawFrom(account);
	}
}

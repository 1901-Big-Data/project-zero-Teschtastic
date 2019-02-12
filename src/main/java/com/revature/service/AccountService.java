package com.revature.service;

import java.util.List;
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
	
	public Optional<Boolean> deleteAccount(Scanner scan, User user, Account account) {
		return accountDao.deleteAccount(scan, user, account);
	}

	public Optional<List<Account>> viewAccounts(User user, Account account, Scanner scan) {
		return accountDao.viewAccounts(user, account, scan);
	}
	
	public Optional<Boolean> depositInto(Account account, Scanner scan) {
		return accountDao.depositInto(account, scan);
	}
	
	public Optional<Boolean> withdrawFrom(Account account, Scanner scan) {
		return accountDao.withdrawFrom(account, scan);
	}
}

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
	
	Optional<Account> createAccount(Scanner scan, User user) {
		return accountDao.createAccount(scan, user);
	}
	
	Optional<Double> viewAccount(Account account) {
		return accountDao.viewAccount(account);
	}
	
	Optional<Boolean> depositInto(Account account) {
		return accountDao.depositInto(account);
	}
	
	Optional<Boolean> withdrawFrom(Account account) {
		return accountDao.withdrawFrom(account);
	}
}

package com.revature.dao;

import java.util.Optional;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.User;

public class AccountOracle implements AccountDao {
	private static AccountOracle instance;
	
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
	public Optional<Double> viewAccount(User user, Account account) {
		String username = user.getUsername();
		account.setAccountUsername(username);
		return Optional.of(account.getBalance());
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

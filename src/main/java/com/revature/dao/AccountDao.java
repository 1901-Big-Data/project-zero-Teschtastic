package com.revature.dao;

import java.util.List;
//import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.User;

public interface AccountDao {
	Optional<Boolean> createAccount(Scanner scan, User user);
	Optional<Boolean> deleteAccount(Scanner scan, User user, Account account);
	Optional<List<Account>> viewAccounts(User user, Account account, Scanner scan);
	Optional<Boolean> depositInto(Account account, Scanner scan);
	Optional<Boolean> withdrawFrom(Account account, Scanner scan);

}

package com.revature.dao;

//import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.User;

public interface AccountDao {
	Optional<Boolean> createAccount(Scanner scan, User user);
	Optional<Boolean> deleteAccount(Scanner scan, User user);
	Optional<Account> viewAccount(User user, Account account, Scanner scan);
	Optional<Boolean> depositInto(Account account);
	Optional<Boolean> withdrawFrom(Account account);

}

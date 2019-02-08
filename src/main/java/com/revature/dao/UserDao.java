package com.revature.dao;

import java.util.Optional;
import java.util.Scanner;

import com.revature.model.User;

public interface UserDao {
	Optional<User> login(User user, Scanner scan) throws Exception;
	Optional<Boolean> deleteUser(User user, Scanner scan);
	Optional<User> createUser(Scanner scan);
}

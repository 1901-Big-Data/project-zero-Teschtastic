package com.revature.dao;

import java.util.Optional;
import java.util.Scanner;

import com.revature.model.User;

public interface UserDao {
	Optional<User> login(Scanner scan) throws Exception;
	Optional<Boolean> deleteUser(Scanner scan);
	Optional<Boolean> createUser(Scanner scan);
}

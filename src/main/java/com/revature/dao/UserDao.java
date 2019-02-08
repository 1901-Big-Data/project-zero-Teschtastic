package com.revature.dao;

import java.util.Optional;

import com.revature.model.User;

public interface UserDao {
	Optional<Boolean> login(User user) throws Exception;
	Optional<User> createUser(User user);
}

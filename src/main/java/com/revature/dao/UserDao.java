package com.revature.dao;

import java.util.Optional;

public interface UserDao {
	Optional<Boolean> login() throws Exception;
	
}

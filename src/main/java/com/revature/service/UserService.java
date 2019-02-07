package com.revature.service;

import java.util.Optional;

public class UserService {
	private static UserService service;
	
	private UserService() {
		
	}
	
	public static UserService getService() {
		if(service == null) {
			service = new UserService();
		}
		return service;
	}
	
	public Optional<Boolean> login() throws Exception {
		return null;
	}
}

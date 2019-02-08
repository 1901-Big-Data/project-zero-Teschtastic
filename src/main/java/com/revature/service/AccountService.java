package com.revature.service;

public class AccountService {
	private static AccountService service;
	
	private AccountService() {
		
	}
	
	public static AccountService getService() {
		if(service == null) {
			service = new AccountService();
		}
		return service;
	}

}

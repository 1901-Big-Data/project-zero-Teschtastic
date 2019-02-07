package com.revature.service;

public class BankService {
	private static BankService service;
	
	private BankService() {
		
	}
	
	public static BankService getService() {
		if(service == null) {
			service = new BankService();
		}
		return service;
	}

}

package com.revature.bank;

public class Users {
	
	private String username;
	private String password;
	private Double balance;
	
	public Users() {
		
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Double getBalance() {
		return balance;
	}

	public void setUserName(String user) {
		this.username = user;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setBalance(Double amt) {
		this.balance = amt;
	}

}

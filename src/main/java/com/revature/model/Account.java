package com.revature.model;

import java.io.Serializable;

public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7356758073368256484L;
	private Double balance;
	private Integer accountID;
	private String accountType;
	private String accountUsername;
	
	public Account() {
		super();
	}

	public Account(Double balance, Integer accountID, String accountType, String accountUsername) {
		super();
		this.balance = balance;
		this.accountID = accountID;
		this.accountType = accountType;
		this.accountUsername = accountUsername;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountID == null) ? 0 : accountID.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((accountUsername == null) ? 0 : accountUsername.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID == null) {
			if (other.accountID != null)
				return false;
		} else if (!accountID.equals(other.accountID))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (accountUsername == null) {
			if (other.accountUsername != null)
				return false;
		} else if (!accountUsername.equals(other.accountUsername))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accountID=" + accountID + ", accountType=" + accountType
				+ ", accountUsername=" + accountUsername + "]";
	}
	
}

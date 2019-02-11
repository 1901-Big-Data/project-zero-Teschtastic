package com.revature.application;

import java.util.*;

import com.revature.model.User;
import com.revature.model.Account;
import com.revature.service.UserService;
import com.revature.service.AccountService;

public class Bank {
	public static void main(String[] args) throws Exception {
		Scanner mainScan = new Scanner(System.in), userScan = new Scanner(System.in), adminScan = new Scanner(System.in), scan = new Scanner(System.in);
		String mainChoice = "", userChoice = "", adminChoice = "";
		UserService userService = UserService.getService();
		AccountService accountService = AccountService.getService();
		User users = new User();
		Account accounts = new Account();
		
		System.out.println("Welcome to Revature Banking.");
		
		mainMenuChoice(users, accounts, mainChoice, userChoice, adminChoice, mainScan, userScan, adminScan, userService, accountService, scan);
		
		mainScan.close();
		userScan.close();
		adminScan.close();
		scan.close();
		return;
	}
	
	public static void mainMenu() {
		System.out.println("\nMain Menu\n");
		System.out.println("1. Create new user");
		System.out.println("2. Log In");
		System.out.println("0. Exit\n");	
	}
	
	public static void mainMenuChoice(User users, Account accounts, String mainChoice, String userChoice,
								String adminChoice, Scanner mainScan, Scanner userScan, Scanner adminScan, UserService userService, AccountService accountService,
								Scanner scan) throws Exception {
		
		do {
			mainMenu();
			mainChoice = mainScan.next();
		
			switch(mainChoice) {
				case "1":
					if(userService.createUser(scan).get())
						System.out.println("New user has been created.");
					else
						System.out.println("User could not be created.");
					break;
			
				case "2":
					users = userService.login(scan).get();
					if(users.getIsAdmin() == 0) {
						userMenuChoice(users, accounts, userChoice, userScan, userService, accountService, scan);
					} else {
						adminMenuChoice(users, accounts, adminChoice, adminScan, userService, accountService, scan);
					}
					break;
				
				case "0":
					System.out.println("Exiting program.\nHave a nice day!");
					break;
		
				default:
					System.out.println("Not a valid choice\n");
					break;
			}
		} while(!mainChoice.equals("0"));
	}
	
	public static void userMenu() {
		System.out.println("\nUser Menu\n");
		System.out.println("1. View account balance");
		System.out.println("2. Deposit into account");
		System.out.println("3. Withdraw from account");
		System.out.println("4. Create account");
		System.out.println("5. Delete account");
		System.out.println("0. Log out\n");
	}
	
	private static void userMenuChoice(User users, Account accounts, String userChoice, Scanner userScan, 
									UserService userService, AccountService accountService, Scanner scan) {
		
		do {
			userMenu();
			userChoice = userScan.next();
		
			switch(userChoice) {
				case "1":
					accounts = accountService.viewAccount(users, accounts, scan).get();
					System.out.println(accounts.getAccountUsername() + "'s balance is: $" + accounts.getBalance());
					break;
			
				case "2":
					System.out.println("Depositing not implemented");
					break;
					
				case "3":
					System.out.println("Withdrawing not implemented");
					break;
					
				case "4":
					System.out.println("Creating account not implemented");
					break;
					
				case "5":
					if(accountService.deleteAccount(scan, users).get())
						System.out.println("\nAccount has been deleted.");
					break;
			case "0":
					System.out.println("Logging out.\n");
					break;
		
				default:
					System.out.println("Not a valid choice\n");
					break;
			}
		} while(!userChoice.equals("0"));
	}
	
	public static void adminMenu() {
		System.out.println("\nUser Menu\n");
		System.out.println("1. View all users");
		System.out.println("2. Create a user");
		System.out.println("3. Update a user");
		System.out.println("4. Delete user(s)");
		System.out.println("0. Log out\n");
	}
	
	private static void adminMenuChoice(User users, Account accounts, String adminChoice, Scanner adminScan, 
									UserService userService, AccountService accountService, Scanner scan) {
		do {
			adminMenu();
			adminChoice = adminScan.next();
			
			switch(adminChoice) {
			case "1":
				System.out.println("Viewing all users not implemented");
				break;
		
			case "2":
				System.out.println("Creating users not not implemented");
				break;
				
			case "3":
				System.out.println("Updating users not implemented");
				break;
				
			case "4":
				System.out.println("Deleting users not implemented");
				break;
		case "0":
				System.out.println("Logging out.\n");
				break;
	
			default:
				System.out.println("Not a valid choice\n");
				break;
		}
			
		} while(!adminChoice.equals("0"));
	}
	
}
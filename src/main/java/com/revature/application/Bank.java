package com.revature.application;

import java.util.*;

import com.revature.model.User;
//import com.revature.model.Account;
import com.revature.service.UserService;
//import com.revature.service.AccountService;

public class Bank {
	public static void main(String[] args) throws Exception {
		Scanner mainScan = new Scanner(System.in), userScan = new Scanner(System.in), scan = new Scanner(System.in);;
		String mainChoice = "", userChoice = "";
		UserService userService = UserService.getService();
		User users = new User();
		
		System.out.println("Welcome to revature Banking.");
		
		mainMenuChoice(users, mainChoice, userChoice, mainScan, userScan, userService, scan);
		
		mainScan.close();
		userScan.close();
		scan.close();
		return;
	}
	
	public static void mainMenu() {
		System.out.println("\nMain Menu\n");
		System.out.println("1. Create new user");
		System.out.println("2. Log In");
		System.out.println("0. Exit\n");	
	}
	
	public static void mainMenuChoice(User users, String mainChoice, String userChoice, Scanner mainScan, Scanner userScan, UserService userService, Scanner scan) throws Exception {
		
		do {
			mainMenu();
			mainChoice = mainScan.next();
		
			switch(mainChoice) {
				case "1":
					users = userService.createUser(scan).get();
					System.out.println(users);
					break;
			
				case "2":
					users = userService.login(users, scan).get();
					userMenuChoice(users, userChoice, userScan, userService);
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
		System.out.println("4. Delete account");
		System.out.println("0. Log out\n");
	}
	
	private static void userMenuChoice(User users, String userChoice, Scanner userScan, UserService userService) {
		
		do {
			userMenu();
			userChoice = userScan.next();
		
			switch(userChoice) {
				case "1":
					System.out.println("Account balance not implemented");
					break;
			
				case "2":
					System.out.println("Depositing not implemented");
					break;
					
				case "3":
					System.out.println("Withdrawing not implemented");
					break;
					
				case "4":
					System.out.println("Deleting not implemented");
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
//	
//	public static boolean deleteUser(Map<String, String> users, Scanner delUser) {
//		String del = "", userDel = "", passDel = "";
//		System.out.println("Confirmation\nDo you want to delete account? (y/n)");
//		
//		del = delUser.next().toLowerCase();
//		
//		if(del.equals("y")) {
//			
//			System.out.print("Enter your username: ");
//			userDel = delUser.next();
//			System.out.print("Enter your password: ");
//			passDel = delUser.next();
//			
//			if(users.containsKey(userDel) && users.containsValue(passDel)) {
//				System.out.println(users.toString() + " will now be deleted.");
//				users.remove(userDel, passDel);
//				return true;
//			} else if (users.containsKey(userDel) && !users.containsValue(passDel)) {
//				System.out.println("Incorrect password");
//				return false;
//			}
//		}
//		return false;
//	}
}
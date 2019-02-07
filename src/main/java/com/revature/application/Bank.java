package com.revature.application;

import java.util.*;

public class Bank {
	public static void main(String[] args) {
		Scanner mainScan = new Scanner(System.in), userScan = new Scanner(System.in), delUser = new Scanner(System.in);
		Map<String, String> users = new HashMap<String, String>();
		String mainChoice = "", userChoice = "";
		
		System.out.println("Welcome to revature Banking.");
		
		mainMenuChoice(users, mainChoice, userChoice, mainScan, userScan, delUser);
		
		mainScan.close();
		userScan.close();
		delUser.close();
		return;
	}
	
	public static void mainMenu() {
		System.out.println("\nMain Menu\n");
		System.out.println("1. Create new user");
		System.out.println("2. Log In");
		System.out.println("0. Exit\n");	
	}
	
	public static void mainMenuChoice(Map<String, String> users, String mainChoice, String userChoice, Scanner mainScan, Scanner userScan, Scanner delUser) {
		
		do {
			mainMenu();
			mainChoice = mainScan.next();
		
			switch(mainChoice) {
				case "1":
					users.putAll(createUser(mainScan, users));
					System.out.println(users);
					break;
			
				case "2":
					if(logIn(mainScan, users)) {
						userMenuChoice(users, userChoice, userScan, delUser);
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
	
	public static Map<String, String> createUser(Scanner scan, Map<String, String> users) {
		String username = "", passwordI = "", passwordII = "";
		System.out.print("Enter your username: ");
		username = scan.next();
		
		do {
			System.out.print("Enter your password: ");
			passwordI = scan.next();
			System.out.print("Re-Enter your password: ");
			passwordII = scan.next();
		} while(!passwordI.equals(passwordII));
		
		users.put(username, passwordI);
		username = null; passwordI = null; passwordII = null;
		
		return users;
	}

	public static boolean logIn(Scanner scan, Map<String, String> users) {
		String user = "", pass = "";
		
		System.out.print("Enter your username: ");
		user = scan.next();
		System.out.print("Enter your password: ");
		pass = scan.next();
		
		if(users.containsKey(user) && users.containsValue(pass)) {
			return true;
		} else if (users.containsKey(user) && !users.containsValue(pass)) {
			System.out.println("Incorrect password");
			return false;
		}
		
		System.out.println("User does not exist");
		user = null; pass = null;
		return false;
	}
	
	public static void userMenu() {
		System.out.println("\nUser Menu\n");
		System.out.println("1. View account balance");
		System.out.println("2. Deposit into account");
		System.out.println("3. Withdraw from account");
		System.out.println("4. Delete account");
		System.out.println("0. Log out\n");
	}
	
	private static void userMenuChoice(Map<String, String> users, String userChoice, Scanner userScan, Scanner delUser) {
		
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
					if(deleteUser(users, delUser))
						return;
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
	
	public static boolean deleteUser(Map<String, String> users, Scanner delUser) {
		String del = "", userDel = "", passDel = "";
		System.out.println("Confirmation\nDo you want to delete account? (y/n)");
		
		del = delUser.next().toLowerCase();
		
		if(del.equals("y")) {
			
			System.out.print("Enter your username: ");
			userDel = delUser.next();
			System.out.print("Enter your password: ");
			passDel = delUser.next();
			
			if(users.containsKey(userDel) && users.containsValue(passDel)) {
				System.out.println(users.toString() + " will now be deleted.");
				users.remove(userDel, passDel);
				return true;
			} else if (users.containsKey(userDel) && !users.containsValue(passDel)) {
				System.out.println("Incorrect password");
				return false;
			}
		}
		return false;
	}
}
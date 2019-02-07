package com.revature.application;

import java.util.*;

public class Bank {
	public static void main(String[] args) {
		Scanner mainScan = new Scanner(System.in), userScan = new Scanner(System.in);
		Map<String, String> users = new HashMap<String, String>();
		String mainChoice = "", userChoice = "";
		mainMenuChoice(users, mainChoice, userChoice, mainScan, userScan);
		mainScan.close();
		userScan.close();
		return;
	}
	
	public static void mainMenu() {
		System.out.println("\nMain Menu\n");
		System.out.println("1. Create new user");
		System.out.println("2. Log In");
		System.out.println("0. Exit\n");	
	}
	
	public static void mainMenuChoice(Map<String, String> users, String mainChoice, String userChoice, Scanner mainScan, Scanner userScan) {
		
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
						userMenuChoice(users, userChoice, userScan);
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
		String username = "", passwordI = "", passwordII = "", garb = "";
		
		garb = scan.nextLine();
		System.out.print("Enter your username: ");
		username = scan.nextLine();
		
		do {
			System.out.print("Enter your password: ");
			passwordI = scan.nextLine();
			System.out.print("Re-Enter your password: ");
			passwordII = scan.nextLine();
		} while(!passwordI.equals(passwordII));
		
		users.put(username, passwordI);
	
		garb = null; username = null; passwordI = null; passwordII = null;
		
		return users;
	}

	public static boolean logIn(Scanner scan, Map<String, String> users) {
		String user = "", pass = "", garb = "";
		
		garb = scan.nextLine();
		
		System.out.print("Enter your username: ");
		user = scan.nextLine();
		System.out.print("Enter your password: ");
		pass = scan.nextLine();
		
		if(users.containsKey(user) && users.containsValue(pass)) {
			return true;
		} else if (users.containsKey(user) && !users.containsValue(pass)) {
			System.out.println("Incorrect password");
			return false;
		}
		
		System.out.println("User does not exist");
		user = null; pass = null; garb = null;
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
	
	private static void userMenuChoice(Map<String, String> users, String userChoice, Scanner userScan) {
		
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
					System.out.println("Deleting account not implemented");
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
}
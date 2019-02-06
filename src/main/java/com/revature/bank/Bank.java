package com.revature.bank;

import java.util.*;

public class Bank {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Map<String, String> users = new HashMap<String, String>();
		int choice = 0;
		
		do {
			mainMenu();
			choice = scan.nextInt();
		
			switch(choice) {
				case 1:
					users.putAll(createUser(scan, users));
					System.out.println(users);
					break;
			
				case 2:
					if(logIn())
						userMenu();
					break;
				
				case 0:
					break;
		
				default:
					System.out.println("Not a valid choice\n");
					break;
			}
		} while(choice != 0);
		
		scan.close();
	}
	
	public static boolean logIn() {
		return true;
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
	
	public static void mainMenu() {
		System.out.println("\nMain Menu\n");
		System.out.println("1. Create new user");
		System.out.println("2. Log In");
		System.out.println("0. Exit\n");	
	}
	
	public static void userMenu() {
		System.out.println("\nUser Menu\n");
		System.out.println("1. View account balance");
		System.out.println("2. Deposit into account");
		System.out.println("3. Withdraw from account");
		System.out.println("4. Delete account");
		System.out.println("0. Log out\n");
	}
}

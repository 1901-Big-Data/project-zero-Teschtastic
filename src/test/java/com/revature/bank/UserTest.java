package com.revature.bank;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.Scanner;

import org.junit.Test;

import com.revature.model.User;
import com.revature.service.UserService;

public class UserTest {

	@Test
	public void testLoginPass() {
		UserService userService = UserService.getService();
		User trueUser = new User();
		trueUser.setUsername("tesch");
		trueUser.setPassword("123");
		trueUser.setId(5);
		trueUser.setIsAdmin(0);
		Scanner scan =  new Scanner(System.in);
		System.out.println("\nLogin pass test\nEnter tesch for username\nenter 123 for password\n");
		Optional<User> user = userService.login(scan);
		assertEquals(trueUser, user.get());
	}
	
	@Test
	public void testLoginFail() {
		UserService userService = UserService.getService();
		User trueUser = new User();
		trueUser.setUsername("tesch");
		trueUser.setPassword("123");
		trueUser.setId(5);
		trueUser.setIsAdmin(0);
		Scanner scan =  new Scanner(System.in);
		System.out.println("\nLogin fail test\nEnter tesch for username\nenter 124 for password\n");
		Optional<User> user = userService.login(scan);
		assertNotEquals(trueUser, user.get());
	}

	@Test
	public void testCreateUserPass() {
		UserService userService = UserService.getService();
		Scanner scan =  new Scanner(System.in);
		System.out.println("\nCreate user pass test\nEnter test for username\nenter 123 for passwords\n");
		assertTrue(userService.createUser(scan).get());
	}

	@Test
	public void testDeleteUserPass() {
		UserService userService = UserService.getService();
		Scanner scan =  new Scanner(System.in);
		System.out.println("\nDelete pass test\nEnter test for username\nenter 123 for passwords\n");
		assertTrue(userService.deleteUser(scan).get());
	}

	@Test
	public void testDeleteUserFail() {
		UserService userService = UserService.getService();
		Scanner scan =  new Scanner(System.in);
		System.out.println("\nDelete fail test\nEnter test for username\nenter 124 for password\n");
		assertFalse(userService.deleteUser(scan).get());
	}

}

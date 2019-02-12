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

		// enter in for user: tesch
		// enter for password: 123
		
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

		// enter in for user: 123
		// enter for password: 123
		
		Optional<User> user = userService.login(scan);
		assertNotEquals(trueUser, user.get());
	}

	@Test
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewUsers() {
		fail("Not yet implemented");
	}

}

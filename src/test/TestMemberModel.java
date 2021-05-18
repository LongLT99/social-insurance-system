package test;

import static org.junit.jupiter.api.Assertions.*;
import src.model.*;
import org.junit.jupiter.api.Test;

class TestMemberModel {
	Member mem = new Member();
	@Test
	void testGetId() {
		
		int expected = 0;
		int actual = mem.getId();
		assertEquals(expected, actual);
	}
	@Test
	void testGetUsername() {
		
		String expected = null;
		String actual = mem.getUsername();
		assertEquals(expected, actual);
	}
	@Test
	void testSetUsername() {
		String username = "hdhuong";
		
		mem.setUsername(username);
		String expected = "hdhuong";
		assertEquals(expected, mem.getUsername());
	}
	@Test
	void testGetPassword() {
		
		String expected = null;
		String actual = mem.getPassword();
		assertEquals(expected, actual);
	}
	@Test
	void testSetPassword() {
		String password = "123456";
		
		mem.setPassword(password);
		String expected = "123456";
		assertEquals(expected, mem.getPassword());
	}

	@Test
	void testGetEmail() {
		
		String expected = null;
		String actual = mem.getEmail();
		assertEquals(expected, actual);
	}
	@Test
	void testSetEmail() {
		String email = "hdhuong@gmail.com";
		
		mem.setEmail(email);
		String expected = "hdhuong@gmail.com";
		assertEquals(expected, mem.getEmail());
	}
	@Test
	void testGetPhoneNumber() {
		
		String expected = null;
		String actual = mem.getPhoneNumber();
		assertEquals(expected, actual);
	}
	@Test
	void testSetPhoneNumber() {
		String phoneNumber = "098912313";
		
		mem.setPhoneNumber(phoneNumber);
		String expected = "098912313";
		assertEquals(expected, mem.getPhoneNumber());
	}
	@Test
	void testGetRole() {
		
		String expected = null;
		String actual = mem.getRole();
		assertEquals(expected, actual);
	}
	@Test
	void testSetRole() {
		String role = "business";
		mem.setRole(role);
		String expected = "business";
		assertEquals(expected, mem.getRole());
	}
	

}

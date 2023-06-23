package com.example.demo; 

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.demo.AddressBook;

public class AddressBookTestSample {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetters() {
		try {
			PhoneNumber phoneNumber = new PhoneNumber("work","1234567890");
			Address address = new Address("home","hackerearth");
			Contact contact = new Contact("Hacker Boss","HackerEarth");//, phoneNumber, address);
			contact.addPhoneNumber(phoneNumber);
			contact.addAddress(address);
			assertEquals("Hacker Boss", contact.getName());
			assertEquals("HackerEarth", contact.getOrganisation());
			assertEquals("1234567890", phoneNumber.getPhoneNumber());
			assertEquals("1234567890", contact.getPhoneNumbers().get(0).getPhoneNumber());
			assertEquals("hackerearth", address.getAddress());
			assertEquals("hackerearth", contact.getAddresses().get(0).getAddress());
			assertEquals("work",phoneNumber.getLabel());
			assertEquals("home", address.getLabel());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}		
	}
	
	@Test
	public void testInvalidFields() {
		try {
			new PhoneNumber("work","123");
			
		}
		catch (Exception e) {
			
		}
		try {
			new PhoneNumber("work","123456789A");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new PhoneNumber(null,"1234567890");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new PhoneNumber("","1234567890");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			new Address(null,"");
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			
			new Contact("123","hackerearth");//,new PhoneNumber("work","1234567890"), new Address("work",""));
			fail();
		}
		catch (Exception e) {
			
		}
		try {
			
			new Contact("","hackerearth");//,new PhoneNumber("work","1234567890"), new Address("work",""));
			fail();
		}
		catch (Exception e) {
			
		}
	}

}

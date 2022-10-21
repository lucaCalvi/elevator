package com.kindalab.elevator.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeycardSystemTest {
	
	private static List<String> keycardSystemValidCodes;
	
	private static KeycardSystem keycardSystem;
	
	@BeforeAll
    static void setup() {
		System.out.println("Setup");
		keycardSystemValidCodes = Arrays.asList("1234", "4312", "5678", "8765", "1111");
		
		keycardSystem = new KeycardSystem(Long.valueOf(0), Arrays.asList(-1, 50), keycardSystemValidCodes);
    }
	
	@BeforeEach
    void resetValues() {
		System.out.println("Reset values");
		keycardSystem.setAccessToNextCallAllowed(false);
    }
	
	@Test
	void readValidKeycard() {
		System.out.println("readKeycard");
		keycardSystem.readKeycard(new Keycard(new Date(System.currentTimeMillis()).getTime(), "1234"));
		
		assertEquals(true, keycardSystem.isAccessToNextCallAllowed());
	}
	
	@Test
	void readInvalidKeycard() {
		System.out.println("readKeycard");
		keycardSystem.readKeycard(new Keycard(new Date(System.currentTimeMillis()).getTime(), "Invalid code"));
		
		assertEquals(false, keycardSystem.isAccessToNextCallAllowed());
	}

}

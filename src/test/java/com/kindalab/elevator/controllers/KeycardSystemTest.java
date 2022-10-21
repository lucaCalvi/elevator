package com.kindalab.elevator.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kindalab.elevator.models.Keycard;
import com.kindalab.elevator.models.KeycardSystem;

public class KeycardSystemTest {
	
	private static List<String> keycardSystemValidCodes;
	
	private static KeycardSystem keycardSystem;
	
	@BeforeAll
    static void setup() {
		System.out.println("Setup");
		keycardSystemValidCodes = Arrays.asList("1234", "4312", "5678", "8765", "1111");
		
		keycardSystem = new KeycardSystem(Arrays.asList(-1, 50), keycardSystemValidCodes);
    }
	
	@BeforeEach
    void resetValues() {
		System.out.println("Reset values");
		keycardSystem.setAccessToNextCallAllowed(false);
    }
	
	@Test
	void readValidKeycard() {
		System.out.println("readKeycard");
		keycardSystem.readKeycard(new Keycard("1234"));
		
		assertEquals(true, keycardSystem.isAccessToNextCallAllowed());
	}
	
	@Test
	void readInvalidKeycard() {
		System.out.println("readKeycard");
		keycardSystem.readKeycard(new Keycard("Invalid code"));
		
		assertEquals(false, keycardSystem.isAccessToNextCallAllowed());
	}

}

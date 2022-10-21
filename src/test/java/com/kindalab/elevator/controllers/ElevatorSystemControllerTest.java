package com.kindalab.elevator.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.models.ElevatorKCSystem;
import com.kindalab.elevator.models.Floor;
import com.kindalab.elevator.models.Keycard;
import com.kindalab.elevator.models.KeycardSystem;
import com.kindalab.elevator.view.ComboItem;
import com.kindalab.elevator.view.ElevatorSystemPanel;

public class ElevatorSystemControllerTest {
	
	private static ElevatorSystemController elevatorSystemController;
	
	private static ElevatorKCSystem publicElevator;
	
	private static Elevator freightElevator;
	
	private static ElevatorSystemPanel elevatorSystemPanel;
	
	private static Floor initFloor;
	
	private static ComboItem itemPE;
	
	private static ComboItem itemFE;
	
	@BeforeAll
    static void setup() {
		System.out.println("Setup");
		KeycardSystem keycardSystem = new KeycardSystem(Arrays.asList(-1, 50), Arrays.asList("1234", "4312", "5678", "8765", "1111"));
		
		List<Floor> floors = new ArrayList<>();
		int floorNumber = -1;
		for(int i = 0; i <= 51; i++) {
			floors.add(new Floor(Long.valueOf(i), floorNumber));
			floorNumber++;
		}
		
		initFloor = floors.stream()
				.filter(f -> f.getNumber().equals(0))
				.findFirst()
				.orElse(null);
		
		publicElevator = new ElevatorKCSystem(Long.valueOf(0), "Public elevator", initFloor, BigDecimal.valueOf(0), BigDecimal.valueOf(1000), keycardSystem);
		freightElevator = new Elevator(Long.valueOf(1), "Freight elevator", initFloor, BigDecimal.valueOf(0), BigDecimal.valueOf(3000));
		
		Building building = new Building(Arrays.asList(publicElevator, freightElevator), floors);
		
		elevatorSystemPanel = new ElevatorSystemPanel();
		
		elevatorSystemController = new ElevatorSystemController(elevatorSystemPanel, building);
		
		int size = elevatorSystemPanel.getDcbmElevator().getSize();
		for(int i = 0; i < size; i++) {
			ComboItem item = (ComboItem) elevatorSystemPanel.getDcbmElevator().getElementAt(i);
			if(item.getKey().equals(String.valueOf(publicElevator.getId()))) {
				itemPE = item;
			} else if(item.getKey().equals(String.valueOf(freightElevator.getId()))) {
				itemFE = item;
			}
		}
    }
	
	@BeforeEach
    void resetValues() {
		System.out.println("Reset values");
		publicElevator.setCurrentFloor(initFloor);
		publicElevator.setCurrentWeight(BigDecimal.valueOf(0));
		publicElevator.setIdle(true);
		publicElevator.setAlarmOn(false);
		publicElevator.setDestFloorsQueue(new LinkedList<>());
		
		freightElevator.setCurrentFloor(initFloor);
		freightElevator.setCurrentWeight(BigDecimal.valueOf(0));
		freightElevator.setIdle(true);
		freightElevator.setAlarmOn(false);
		freightElevator.setDestFloorsQueue(new LinkedList<>());
    }
	
	@Test
	void callElevatorOnce() {
		System.out.println("callElevatorOnce");
		elevatorSystemController.callElevator(freightElevator, 5);
		
		try {
			elevatorSystemController.getThreads().get(freightElevator.getId()).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(5, freightElevator.getCurrentFloor().getNumber());
	}
	
	@Test
	void callElevatorMoreThanOnce() {
		System.out.println("callElevatorMoreThanOnce");
		elevatorSystemController.callElevator(freightElevator, 5);
		
		try {
			elevatorSystemController.getThreads().get(freightElevator.getId()).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		elevatorSystemController.callElevator(freightElevator, 3);
		
		try {
			elevatorSystemController.getThreads().get(freightElevator.getId()).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(3, freightElevator.getCurrentFloor().getNumber());
	}
	
	@Test
	void callElevatorMoreThanOnceInQueue() {
		System.out.println("callElevatorMoreThanOnceInQueue");
		elevatorSystemController.callElevator(freightElevator, 5);
		elevatorSystemController.callElevator(freightElevator, 7);
		
		try {
			elevatorSystemController.getThreads().get(freightElevator.getId()).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(7, freightElevator.getCurrentFloor().getNumber());
	}
	
	@Test
	void generateKeycard() {
		System.out.println("generateKeycard");
		Keycard keycard = elevatorSystemController.generateKeycard("1234");
		
		assertEquals("1234", keycard.getCode());
	}
	
	@Test
	void readValidKeycard() {
		System.out.println("readValidKeycard");
		elevatorSystemPanel.getDcbmElevator().setSelectedItem(itemPE);
		
		Keycard keycard = elevatorSystemController.generateKeycard("1234");
		
		elevatorSystemController.readKeycard(keycard);
		
		assertEquals(true, publicElevator.getKeycardSystem().isAccessToNextCallAllowed());
	}
	
	@Test
	void readInvalidKeycard() {
		System.out.println("readInvalidKeycard");
		elevatorSystemPanel.getDcbmElevator().setSelectedItem(itemPE);
		
		Keycard keycard = elevatorSystemController.generateKeycard("Invalid code");
		
		elevatorSystemController.readKeycard(keycard);
		
		assertEquals(false, publicElevator.getKeycardSystem().isAccessToNextCallAllowed());
	}
	
	@Test
	void callElevatorToBlockedFloorWithValidKeycard() {
		System.out.println("callElevatorToBlockedFloorWithValidKeycard");
		elevatorSystemPanel.getDcbmElevator().setSelectedItem(itemPE);
		
		Keycard keycard = elevatorSystemController.generateKeycard("1234");
		
		elevatorSystemController.readKeycard(keycard);
		
		elevatorSystemController.callElevator(publicElevator, -1);
		
		try {
			elevatorSystemController.getThreads().get(publicElevator.getId()).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(-1, publicElevator.getCurrentFloor().getNumber());
	}
	
	@Test
	void callElevatorToBlockedFloorWithInvalidKeycard() {
		System.out.println("callElevatorToBlockedFloorWithInvalidKeycard");
		elevatorSystemPanel.getDcbmElevator().setSelectedItem(itemPE);
		
		Keycard keycard = elevatorSystemController.generateKeycard("Invalid code");
		
		elevatorSystemController.readKeycard(keycard);
		
		Integer previousFloor = publicElevator.getCurrentFloor().getNumber();
		
		elevatorSystemController.callElevator(publicElevator, -1);
		
		assertEquals(previousFloor, publicElevator.getCurrentFloor().getNumber());
	}
	
	@Test
	void turnOffAlarm() {
		System.out.println("turnOffAlarm");
		elevatorSystemPanel.getDcbmElevator().setSelectedItem(itemFE);
		
		freightElevator.shutOff();
		
		elevatorSystemPanel.getBtnTurnOffAlarm().doClick();
		
		assertEquals(false, freightElevator.isAlarmOn());
	}
	
	@Test
	void changeElevatorWeight() {
		System.out.println("changeElevatorWeight");
		elevatorSystemPanel.getDcbmElevator().setSelectedItem(itemFE);
		
		elevatorSystemPanel.getTxtWeight().setText("5000");
		
		elevatorSystemPanel.getBtnChangeWeight().doClick();
		
		assertEquals(BigDecimal.valueOf(Double.valueOf("5000")), freightElevator.getCurrentWeight());
	}
	
	@Test
	void callElevatorWithWeightGreaterThanMaximum() {
		System.out.println("callElevatorWithWeightGreaterThanMaximum");
		freightElevator.setCurrentWeight(BigDecimal.valueOf(6000));
		
		elevatorSystemController.callElevator(freightElevator, 5);
		
		try {
			elevatorSystemController.getThreads().get(freightElevator.getId()).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, freightElevator.isAlarmOn());
		assertEquals(true, freightElevator.isIdle());
	}
	
	@Test
	void callElevatorWithLessThanMaximumWeight() {
		System.out.println("callElevatorWithLessThanMaximumWeight");
		freightElevator.setCurrentWeight(BigDecimal.valueOf(1000));
		
		elevatorSystemController.callElevator(freightElevator, 3);
		
		try {
			elevatorSystemController.getThreads().get(freightElevator.getId()).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(3, freightElevator.getCurrentFloor().getNumber());
	}

}

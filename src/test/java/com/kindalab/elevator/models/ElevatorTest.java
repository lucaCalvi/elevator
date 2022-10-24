package com.kindalab.elevator.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevatorTest {
	
	private static ElevatorKCSystem publicElevator;
	
	private static List<Floor> floors;
	
	private static Floor initFloor;
	
	@BeforeAll
    static void setup() {
		System.out.println("Setup");
		floors = new ArrayList<>();
		int floorNumber = -1;
		for(int i = 0; i <= 51; i++) {
			floors.add(new Floor(Long.valueOf(i), floorNumber));
			floorNumber++;
		}
		
		initFloor = floors.stream()
				.filter(f -> f.getNumber().equals(0))
				.findFirst()
				.orElse(null);
		
		List<Floor> blockedFloors = floors.stream()
				.filter(f -> f.getNumber().equals(-1) || f.getNumber().equals(50))
				.collect(Collectors.toList());
		
		KeycardSystem keycardSystem = new KeycardSystem(Long.valueOf(0), blockedFloors, Arrays.asList("1234", "4312", "5678", "8765", "1111"));
		
		publicElevator = new ElevatorKCSystem(Long.valueOf(0), "Public elevator", initFloor, new BigDecimal(0), new BigDecimal(1000), keycardSystem);
    }
	
	@BeforeEach
    void resetValues() {
		System.out.println("Reset values");
		publicElevator.setCurrentFloor(initFloor);
		publicElevator.setCurrentWeight(new BigDecimal(0));
		publicElevator.setIdle(true);
		publicElevator.setAlarmOn(false);
		publicElevator.setDestFloorsQueue(new LinkedList<>());
    }
	
	@Test
	void goUp() {
		System.out.println("goUp");
		Integer previousFloor = publicElevator.getCurrentFloor().getNumber();
		
		try {
			Thread.sleep(Elevator.TIME_BETWEEN_FLOORS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		publicElevator.goUp(floors);
		
		assertEquals(previousFloor + 1, publicElevator.getCurrentFloor().getNumber());
	}

	@Test
	void goDown() {
		System.out.println("goDown");
		Integer previousFloor = publicElevator.getCurrentFloor().getNumber();
		
		try {
			Thread.sleep(Elevator.TIME_BETWEEN_FLOORS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		publicElevator.goDown(floors);
		
		assertEquals(previousFloor - 1, publicElevator.getCurrentFloor().getNumber());
	}
	
	@Test
	void waitInFloor() {
		System.out.println("waitInFloor");
		publicElevator.waitInFloor();
		
		try {
			Thread.sleep(Elevator.TIME_WAITING_FLOOR);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(false, publicElevator.isIdle());
	}

	@Test
	void shutOff() {
		System.out.println("shutOff");
		publicElevator.shutOff();
		
		assertEquals(true, publicElevator.isAlarmOn());
		assertEquals(true, publicElevator.isIdle());
	}

	@Test
	void addDestFloorToQueue() {
		System.out.println("addDestFloorToQueue");
		publicElevator.addDestFloorToQueue(5);
		
		assertEquals(5, publicElevator.getDestFloorsQueue().peek());
	}

	@Test
	void removeFirstDestFloorFromQueue() {
		System.out.println("removeFirstDestFloorFromQueue");
		publicElevator.addDestFloorToQueue(5);
		publicElevator.addDestFloorToQueue(10);
		publicElevator.removeFirstDestFloorFromQueue();
		
		assertEquals(10, publicElevator.getDestFloorsQueue().peek());
	}

	@Test
	void getFirstDestFloorFromQueue() {
		System.out.println("getFirstDestFloorFromQueue");
		publicElevator.addDestFloorToQueue(5);
		
		assertEquals(5, publicElevator.getFirstDestFloorFromQueue());
	}

}

package com.kindalab.elevator.models;

import java.math.BigDecimal;

public class ElevatorKCSystem extends Elevator {
	
	public KeycardSystem keycardSystem;
	
	public ElevatorKCSystem(Long id, String description, Floor currentFloor, BigDecimal currentWeight, BigDecimal maxWeight, KeycardSystem keycardSystem) {
		super(id, description, currentFloor, currentWeight, maxWeight);
		this.keycardSystem = keycardSystem;
	}

	public KeycardSystem getKeycardSystem() {
		return keycardSystem;
	}

	public void setKeycardSystem(KeycardSystem keycardSystem) {
		this.keycardSystem = keycardSystem;
	}

}

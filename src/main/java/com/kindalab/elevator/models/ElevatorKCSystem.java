package com.kindalab.elevator.models;

import java.math.BigDecimal;

public class ElevatorKCSystem extends Elevator {
	
	public KeycardSystem keycardSystem;
	
	public ElevatorKCSystem(Long id, Integer currentFloor, BigDecimal currentWeight, BigDecimal maxWeight, KeycardSystem keycardSystem) {
		super(id, currentFloor, currentWeight, maxWeight);
		this.keycardSystem = keycardSystem;
	}

	public KeycardSystem getKeycardSystem() {
		return keycardSystem;
	}

	public void setKeycardSystem(KeycardSystem keycardSystem) {
		this.keycardSystem = keycardSystem;
	}

}

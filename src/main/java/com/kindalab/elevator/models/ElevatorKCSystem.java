package com.kindalab.elevator.models;

public class ElevatorKCSystem extends Elevator {
	
	public KeycardSystem keycardSystem;
	
	public ElevatorKCSystem(Long id, Integer currentFloor, Long maxWeight) {
		super(id, currentFloor, maxWeight);
		this.keycardSystem = new KeycardSystem();
	}

	public KeycardSystem getKeycardSystem() {
		return keycardSystem;
	}

	public void setKeycardSystem(KeycardSystem keycardSystem) {
		this.keycardSystem = keycardSystem;
	}

}

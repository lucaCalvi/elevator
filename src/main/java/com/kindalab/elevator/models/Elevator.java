package com.kindalab.elevator.models;

public class Elevator implements IElevator {
	
	private Long id;
	
	private Integer currentFloor;
	
	private Long maxWeight;
	
	private boolean idle;
	
	private boolean alarmOn;
	
	public Elevator(Long id, Integer currentFloor, Long maxWeight) {
		this.id = id;
		this.currentFloor = currentFloor;
		this.maxWeight = maxWeight;
		this.idle = true;
		this.alarmOn = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
	}

	public Long getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Long maxWeight) {
		this.maxWeight = maxWeight;
	}

	public boolean isIdle() {
		return idle;
	}

	public void setIdle(boolean idle) {
		this.idle = idle;
	}

	public boolean isAlarmOn() {
		return alarmOn;
	}

	public void setAlarmOn(boolean alarmOn) {
		this.alarmOn = alarmOn;
	}

	@Override
	public void goUp() {
		this.currentFloor++;
	}

	@Override
	public void goDown() {
		this.currentFloor--;
	}

	@Override
	public void shutOff() {
		this.idle = true;
		this.alarmOn = true;
	}

	@Override
	public void turnOn() {
		// this.idle = false;
		this.alarmOn = false;
	}

}

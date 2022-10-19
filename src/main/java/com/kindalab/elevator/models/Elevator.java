package com.kindalab.elevator.models;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

public class Elevator implements IElevator {
	
	public static final Integer TIME_BETWEEN_FLOORS = 2000;
	public static final Integer TIME_BETWEEN_REQUESTS = 5000;
	
	private Long id;
	
	private Integer currentFloor;
	
	private BigDecimal currentWeight;
	
	private BigDecimal maxWeight;
	
	private boolean idle;
	
	private boolean alarmOn;
	
	private Queue<Integer> destFloorsQueue;
	
	public Elevator(Long id, Integer currentFloor, BigDecimal currentWeight, BigDecimal maxWeight) {
		this.id = id;
		this.currentFloor = currentFloor;
		this.currentWeight = currentWeight;
		this.maxWeight = maxWeight;
		this.idle = true;
		this.alarmOn = false;
		this.destFloorsQueue = new LinkedList<>();
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

	public BigDecimal getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(BigDecimal currentWeight) {
		this.currentWeight = currentWeight;
	}

	public BigDecimal getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(BigDecimal maxWeight) {
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
		try {
			Thread.sleep(TIME_BETWEEN_FLOORS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.currentFloor++;
	}

	@Override
	public void goDown() {
		try {
			Thread.sleep(TIME_BETWEEN_FLOORS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.currentFloor--;
	}
	
	@Override
	public void waitInFloor() {
		try {
			Thread.sleep(TIME_BETWEEN_REQUESTS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutOff() {
		this.idle = true;
		this.alarmOn = true;
	}

	@Override
	public void addDestFloorToQueue(Integer destFloor) {
		this.destFloorsQueue.offer(destFloor);
	}

	@Override
	public void removeFirstDestFloorFromQueue() {
		this.destFloorsQueue.poll();
	}

	@Override
	public Integer getFirstDestFloorFromQueue() {
		return this.destFloorsQueue.peek();
	}

}

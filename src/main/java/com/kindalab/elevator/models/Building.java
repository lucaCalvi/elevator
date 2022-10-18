package com.kindalab.elevator.models;

import java.util.List;

public class Building {
	
	private Long id;
	
	private List<Elevator> elevators;
	
	private Integer minFloor;
	
	private Integer maxFloor;
	
	public Building(List<Elevator> elevators, Integer minFloor, Integer maxFloor) {
		this.elevators = elevators;
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Elevator> getElevators() {
		return elevators;
	}

	public void setElevators(List<Elevator> elevators) {
		this.elevators = elevators;
	}

	public Integer getMinFloor() {
		return minFloor;
	}

	public void setMinFloor(Integer minFloor) {
		this.minFloor = minFloor;
	}

	public Integer getMaxFloor() {
		return maxFloor;
	}

	public void setMaxFloor(Integer maxFloor) {
		this.maxFloor = maxFloor;
	}
	
	

}

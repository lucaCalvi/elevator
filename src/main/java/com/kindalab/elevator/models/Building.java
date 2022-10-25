package com.kindalab.elevator.models;

import java.util.List;

public class Building {
	
	private Long id;
	
	private List<Elevator> elevators;
	
	private List<Floor> floors;
	
	public Building(Long id, List<Elevator> elevators, List<Floor> floors) {
		this.id = id;
		this.elevators = elevators;
		this.floors = floors;
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

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}
	
	

}

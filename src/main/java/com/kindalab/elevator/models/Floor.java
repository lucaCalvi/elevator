package com.kindalab.elevator.models;

public class Floor {
	
	private Long id;
	
	private Integer number;
	
	public Floor(Long id, Integer number) {
		this.id = id;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}

package com.kindalab.elevator.models;

public class Keycard {
	
	private Long id;
	
	private String code;
	
	public Keycard(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

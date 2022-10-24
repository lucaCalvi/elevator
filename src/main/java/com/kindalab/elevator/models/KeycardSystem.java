package com.kindalab.elevator.models;

import java.util.List;

public class KeycardSystem implements IKeycardSystem {
	
	private Long id;
	
	private List<Floor> blockedFloors;
	
	private List<String> keycardSystemValidCodes;
	
	private boolean accessToNextCallAllowed;
	
	public KeycardSystem(Long id, List<Floor> blockedFloors, List<String> keycardSystemValidCodes) {
		this.id = id;
		this.blockedFloors = blockedFloors;
		this.keycardSystemValidCodes = keycardSystemValidCodes;
		this.accessToNextCallAllowed = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Floor> getBlockedFloors() {
		return blockedFloors;
	}

	public void setBlockedFloors(List<Floor> blockedFloors) {
		this.blockedFloors = blockedFloors;
	}

	public List<String> getKeycardSystemValidCodes() {
		return keycardSystemValidCodes;
	}

	public void setKeycardSystemValidCodes(List<String> keycardSystemValidCodes) {
		this.keycardSystemValidCodes = keycardSystemValidCodes;
	}

	public boolean isAccessToNextCallAllowed() {
		return accessToNextCallAllowed;
	}

	public void setAccessToNextCallAllowed(boolean accessToNextCallAllowed) {
		this.accessToNextCallAllowed = accessToNextCallAllowed;
	}

	@Override
	public void readKeycard(Keycard keycard) {
		if(keycardSystemValidCodes.contains(keycard.getCode())) {
			System.out.println("Next call allowed for blocked floors");
			this.accessToNextCallAllowed = true;
		} else {
			System.out.println("Next call NOT allowed for blocked floors");
			this.accessToNextCallAllowed = false;
		}
	}

}

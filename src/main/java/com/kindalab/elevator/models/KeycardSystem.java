package com.kindalab.elevator.models;

import java.util.List;

public class KeycardSystem implements IKeycardSystem {
	
	public KeycardSystem(List<Integer> blockedFloors, List<String> keycardSystemValidCodes) {
		this.blockedFloors = blockedFloors;
		this.keycardSystemValidCodes = keycardSystemValidCodes;
	}
	
	private List<Integer> blockedFloors;
	
	private List<String> keycardSystemValidCodes;
	
	private boolean accessToNextCallAllowed;

	public List<Integer> getBlockedFloors() {
		return blockedFloors;
	}

	public void setBlockedFloors(List<Integer> blockedFloors) {
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

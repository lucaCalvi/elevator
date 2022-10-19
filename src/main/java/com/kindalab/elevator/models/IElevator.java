package com.kindalab.elevator.models;

public interface IElevator {
	
	public void goUp();
	
	public void goDown();
	
	public void waitInFloor();
	
	public void shutOff();
	
	public void addDestFloorToQueue(Integer destFloor);
	
	public void removeFirstDestFloorFromQueue();
	
	public Integer getFirstDestFloorFromQueue();

}

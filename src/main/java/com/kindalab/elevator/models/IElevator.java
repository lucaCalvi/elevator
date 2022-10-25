package com.kindalab.elevator.models;

import java.util.List;

public interface IElevator {
	
	public void goUp(List<Floor> floors);
	
	public void goDown(List<Floor> floors);
	
	public void waitInFloor();
	
	public void shutOff();
	
	public void addDestFloorToQueue(Integer destFloor);
	
	public void removeFirstDestFloorFromQueue();
	
	public Integer getFirstDestFloorFromQueue();

}

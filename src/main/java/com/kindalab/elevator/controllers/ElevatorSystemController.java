package com.kindalab.elevator.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.models.ElevatorKCSystem;
import com.kindalab.elevator.models.Keycard;
import com.kindalab.elevator.view.ElevatorSystemPanel;

public class ElevatorSystemController {
	
	private ElevatorSystemPanel elevatorSystemPanel;
	
	private Building building;
	
	public ElevatorSystemController(ElevatorSystemPanel elevatorSystemPanel, Building building) {
		this.elevatorSystemPanel = elevatorSystemPanel;
		this.building = building;
		
		this.elevatorSystemPanel.addElevatorActionListener(new ElevatorActionListener());
	}
	
	private void callElevator(Elevator elevator, Integer destFloor) {
		System.out.println(this.elevatorSystemPanel.getCmbSelectElevator().getSelectedItem() + " -> Calling elevator");
		
		if(elevator != null) {
			if(!(elevator instanceof ElevatorKCSystem) || !(((ElevatorKCSystem) elevator).getKeycardSystem().getBlockedFloors().contains(destFloor) &&
					!((ElevatorKCSystem) elevator).getKeycardSystem().isAccessToNextCallAllowed())) {
				elevator.addDestFloorToQueue(destFloor);
				
				if(elevator.isIdle()) {
					new Thread(() -> startElevator(elevator)).start();
				}
				
				((ElevatorKCSystem) elevator).getKeycardSystem().setAccessToNextCallAllowed(false);
			}
		}
	}
	
	private void startElevator(Elevator elevator) {
		System.out.println((elevator.getId() == Long.valueOf(0) ? "Public elevator" : "Freight elevator") + " -> New Thread");
		elevator.setIdle(false);
		
		if(elevator.getMaxWeight().compareTo(elevator.getCurrentWeight()) <= 0) {
			System.out.println((elevator.getId() == Long.valueOf(0) ? "Public elevator" : "Freight elevator") + " -> Shutt Off");
			elevator.shutOff();
		} else {
			Integer destFloor = elevator.getFirstDestFloorFromQueue();
			elevator.removeFirstDestFloorFromQueue();
			takeElevatorToNextDestFloor(elevator, destFloor);
		}
	}
	
	private void takeElevatorToNextDestFloor(Elevator elevator, Integer destFloor) {
		if(destFloor > elevator.getCurrentFloor()) {
			for(int i = elevator.getCurrentFloor(); i < destFloor; i++) {
				elevator.goUp();
				System.out.println((elevator.getId() == Long.valueOf(0) ? "Public elevator" : "Freight elevator") + " -> currentFloor: " + elevator.getCurrentFloor());
			}
		} else if(destFloor < elevator.getCurrentFloor()) {
			for(int i = elevator.getCurrentFloor(); i > destFloor; i--) {
				elevator.goDown();
				System.out.println((elevator.getId() == Long.valueOf(0) ? "Public elevator" : "Freight elevator") + " -> currentFloor: " + elevator.getCurrentFloor());
			}
		}
		
		Integer nextDestFloor = elevator.getFirstDestFloorFromQueue();
		if(nextDestFloor != null) {
			elevator.setIdle(true);
			
			System.out.println((elevator.getId() == Long.valueOf(0) ? "Public elevator" : "Freight elevator") + " -> Enter/leave the elevator please!!!");
			elevator.waitInFloor();
			
			elevator.setIdle(false);
			
			if(elevator.getMaxWeight().compareTo(elevator.getCurrentWeight()) <= 0) {
				System.out.println((elevator.getId() == Long.valueOf(0) ? "Public elevator" : "Freight elevator") + " -> Shutt Off");
				elevator.shutOff();
			} else {
				elevator.removeFirstDestFloorFromQueue();
				takeElevatorToNextDestFloor(elevator, nextDestFloor);
			}
		} else {
			elevator.setIdle(true);
			System.out.println((elevator.getId() == Long.valueOf(0) ? "Public elevator" : "Freight elevator") + " -> Elevator in destFloor!!!");
		}
	}
	
	private Keycard generateKeycard(String keycardGenerationCode) {
		return new Keycard(keycardGenerationCode);
	}
	
	private void readKeycard(Keycard keycard) {
		Elevator elevator = getSelectedElevator();
		if(elevator instanceof ElevatorKCSystem) {
			((ElevatorKCSystem) elevator).getKeycardSystem().readKeycard(keycard);
		}
	}

	public class ElevatorActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(elevatorSystemPanel.getBtnCallElevator() == e.getSource()) {
				callElevator(getSelectedElevator(), Integer.parseInt((String) elevatorSystemPanel.getCmbSelectFloor().getSelectedItem()));
			} else if(elevatorSystemPanel.getBtnEnterKeycard() == e.getSource()) {
				readKeycard(generateKeycard(elevatorSystemPanel.getTxtKeycard().getText()));
			} else if(elevatorSystemPanel.getCmbSelectElevator() == e.getSource()) {
				Elevator elevator = getSelectedElevator();
				if(elevator != null)
					elevatorSystemPanel.getLblCurrentFloorValue().setText(String.valueOf(elevator.getCurrentFloor()));
			} else if(elevatorSystemPanel.getBtnTurnOffAlarm() == e.getSource()) {
				Elevator elevator = getSelectedElevator();
				if(elevator != null)
					elevator.setAlarmOn(false);
			} else if(elevatorSystemPanel.getBtnChangeWeight() == e.getSource()) {
				Elevator elevator = getSelectedElevator();
				if(elevator != null)
					elevator.setCurrentWeight(BigDecimal.valueOf(Double.valueOf(elevatorSystemPanel.getTxtWeight().getText())));
			}
		}
		
	}
	
	private Elevator getSelectedElevator() {
		Long id = Long.valueOf(elevatorSystemPanel.getCmbSelectElevator().getSelectedIndex());
		Elevator elevator = building.getElevators().stream()
			.filter(elev -> elev.getId().equals(id))
			.findFirst()
			.orElse(null);
		
		return elevator;
	}

}

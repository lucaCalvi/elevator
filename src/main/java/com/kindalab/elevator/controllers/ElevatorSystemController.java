package com.kindalab.elevator.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.view.ElevatorSystemPanel;

public class ElevatorSystemController {
	
	public static final Integer TIME_BETWEEN_FLOORS = 1000;
	
	private ElevatorSystemPanel elevatorSystemPanel;
	
	private Building building;
	
	public ElevatorSystemController(ElevatorSystemPanel elevatorSystemPanel, Building building) {
		this.elevatorSystemPanel = elevatorSystemPanel;
		this.building = building;
		
		this.elevatorSystemPanel.addElevatorActionListener(new ElevatorActionListener());
	}
	
	public void callElevator(String elevatorId, Integer destFloor) {
		System.out.println("Calling elevator!!!");
		System.out.println("elevatorId: " + elevatorId);
		System.out.println("destFloor: " + destFloor);
	}
	
	public void readKeycard() {
		
	}

	public class ElevatorActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(elevatorSystemPanel.getBtnCallElevator() == e.getSource()) {
				callElevator((String) elevatorSystemPanel.getCmbSelectElevator().getSelectedItem(), 
						Integer.parseInt((String) elevatorSystemPanel.getCmbSelectFloor().getSelectedItem()));
			} else if(elevatorSystemPanel.getBtnEnterKeycard() == e.getSource()) {
				
			} else if(elevatorSystemPanel.getCmbSelectElevator() == e.getSource()) {
				Long id = elevatorSystemPanel.getCmbSelectElevator().getSelectedItem() == "Public elevator" ? Long.valueOf(0) : Long.valueOf(1);
				Elevator elevator = building.getElevators().stream()
					.filter(elev -> elev.getId().equals(id))
					.findFirst()
					.orElse(null);
				
				elevatorSystemPanel.getLblCurrentFloorValue().setText(String.valueOf(elevator.getCurrentFloor()));
			} else if(elevatorSystemPanel.getCmbSelectFloor() == e.getSource()) {
				
			}
		}
		
	}

}

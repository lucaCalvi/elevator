package com.kindalab.elevator;

import java.util.Arrays;

import com.kindalab.elevator.controllers.ElevatorSystemController;
import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.models.ElevatorKCSystem;
import com.kindalab.elevator.models.KeycardSystem;
import com.kindalab.elevator.view.ElevatorSystemPanel;

public class ElevatorSystem {
	
	public void init() {
		ElevatorKCSystem publicElevator = new ElevatorKCSystem(Long.valueOf(0), 2, Long.valueOf(1000));
		Elevator freightElevator = new Elevator(Long.valueOf(1), 5, Long.valueOf(3000));
		publicElevator.setKeycardSystem(new KeycardSystem());
		Building building = new Building(Arrays.asList(freightElevator, publicElevator), -1, 50);
		
		ElevatorSystemPanel elevatorSystemPanel = new ElevatorSystemPanel();
		
		ElevatorSystemController elevatorController = new ElevatorSystemController(elevatorSystemPanel, building);
		
		elevatorSystemPanel.setVisible(true);
	}

}

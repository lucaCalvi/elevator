package com.kindalab.elevator;

import java.math.BigDecimal;
import java.util.Arrays;

import com.kindalab.elevator.controllers.ElevatorSystemController;
import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.models.ElevatorKCSystem;
import com.kindalab.elevator.models.KeycardSystem;
import com.kindalab.elevator.view.ElevatorSystemPanel;

public class ElevatorSystem {
	
	public void init() {
		KeycardSystem keycardSystem = new KeycardSystem(Arrays.asList(-1, 50), Arrays.asList("1234", "4312", "5678", "8765", "1111"));
		ElevatorKCSystem publicElevator = new ElevatorKCSystem(Long.valueOf(0), 2, BigDecimal.valueOf(0), BigDecimal.valueOf(1000), keycardSystem);
		Elevator freightElevator = new Elevator(Long.valueOf(1), 5, BigDecimal.valueOf(0), BigDecimal.valueOf(3000));
		Building building = new Building(Arrays.asList(freightElevator, publicElevator), -1, 50);
		
		ElevatorSystemPanel elevatorSystemPanel = new ElevatorSystemPanel();
		
		new ElevatorSystemController(elevatorSystemPanel, building);
		
		elevatorSystemPanel.setVisible(true);
	}

}

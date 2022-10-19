package com.kindalab.elevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kindalab.elevator.controllers.ElevatorSystemController;
import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.models.ElevatorKCSystem;
import com.kindalab.elevator.models.Floor;
import com.kindalab.elevator.models.KeycardSystem;
import com.kindalab.elevator.view.ElevatorSystemPanel;

public class ElevatorSystem {
	
	public void init() {
		KeycardSystem keycardSystem = new KeycardSystem(Arrays.asList(-1, 50), Arrays.asList("1234", "4312", "5678", "8765", "1111"));
		
		List<Floor> floors = new ArrayList<>();
		int floorNumber = -1;
		for(int i = 0; i <= 51; i++) {
			floors.add(new Floor(Long.valueOf(i), floorNumber));
			floorNumber++;
		}
		
		Floor initFloor = floors.stream()
				.filter(f -> f.getNumber().equals(0))
				.findFirst()
				.orElse(null);
		
		ElevatorKCSystem publicElevator = new ElevatorKCSystem(Long.valueOf(0), "Public elevator", initFloor, BigDecimal.valueOf(0), BigDecimal.valueOf(1000), keycardSystem);
		Elevator freightElevator = new Elevator(Long.valueOf(1), "Freight elevator", initFloor, BigDecimal.valueOf(0), BigDecimal.valueOf(3000));
		
		Building building = new Building(Arrays.asList(publicElevator, freightElevator), floors);
		
		ElevatorSystemPanel elevatorSystemPanel = new ElevatorSystemPanel();
		
		new ElevatorSystemController(elevatorSystemPanel, building);
		
		elevatorSystemPanel.setVisible(true);
	}

}

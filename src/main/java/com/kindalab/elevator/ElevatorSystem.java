package com.kindalab.elevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.kindalab.elevator.controllers.ElevatorSystemController;
import com.kindalab.elevator.gui.ElevatorSystemPanel;
import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.models.ElevatorKCSystem;
import com.kindalab.elevator.models.Floor;
import com.kindalab.elevator.models.KeycardSystem;

public class ElevatorSystem {
	
	public void init() {
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
		
		List<Floor> blockedFloors = floors.stream()
				.filter(f -> f.getNumber().equals(-1) || f.getNumber().equals(50))
				.collect(Collectors.toList());
		
		KeycardSystem keycardSystem = new KeycardSystem(Long.valueOf(0), blockedFloors, Arrays.asList("1234", "4312", "5678", "8765", "1111"));
		
		ElevatorKCSystem publicElevator = new ElevatorKCSystem(Long.valueOf(0), "Public elevator", initFloor, new BigDecimal(0), new BigDecimal(1000), keycardSystem);
		Elevator freightElevator = new Elevator(Long.valueOf(1), "Freight elevator", initFloor, new BigDecimal(0), new BigDecimal(3000));
		
		Building building = new Building(Long.valueOf(0), Arrays.asList(publicElevator, freightElevator), floors);
		
		ElevatorSystemPanel elevatorSystemPanel = new ElevatorSystemPanel();
		
		new ElevatorSystemController(elevatorSystemPanel, building);
		
		elevatorSystemPanel.setVisible(true);
	}

}

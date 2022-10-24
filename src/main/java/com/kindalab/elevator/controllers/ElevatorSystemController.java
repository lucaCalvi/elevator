package com.kindalab.elevator.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.kindalab.elevator.gui.ComboItem;
import com.kindalab.elevator.gui.ElevatorSystemPanel;
import com.kindalab.elevator.models.Building;
import com.kindalab.elevator.models.Elevator;
import com.kindalab.elevator.models.ElevatorKCSystem;
import com.kindalab.elevator.models.Floor;
import com.kindalab.elevator.models.Keycard;

public class ElevatorSystemController {
	
	private ElevatorSystemPanel elevatorSystemPanel;
	
	private Building building;
	
	private Map<Long, Thread> threads;
	
	public ElevatorSystemController(ElevatorSystemPanel elevatorSystemPanel, Building building) {
		this.elevatorSystemPanel = elevatorSystemPanel;
		this.building = building;
		
		this.threads = new HashMap<Long, Thread>();
		
		initValues();
	}
	
	public Map<Long, Thread> getThreads() {
		return threads;
	}

	public void callElevator(Elevator elevator, Integer destFloor) {
		System.out.println(elevator.getDescription() + " -> Calling elevator");
		
		if(elevator != null) {
			if(!(elevator instanceof ElevatorKCSystem) || 
					!(((ElevatorKCSystem) elevator).getKeycardSystem().getBlockedFloors().stream()
							.map(bf -> bf.getNumber())
							.collect(Collectors.toList())
							.contains(destFloor) &&
					!((ElevatorKCSystem) elevator).getKeycardSystem().isAccessToNextCallAllowed())) {
				elevator.addDestFloorToQueue(destFloor);
				
				Thread elevatorThread = this.threads.get(elevator.getId());
				if(elevatorThread == null || !elevatorThread.isAlive()) {
					Thread thread = new Thread(() -> startElevator(elevator));
					thread.start();
					
					this.threads.put(elevator.getId(), thread);
				}
				
				if(elevator instanceof ElevatorKCSystem)
					((ElevatorKCSystem) elevator).getKeycardSystem().setAccessToNextCallAllowed(false);
			}
		}
	}
	
	private void startElevator(Elevator elevator) {
		System.out.println(elevator.getDescription() + " -> New Thread");
		elevator.setIdle(false);
		
		if(elevator.getMaxWeight().compareTo(elevator.getCurrentWeight()) < 0) {
			System.out.println(elevator.getDescription() + " -> Shutt Off");
			elevator.shutOff();
		} else {
			Integer destFloor = elevator.getFirstDestFloorFromQueue();
			elevator.removeFirstDestFloorFromQueue();
			takeElevatorToNextDestFloor(elevator, destFloor);
		}
	}
	
	private void takeElevatorToNextDestFloor(Elevator elevator, Integer destFloor) {
		if(destFloor > elevator.getCurrentFloor().getNumber()) {
			for(int i = elevator.getCurrentFloor().getNumber(); i < destFloor; i++) {
				elevator.goUp(building.getFloors());
				System.out.println(elevator.getDescription() + " -> currentFloor: " + elevator.getCurrentFloor().getNumber());
			}
		} else if(destFloor < elevator.getCurrentFloor().getNumber()) {
			for(int i = elevator.getCurrentFloor().getNumber(); i > destFloor; i--) {
				elevator.goDown(building.getFloors());
				System.out.println(elevator.getDescription() + " -> currentFloor: " + elevator.getCurrentFloor().getNumber());
			}
		}
		
		Integer nextDestFloor = elevator.getFirstDestFloorFromQueue();
		while(nextDestFloor != null && nextDestFloor == elevator.getCurrentFloor().getNumber()) {
			elevator.removeFirstDestFloorFromQueue();
			nextDestFloor = elevator.getFirstDestFloorFromQueue();
		}
		
		if(nextDestFloor != null) {
			System.out.println(elevator.getDescription() + " -> Enter/leave the elevator please!!!");
			elevator.waitInFloor();
			
			if(elevator.getMaxWeight().compareTo(elevator.getCurrentWeight()) < 0) {
				System.out.println(elevator.getDescription() + " -> Shutt Off");
				elevator.shutOff();
			} else {
				elevator.removeFirstDestFloorFromQueue();
				takeElevatorToNextDestFloor(elevator, nextDestFloor);
			}
		} else {
			elevator.setIdle(true);
			System.out.println(elevator.getDescription() + " -> Elevator in destFloor!!!");
		}
	}
	
	public Keycard generateKeycard(String keycardGenerationCode) {
		return new Keycard(new Date(System.currentTimeMillis()).getTime(), keycardGenerationCode);
	}
	
	public void readKeycard(Keycard keycard) {
		Elevator elevator = getSelectedElevator();
		if(elevator instanceof ElevatorKCSystem) {
			((ElevatorKCSystem) elevator).getKeycardSystem().readKeycard(keycard);
		}
	}
	
	private Elevator getSelectedElevator() {
		ComboItem item = (ComboItem) elevatorSystemPanel.getCmbSelectElevator().getSelectedItem();
		Elevator elevator = building.getElevators().stream()
			.filter(elev -> elev.getId().equals(Long.valueOf(item.getKey())))
			.findFirst()
			.orElse(null);
		
		return elevator;
	}
	
	private void updateComboBoxValue(Elevator elevator) {
		int size = this.elevatorSystemPanel.getDcbmFloor().getSize();
		for(int i = 0; i < size; i++) {
			ComboItem item = (ComboItem) this.elevatorSystemPanel.getDcbmFloor().getElementAt(i);
			if(item.getKey().equals(String.valueOf(elevator.getCurrentFloor().getId()))) {
				this.elevatorSystemPanel.getDcbmFloor().setSelectedItem(item);
				break;
			}
		}
	}
	
	private void initValues() {
		this.elevatorSystemPanel.addElevatorActionListener(new ElevatorActionListener());
		for(Elevator e: building.getElevators()) {
			this.elevatorSystemPanel.getDcbmElevator().addElement(new ComboItem(String.valueOf(e.getId()), e.getDescription()));
		}
		this.elevatorSystemPanel.getCmbSelectElevator().setModel(this.elevatorSystemPanel.getDcbmElevator());
		for(Floor f: building.getFloors()) {
			this.elevatorSystemPanel.getDcbmFloor().addElement(new ComboItem(String.valueOf(f.getId()), String.valueOf(f.getNumber())));
		}
		this.elevatorSystemPanel.getCmbSelectFloor().setModel(this.elevatorSystemPanel.getDcbmFloor());
		
		Elevator selectedElevator = getSelectedElevator();
		
		if(selectedElevator != null) {
			updateComboBoxValue(selectedElevator);
			this.elevatorSystemPanel.getTxtWeight().setText(String.valueOf(selectedElevator.getCurrentWeight()));
		}
	}

	public class ElevatorActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(elevatorSystemPanel.getBtnCallElevator() == e.getSource()) {
				callElevator(getSelectedElevator(), Integer.parseInt(((ComboItem) elevatorSystemPanel.getCmbSelectFloor().getSelectedItem()).getValue()));
			} else if(elevatorSystemPanel.getBtnEnterKeycard() == e.getSource()) {
				readKeycard(generateKeycard(elevatorSystemPanel.getTxtKeycard().getText()));
			} else if(elevatorSystemPanel.getCmbSelectElevator() == e.getSource()) {
				Elevator elevator = getSelectedElevator();
				if(elevator != null) {
					updateComboBoxValue(elevator);
					elevatorSystemPanel.getTxtWeight().setText(String.valueOf(elevator.getCurrentWeight()));
				}
			} else if(elevatorSystemPanel.getBtnTurnOffAlarm() == e.getSource()) {
				Elevator elevator = getSelectedElevator();
				if(elevator != null) {
					elevator.setAlarmOn(false);
					System.out.println(elevator.getDescription() + " -> Alarm turned off");
				}
			} else if(elevatorSystemPanel.getBtnChangeWeight() == e.getSource()) {
				Elevator elevator = getSelectedElevator();
				if(elevator != null && elevator.isIdle()) {
					try {
						elevator.setCurrentWeight(new BigDecimal(elevatorSystemPanel.getTxtWeight().getText()));
						System.out.println(elevator.getDescription() + " -> Elevator weight changed to: " + elevator.getCurrentWeight());
					} catch(NumberFormatException ex) {
						System.out.println("Invalid number format");
						ex.printStackTrace();
					}
				}
			}
		}
		
	}

}

package com.kindalab.elevator.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.kindalab.elevator.controllers.ElevatorSystemController.ElevatorActionListener;

public class ElevatorSystemPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel principalPanel;
	private JTextField txtKeycard;
	private JButton btnEnterKeycard, btnCallElevator, btnTurnOffAlarm, btnChangeWeight;
	private JLabel lblCurrentFloorValue, lblAlarmStatusValue, lblElevatorStatusValue;
	private JComboBox<String> cmbSelectElevator, cmbSelectFloor;
	private JTextField txtWeight;

	public JTextField getTxtWeight() {
		return txtWeight;
	}

	public JLabel getLblCurrentFloorValue() {
		return lblCurrentFloorValue;
	}

	public void setLblCurrentFloorValue(JLabel lblCurrentFloorValue) {
		this.lblCurrentFloorValue = lblCurrentFloorValue;
	}

	public JLabel getLblAlarmStatusValue() {
		return lblAlarmStatusValue;
	}

	public void setLblAlarmStatusValue(JLabel lblAlarmStatusValue) {
		this.lblAlarmStatusValue = lblAlarmStatusValue;
	}

	public JLabel getLblElevatorStatusValue() {
		return lblElevatorStatusValue;
	}

	public void setLblElevatorStatusValue(JLabel lblElevatorStatusValue) {
		this.lblElevatorStatusValue = lblElevatorStatusValue;
	}

	public JTextField getTxtKeycard() {
		return txtKeycard;
	}

	public JComboBox<String> getCmbSelectElevator() {
		return cmbSelectElevator;
	}

	public JComboBox<String> getCmbSelectFloor() {
		return cmbSelectFloor;
	}

	public JButton getBtnEnterKeycard() {
		return btnEnterKeycard;
	}

	public JButton getBtnCallElevator() {
		return btnCallElevator;
	}
	
	public JButton getBtnTurnOffAlarm() {
		return btnTurnOffAlarm;
	}

	public JButton getBtnChangeWeight() {
		return btnChangeWeight;
	}

	public void addElevatorActionListener(ElevatorActionListener elevatorActionListener) {
		btnCallElevator.addActionListener(elevatorActionListener);
		btnEnterKeycard.addActionListener(elevatorActionListener);
		cmbSelectElevator.addActionListener(elevatorActionListener);
		cmbSelectFloor.addActionListener(elevatorActionListener);
	}

	/**
	 * Create the frame.
	 */
	public ElevatorSystemPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 900, 600);
		principalPanel = new JPanel();
		principalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(principalPanel);
		principalPanel.setLayout(null);
		
		JLabel panelTitle = new JLabel("Elevator System");
		panelTitle.setFont(new Font("Arial", Font.BOLD, 22));
		panelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.setBounds(181, 11, 491, 39);
		principalPanel.add(panelTitle);
		
		JLabel lblCurrentFloor = new JLabel("Current Floor: ");
		lblCurrentFloor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCurrentFloor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentFloor.setBounds(258, 111, 172, 39);
		principalPanel.add(lblCurrentFloor);
		
		JLabel lblSelectElevator = new JLabel("Select elevator");
		lblSelectElevator.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectElevator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectElevator.setBounds(258, 61, 172, 39);
		principalPanel.add(lblSelectElevator);
		
		cmbSelectElevator = new JComboBox();
		cmbSelectElevator.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectElevator.setLabelFor(cmbSelectElevator);
		cmbSelectElevator.setBounds(440, 69, 136, 22);
		cmbSelectElevator.addItem("Public elevator");
		cmbSelectElevator.addItem("Freight elevator");
		principalPanel.add(cmbSelectElevator);
		
		JLabel lblSelectFloor = new JLabel("Select floor");
		lblSelectFloor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectFloor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectFloor.setBounds(258, 313, 172, 39);
		principalPanel.add(lblSelectFloor);
		
		cmbSelectFloor = new JComboBox();
		lblSelectFloor.setLabelFor(cmbSelectFloor);
		cmbSelectFloor.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbSelectFloor.setBounds(440, 321, 136, 22);
		for(int i = -1; i <= 50; i++) {
			cmbSelectFloor.addItem(String.valueOf(i));
		}
		cmbSelectFloor.setSelectedItem("0");
		principalPanel.add(cmbSelectFloor);
		
		btnCallElevator = new JButton("Call elevator");
		btnCallElevator.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCallElevator.setBounds(355, 422, 153, 23);
		principalPanel.add(btnCallElevator);
		
		txtKeycard = new JTextField();
		txtKeycard.setFont(new Font("Arial", Font.PLAIN, 12));
		txtKeycard.setBounds(355, 363, 158, 31);
		principalPanel.add(txtKeycard);
		txtKeycard.setColumns(10);
		
		JLabel lblEnterKeycard = new JLabel("Enter keycard");
		lblEnterKeycard.setLabelFor(txtKeycard);
		lblEnterKeycard.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEnterKeycard.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterKeycard.setBounds(192, 359, 153, 39);
		principalPanel.add(lblEnterKeycard);
		
		JLabel lblAlarmStatus = new JLabel("Alarm status: ");
		lblAlarmStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAlarmStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmStatus.setBounds(258, 161, 172, 39);
		principalPanel.add(lblAlarmStatus);
		
		JLabel lblElevatorStatus = new JLabel("Elevator status: ");
		lblElevatorStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		lblElevatorStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblElevatorStatus.setBounds(258, 211, 172, 39);
		principalPanel.add(lblElevatorStatus);
		
		btnEnterKeycard = new JButton("Enter");
		btnEnterKeycard.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEnterKeycard.setBounds(523, 367, 89, 23);
		principalPanel.add(btnEnterKeycard);
		
		lblCurrentFloorValue = new JLabel("0");
		lblCurrentFloor.setLabelFor(lblCurrentFloorValue);
		lblCurrentFloorValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentFloorValue.setFont(new Font("Arial", Font.BOLD, 12));
		lblCurrentFloorValue.setBounds(422, 111, 172, 39);
		principalPanel.add(lblCurrentFloorValue);
		
		lblAlarmStatusValue = new JLabel("OFF");
		lblAlarmStatus.setLabelFor(lblAlarmStatusValue);
		lblAlarmStatusValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmStatusValue.setFont(new Font("Arial", Font.BOLD, 12));
		lblAlarmStatusValue.setBounds(422, 161, 172, 39);
		principalPanel.add(lblAlarmStatusValue);
		
		lblElevatorStatusValue = new JLabel("IDLE");
		lblElevatorStatus.setLabelFor(lblElevatorStatusValue);
		lblElevatorStatusValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblElevatorStatusValue.setFont(new Font("Arial", Font.BOLD, 12));
		lblElevatorStatusValue.setBounds(422, 211, 172, 39);
		principalPanel.add(lblElevatorStatusValue);
		
		JLabel lblWeight = new JLabel("Enter weight:");
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setFont(new Font("Arial", Font.PLAIN, 12));
		lblWeight.setBounds(258, 253, 172, 39);
		principalPanel.add(lblWeight);
		
		txtWeight = new JTextField();
		lblWeight.setLabelFor(txtWeight);
		txtWeight.setFont(new Font("Arial", Font.PLAIN, 12));
		txtWeight.setColumns(10);
		txtWeight.setBounds(440, 261, 136, 31);
		txtWeight.setText("0");
		principalPanel.add(txtWeight);
		
		btnTurnOffAlarm = new JButton("Turn off Alarm");
		btnTurnOffAlarm.setFont(new Font("Arial", Font.PLAIN, 12));
		btnTurnOffAlarm.setBounds(601, 169, 118, 23);
		principalPanel.add(btnTurnOffAlarm);
		
		btnChangeWeight = new JButton("Change weight");
		btnChangeWeight.setFont(new Font("Arial", Font.PLAIN, 12));
		btnChangeWeight.setBounds(601, 261, 118, 23);
		principalPanel.add(btnChangeWeight);
	}
}

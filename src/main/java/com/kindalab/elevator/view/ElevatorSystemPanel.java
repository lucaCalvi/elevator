package com.kindalab.elevator.view;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
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
	private JComboBox<String> cmbSelectElevator, cmbSelectFloor;
	private JTextField txtWeight;
	private DefaultComboBoxModel dcbmElevator, dcbmFloor;

	public JTextField getTxtWeight() {
		return txtWeight;
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

	public DefaultComboBoxModel getDcbmElevator() {
		return dcbmElevator;
	}

	public DefaultComboBoxModel getDcbmFloor() {
		return dcbmFloor;
	}

	public void addElevatorActionListener(ElevatorActionListener elevatorActionListener) {
		btnCallElevator.addActionListener(elevatorActionListener);
		btnEnterKeycard.addActionListener(elevatorActionListener);
		btnChangeWeight.addActionListener(elevatorActionListener);
		btnTurnOffAlarm.addActionListener(elevatorActionListener);
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
		
		JLabel lblSelectElevator = new JLabel("Select elevator");
		lblSelectElevator.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectElevator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectElevator.setBounds(258, 86, 172, 39);
		principalPanel.add(lblSelectElevator);
		
		cmbSelectElevator = new JComboBox();
		cmbSelectElevator.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectElevator.setLabelFor(cmbSelectElevator);
		cmbSelectElevator.setBounds(440, 94, 136, 22);
		dcbmElevator = new DefaultComboBoxModel();
		principalPanel.add(cmbSelectElevator);
		
		JLabel lblSelectFloor = new JLabel("Select floor");
		lblSelectFloor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectFloor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectFloor.setBounds(258, 135, 172, 39);
		principalPanel.add(lblSelectFloor);
		
		cmbSelectFloor = new JComboBox();
		lblSelectFloor.setLabelFor(cmbSelectFloor);
		cmbSelectFloor.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbSelectFloor.setBounds(440, 143, 136, 22);
		dcbmFloor = new DefaultComboBoxModel();
		principalPanel.add(cmbSelectFloor);
		
		btnCallElevator = new JButton("Call elevator");
		btnCallElevator.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCallElevator.setBounds(355, 422, 153, 23);
		principalPanel.add(btnCallElevator);
		
		txtKeycard = new JTextField();
		txtKeycard.setFont(new Font("Arial", Font.PLAIN, 12));
		txtKeycard.setBounds(355, 345, 158, 31);
		principalPanel.add(txtKeycard);
		txtKeycard.setColumns(10);
		
		JLabel lblEnterKeycard = new JLabel("Enter keycard");
		lblEnterKeycard.setLabelFor(txtKeycard);
		lblEnterKeycard.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEnterKeycard.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterKeycard.setBounds(204, 341, 153, 39);
		principalPanel.add(lblEnterKeycard);
		
		btnEnterKeycard = new JButton("Enter");
		btnEnterKeycard.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEnterKeycard.setBounds(542, 349, 89, 23);
		principalPanel.add(btnEnterKeycard);
		
		JLabel lblWeight = new JLabel("Enter weight:");
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setFont(new Font("Arial", Font.PLAIN, 12));
		lblWeight.setBounds(211, 216, 143, 39);
		principalPanel.add(lblWeight);
		
		txtWeight = new JTextField();
		lblWeight.setLabelFor(txtWeight);
		txtWeight.setFont(new Font("Arial", Font.PLAIN, 12));
		txtWeight.setColumns(10);
		txtWeight.setBounds(372, 220, 136, 31);
		txtWeight.setText("0");
		principalPanel.add(txtWeight);
		
		btnTurnOffAlarm = new JButton("Turn off Alarm");
		btnTurnOffAlarm.setFont(new Font("Arial", Font.PLAIN, 12));
		btnTurnOffAlarm.setBounds(379, 286, 118, 23);
		principalPanel.add(btnTurnOffAlarm);
		
		btnChangeWeight = new JButton("Change weight");
		btnChangeWeight.setFont(new Font("Arial", Font.PLAIN, 12));
		btnChangeWeight.setBounds(541, 224, 118, 23);
		principalPanel.add(btnChangeWeight);
	}
}

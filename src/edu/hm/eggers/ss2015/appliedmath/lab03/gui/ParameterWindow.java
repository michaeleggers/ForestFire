package edu.hm.eggers.ss2015.appliedmath.lab03.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Forest;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.GameBoard;
import edu.hm.eggers.ss2015.appliedmath.lab03.simulator.Simulator;

/**
 * This GUI initializes a new GameBoard and Simulates it. It then calls the other
 * GUI elements to display the simulation.
 * 
 * @author Michi
 *
 */
public class ParameterWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	
	private JLabel cyclesLabel;
	
	private JLabel pInitFireLabel;
	
	private JLabel pIgniteLabel;
	
	private JLabel pZeusLabel;
	
	private JLabel maxLifeCyclesLabel;
	
	private JLabel windDirectionLabel;
	
	private JLabel windForceLabel;
	
	private JTextField cyclesTextField;
	
	private JTextField pInitFireTextField;
	
	private JTextField pIgniteTextField;
	
	private JTextField pZeusTextField;
	
	private JTextField maxLifeCyclesTextField;
	
	private JComboBox windDirBox;
	
	private JComboBox windForceBox;
	
	private JLabel forestSizeLabel;
	
	private JTextField forestSizeXTF;
	
	private JTextField forestSizeYTF;
	
	private JLabel forestInitTreesLabel;
	
	private JTextField forestInitTreesTF;
	
	private JButton createButton;
	
	

	
	public ParameterWindow(){
		super("Simulation Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		initializeComponents();
		setVisible(true);
	}
	
	private void initializeComponents(){
		mainPanel = new JPanel(new GridBagLayout());
		cyclesLabel = new JLabel("cycles:");
		pInitFireLabel = new JLabel("pInitFire:");
		pIgniteLabel = new JLabel("pIgnite:");
		pZeusLabel = new JLabel("pZeus:");
		maxLifeCyclesLabel = new JLabel("max life cycles:");
		windDirectionLabel = new JLabel("wind direction:");
		windForceLabel = new JLabel("wind force:");
		cyclesTextField = new JTextField(5);
		pInitFireTextField = new JTextField(5);
		pIgniteTextField = new JTextField(5);
		pZeusTextField = new JTextField(5);
		maxLifeCyclesTextField = new JTextField(5);
		windDirBox = new JComboBox(new String[]{"north","east","south","west"});
		windForceBox = new JComboBox(new String[]{"off","weak","strong"});
		forestSizeLabel = new JLabel("forest size (x/y)");
		forestSizeXTF = new JTextField(5);
		forestSizeYTF = new JTextField(5);
		forestInitTreesLabel = new JLabel("pInitTrees");
		forestInitTreesTF = new JTextField(5);
		createButton = new JButton("create simulation");
		
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4,4,4,4);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(cyclesLabel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		mainPanel.add(cyclesTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(pInitFireLabel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		mainPanel.add(pInitFireTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		mainPanel.add(pIgniteLabel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		mainPanel.add(pIgniteTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		mainPanel.add(pZeusLabel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		mainPanel.add(pZeusTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		mainPanel.add(maxLifeCyclesLabel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 4;
		mainPanel.add(maxLifeCyclesTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		mainPanel.add(windDirectionLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		mainPanel.add(windDirBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		mainPanel.add(windForceLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		mainPanel.add(windForceBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 8;
		mainPanel.add(forestSizeLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 8;
		mainPanel.add(forestSizeXTF, gbc);
		gbc.gridx = 2;
		gbc.gridy = 8;
		mainPanel.add(forestSizeYTF, gbc);
		gbc.gridx = 0;
		gbc.gridy = 9;
		mainPanel.add(forestInitTreesLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 9;
		mainPanel.add(forestInitTreesTF, gbc);
		gbc.gridx = 0;
		gbc.gridy = 10;
		mainPanel.add(createButton, gbc);
		

		add(mainPanel);
		
		registerListener();
		
	}
	
	private void registerListener(){
		createButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e){
				final GameBoard forest = new Forest(Integer.parseInt(forestSizeXTF.getText()),
													Integer.parseInt(forestSizeYTF.getText()),
													Double.parseDouble(forestInitTreesTF.getText())).frame();
				
				final Simulator simulator = new Simulator(forest,
														  Integer.parseInt(cyclesTextField.getText()),
														  Double.parseDouble(pInitFireTextField.getText()),
														  Double.parseDouble(pIgniteTextField.getText()),
														  Double.parseDouble(pZeusTextField.getText()),
														  Integer.parseInt(maxLifeCyclesTextField.getText()),
														  windDirBox.getSelectedIndex()+1,
														  windForceBox.getSelectedIndex());
				simulator.initialize();
				simulator.simulate();
				final Viewer viewer = new Viewer(simulator);
				viewer.startGUI();
				
			}
		});
	}
}

package edu.hm.eggers.ss2015.appliedmath.lab03.gui;

import edu.hm.eggers.ss2015.appliedmath.lab03.simulator.Simulator;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Viewer initializes all data necessary to display the GameBoard.
 * 
 * @author Michi
 *
 */
public class Viewer extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel dataPanel;
	
	private JPanel controlPanel;
	
	private JPanel graphicsPanel;
	
	private DrawingComponent dc;
	
	private JLabel iterationsLabel;
	
	private JLabel pInitFireLabel;
	
	private JLabel pIgniteLabel;
	
	private JLabel pZeusLabel;
	
	private JLabel maxLifeCyclesLabel;
	
	private JLabel windForceLabel;
	
	private JLabel windDirectionLabel;
	
	private JButton nextButton;
	
	private JButton previousButton;
	
	private final Simulator simulator;
	
	private JFrame forestViewer;
	
	private int counter;
	
	
	public Viewer(final Simulator simulator){
		super("Forest Fire Simulator");
		this.simulator = simulator;
		setSize(300, 300);
		

		//setLayout(new GridLayout());
		initializeComponents();
		forestViewer = new JFrame("Forest Viewer");
		forestViewer.add(dc);
		forestViewer.setSize(dc.getSize());
		forestViewer.setVisible(true);
	}
	
	private Simulator getSimulator(){
		return simulator;
	}
	
	private void initializeComponents(){
		dataPanel = new JPanel(new GridBagLayout());
		controlPanel = new JPanel(new GridBagLayout());
		graphicsPanel = new JPanel(new GridBagLayout());
		counter = 0;
		dc = new DrawingComponent(getSimulator().getSimulatedBoards().get(0));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		
		
		iterationsLabel = new JLabel(String.format("cycles: %d", getSimulator().getCycles()));
		pInitFireLabel = new JLabel(String.format("pInitFire: %.3f", getSimulator().getpInitFire()));
		pIgniteLabel = new JLabel(String.format("pIgnite: %.3f", getSimulator().getpIgnite()));
		pZeusLabel = new JLabel(String.format("pZeus: %.3f", getSimulator().getpZeus()));
		maxLifeCyclesLabel = new JLabel(String.format("maxLifeCycles: %d", getSimulator().getMaxLifeCycles()));
		
		final String windDirectionString;
		final int windDirectionValue = getSimulator().getWindDirection();
		if(windDirectionValue == 1)
			windDirectionString = "North";
		else if(windDirectionValue == 2)
			windDirectionString = "East";
		else if(windDirectionValue == 3)
			windDirectionString = "South";
		else
			windDirectionString = "West";
		
		final String windForceString;
		final int windForceValue = getSimulator().getWindForce();
		if(windForceValue == 1)
			windForceString = "weak";
		else if(windForceValue == 2)
			windForceString = "strong";
		else
			windForceString = "no wind";
		
		windDirectionLabel = new JLabel(String.format("wind direction: %s", windDirectionString));
		windForceLabel = new JLabel(String.format("wind force: %s", windForceString));
		nextButton = new JButton("next");
		previousButton = new JButton("previous");

		gbc.gridx = 0;
		gbc.gridy = 0;
		dataPanel.add(iterationsLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		dataPanel.add(pInitFireLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 20;
		dataPanel.add(pIgniteLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 30;
		dataPanel.add(pZeusLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 40;
		dataPanel.add(maxLifeCyclesLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 60;
		dataPanel.add(windForceLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 80;
		dataPanel.add(windDirectionLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		controlPanel.add(previousButton, gbc);
		
		gbc.gridx = 20;
		gbc.gridy = 0;
		controlPanel.add(nextButton, gbc);
		graphicsPanel.add(dc, gbc);
		
		//add(graphicsPanel, BorderLayout.WEST);
		add(dataPanel, BorderLayout.NORTH);
		add(controlPanel, BorderLayout.SOUTH);
		
		registerListener();
	}
	
	private void registerListener(){
		nextButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e){
				if(counter < getSimulator().getSimulatedBoards().size() -1){
					dc.update(getSimulator().getSimulatedBoards().get(++counter));
					forestViewer.repaint();
				}
			}
		});
		
		previousButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e){
				if(counter > 0){
					dc.update(getSimulator().getSimulatedBoards().get(--counter));
					forestViewer.repaint();
				}
			}
		});
	}

	public void startGUI(){
		setVisible(true);
	}
	
}

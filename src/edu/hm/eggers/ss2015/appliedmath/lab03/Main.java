package edu.hm.eggers.ss2015.appliedmath.lab03;


import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.GameBoard;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Forest;
import edu.hm.eggers.ss2015.appliedmath.lab03.gui.ParameterWindow;
import edu.hm.eggers.ss2015.appliedmath.lab03.gui.Viewer;
import edu.hm.eggers.ss2015.appliedmath.lab03.simulator.Simulator;

/* (C) 2015, Michael Eggers, eggers@hm.edu
 * 
 * Munich university of applied sciences (MUAS)
 * 
 * Forest Fire simulator
 * 
 * Course: Applied Mathematics
 * Professor: Dr. Rainer Fischer
 * Lab: Simulation of a forest fire.
 * 
 * Java 1.8.0_20, x64
 * rohan (Windows 7 Pro 64, Intel i7 4930K@3.9GHz, 16GB RAM)
 */


// TODO move generic functionality from Forest class to ABC or static Iface methods.
// TODO only let ParameterWindow generate the GameBoard and the Simulation, then hand it over to other GUI elements in main.
// TODO fix wind simulation in Simulation class. Got damaged during refactoring...
// TODO move Viewer GUI into the DrawGameboard GUI (too many windows...)
// TODO ask Prof. Schiedermeier: ImageLoader.class.getResourceAsStream (syntax).
/**
 * Main controls the flow of the simulation pipeline.
 * A GameBoard represents a rectangular pattern that consists of so called Fields.
 * In the case of a forest fire simulation we need a forest, so our GameBoard
 * is a forest that consists of trees and empty fields.
 * 
 * To initialize a new forest the following parameters are required:
 * - sizeX : horizontal size of the forest.
 * - sizeY : vertical size of the forest.
 * - tree density : 0.0 -> the forest has no trees; 1.0 -> every fields is a tree.
 * 
 * The forest object is fed to the Simulator where the simulation is being computed.
 * The fire will spread out across the forest depending on the following
 * parameters of the simulator:
 * - gameboard : the forest you want  to ignite.
 * - cycles : iterations of the simulation.
 * - pInitFire : 0.0 -> one single fire at the beginning of the simulation, 1.0 -> the whole forest is already burning.
 * - pIgnite : Probability that a tree will catch fire if either its northern, easter, southern or western neighbors are burning.
 * - pZeus : Probability of Zeus being in a bad mood and throwing thunderbolts down to the forest thus will set a tree under fire.
 * - maxLifeCycles : maximum life cycles of a fire field.
 * - windDirection : Direction the wind is blowing to. 1 = North, 2 = East, 3 = South, 4 = West.
 * - windForece : How strong is the wind blowing? 0 = no wind, 1 = weak, 2 = strong.
 * @author Michael Eggers, eggers@hm.edu
 * @version 2015-05-23
 * @version 2015-05-07 added wind force and wind direction.
 * @version	2015-05-23	new GUI element "ParameterWindow" to setup the simulation.
 *
 */
public class Main {

	public static void main(String[] args) {
//		// initialize new forest and frame it with empty fields.
//		final GameBoard gameboard = new Forest(20, 20, 0.5).frame();
//		
//		// Generate a simulator.
//		final Simulator sim = new Simulator(gameboard, 				// the forest
//										    15, 					// cycles of simulation
//										    0.01, 					// anteilFeuer
//										    0.8, 					// pBrand
//										    0.5, 					// pBlitz
//										    1, 						// life cycles of a fire
//										    1, 						// Windrichtung
//										    0);						// Windstaerke
//		
//		// initialize the simulator.
//		sim.initialize();
//		
//		// Simulate the forest fire.
//		sim.simulate();
//		
//		// output to GUI.
//		final Viewer viewer = new Viewer(sim);
//		viewer.startGUI();
		
		// no only the GUI is used to create a forest and its simulation.
		final ParameterWindow pWindow = new ParameterWindow();
	}
}

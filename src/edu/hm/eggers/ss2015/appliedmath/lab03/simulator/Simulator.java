package edu.hm.eggers.ss2015.appliedmath.lab03.simulator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Empty;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Field;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Fire;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Forest;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.GameBoard;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Thunderbolt;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Tree;

/**
 * Simulates a fire that spreads out across the forest.
 * Possibility that thunderbolts hits trees and ignite them is also given.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @version 2015-05-03
 *
 */
public class Simulator implements Iterable<GameBoard>{
	
	/** The GameBoard (Forest) we want to use for a nice forest fire simulation. */
	private final GameBoard initialBoard;
	
	/** Contains all the simulated GameBoards. */
	private final List<GameBoard> simulatedBoards = new ArrayList<>();
	
	/** Iterations of the simulation. */
	private final int cycles;
	
	/** Initial Fire-Fields. Has to be 0.0 to start a single fire in the middle of the GameBoard. */
	private final double pInitFire;
	
	/** Probability that a field will catch fire if either a northern, eastern, southern or western neighbor
	 * is already burning.
	 */
	private final double pIgnite;
	
	/** Probability of Zeus throwing a bolt onto any Field. */
	private final double pZeus;
	
	/** Maximum lifespan of a fire-field. */
	private final int maxLifeCycles;
	
	/** Direction of the wind. */
	private final int windDirection;
	
	/** Force of the wind. */
	private final int windForce;
	
	/**
	 * Initializes the Simulator.
	 * All Parameters beginning with 'p' are values between 0.0 and 1.0.
	 * 
	 * @param board GameBoard to be simulated. For now its just a Forest.
	 * @param cycles Duration of the simulation.
	 * @param pInitFire Initial Fields on fire. 0.0 is one single Fire in the middle of the GameBoard.
	 * @param pIgnite A Probability of a Field getting ignited by its neighbors.
	 * @param pZeus Probability of Zeus throwing a bolt
	 * @param maxLifeCycles Durability of a field ( in this case a fire ).
	 * @param windDirection Direction the wind is blowing to.
	 * @param windForce Force of the wind: 0 = no wind, 1 = soft wind,  2 = strong wind.
	 * @throws NullPointerException if board is null.
	 * @throws IllegalArgumentException if primitive type are not within its defined range.
	 */
	public Simulator(final GameBoard board, 
					 final int cycles, 
					 final double pInitFire, 
					 final double pIgnite, 
					 final double pZeus, 
					 final int maxLifeCycles, 
					 final int windDirection, 
					 final int windForce){
		if(board == null)
			throw new NullPointerException();
		if(cycles < 1 || pInitFire < 0.0 || pInitFire > 1.0 || pIgnite < 0.0 || pIgnite > 1.0 || pZeus < 0.0 || pZeus > 1.0 || maxLifeCycles < 0)
			throw new IllegalArgumentException();
		initialBoard = board;
		this.cycles = cycles;
		this.pInitFire = pInitFire;
		this.pIgnite = pIgnite;
		this.pZeus = pZeus;
		this.maxLifeCycles = maxLifeCycles;
		this.windDirection = windDirection;
		this.windForce = windForce;
	}

	public GameBoard getInitialBoard() {
		return initialBoard;
	}

	public int getCycles() {
		return cycles;
	}

	public double getpInitFire() {
		return pInitFire;
	}

	public double getpIgnite() {
		return pIgnite;
	}

	public double getpZeus() {
		return pZeus;
	}
	
	public int getMaxLifeCycles(){
		return maxLifeCycles;
	}

	public List<GameBoard> getSimulatedBoards() {
		return simulatedBoards;
	}

	public int getWindDirection() {
		return windDirection;
	}

	public int getWindForce() {
		return windForce;
	}

	/**
	 * Initializes the simulation.
	 */
	public void initialize(){
		
		// Make a copy of the original GameBoard.
		final GameBoard initializedGameBoard = new  Forest(getInitialBoard().getSizeX(), getInitialBoard().getSizeY(), getInitialBoard().getBoard());
		final int sizeX = initializedGameBoard.getSizeX();
		final int sizeY = initializedGameBoard.getSizeY();
		
		// Ignite trees depending on pInitFire.
		if(getpInitFire() == 0){
			initializedGameBoard.setField(sizeX/2, sizeY/2, new Fire());
		}
		else{
			for(int rowIndex = 0; rowIndex < sizeY; rowIndex++){
				for(int columnIndex = 0; columnIndex < sizeX; columnIndex++){
					if(initializedGameBoard.getThisField(rowIndex, columnIndex)  instanceof Tree && Math.random() < getpInitFire())
						initializedGameBoard.setField(rowIndex, columnIndex, new Fire());
				}
			}
		}
		getSimulatedBoards().add(initializedGameBoard);
	}
	
	/**
	 * Simulates a forest fire.
	 */
	public void simulate(){
		
		final double reduceThreshold;
		final int wind = getWindForce();
		System.out.println(getWindDirection());
		
		if(wind == 1)
			reduceThreshold = 0.5;
		else if(wind == 2)
			reduceThreshold = 0.25;
		else
			reduceThreshold  = 1.0;
		
		for(int iteration = 0; iteration < getCycles(); iteration++){
			// Get last simulated GameBoard out of the List and make a copy.
			final GameBoard lastSimulatedBoard = getSimulatedBoards().get(getSimulatedBoards().size()-1);
			final int sizeX = lastSimulatedBoard.getSizeX();
			final int sizeY = lastSimulatedBoard.getSizeY();
			final GameBoard gameboardToSimulate = new Forest(sizeX, sizeY, lastSimulatedBoard.getBoard());
			final double zeusThreshold = Math.random();
			final int randomColumn = (int)(Math.random() * sizeX);
			final int randomRow = (int)(Math.random() * sizeY);
			final Field randomField = gameboardToSimulate.getThisField(randomRow, randomColumn);
			
			// The simulation is being made here.
			for(int rowIndex = 0; rowIndex < sizeY; rowIndex++){
				for(int columnIndex = 0; columnIndex < sizeX; columnIndex++){
					final Field actualField = lastSimulatedBoard.getThisField(rowIndex, columnIndex);
					final Field northernField = lastSimulatedBoard.getNorth(rowIndex, columnIndex);
					final Field easternField = lastSimulatedBoard.getEast(rowIndex, columnIndex);
					final Field southernField = lastSimulatedBoard.getSouth(rowIndex, columnIndex);
					final Field westernField = lastSimulatedBoard.getWest(rowIndex, columnIndex);
					final double ignitionThreshold = Math.random();
					final double reducedThreshold = ignitionThreshold * reduceThreshold;
					
					if(actualField instanceof Tree && wind != 0){
						if(getWindDirection() == 1){
							if(reducedThreshold < getpIgnite() && southernField instanceof Fire){
								gameboardToSimulate.setField(rowIndex, columnIndex, new Fire());
							}
						}
						if(getWindDirection() == 2){
							if(reducedThreshold < getpIgnite() && westernField instanceof Fire){
								gameboardToSimulate.setField(rowIndex, columnIndex, new Fire());
							}
						}
						if(getWindDirection() == 3){
							if(reducedThreshold < getpIgnite() && northernField instanceof Fire){
								gameboardToSimulate.setField(rowIndex, columnIndex, new Fire());
							}
						}
						if(getWindDirection() == 4){
							if(reducedThreshold < getpIgnite() && easternField instanceof Fire){
								gameboardToSimulate.setField(rowIndex, columnIndex, new Fire());
							}
						}
					}
					if(actualField instanceof Tree && ignitionThreshold < getpIgnite()){
						if( (northernField instanceof Fire) || (easternField instanceof Fire) || (southernField instanceof Fire) || (westernField instanceof Fire))
							gameboardToSimulate.setField(rowIndex, columnIndex, new Fire());
					}
					
					if(actualField instanceof Fire && actualField.getLifeCycles() >= getMaxLifeCycles()-1)
						gameboardToSimulate.setField(rowIndex, columnIndex, new Empty());
					if(randomField instanceof Tree && zeusThreshold < getpZeus()){
						gameboardToSimulate.setField(randomRow, randomColumn, new Thunderbolt());
					}
					if(actualField instanceof Thunderbolt)
						gameboardToSimulate.setField(rowIndex, columnIndex, new Fire());
					
					actualField.setLifeCycles(actualField.getLifeCycles()+1);
				}
			}
			getSimulatedBoards().add(gameboardToSimulate);
		}
	}
	
	@Override public SimulationIterator iterator(){
		return new SimulationIterator();
	}
	
	private final class SimulationIterator implements Iterator<GameBoard> {
		
		private int counter;
		
		public SimulationIterator(){
			counter = 0;
		}
		
		@Override public boolean hasNext(){
			return counter < getSimulatedBoards().size();
		}
		
		@Override public GameBoard next(){
			return getSimulatedBoards().get(counter++);
		}
	}
}

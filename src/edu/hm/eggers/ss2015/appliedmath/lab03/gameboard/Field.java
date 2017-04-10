package edu.hm.eggers.ss2015.appliedmath.lab03.gameboard;

/**
 * Represents a field.
 * A field can be anything, it is just a tile that stores information of what it is (tree, fire, etc...)
 * and how long it is already living.
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public abstract class Field {

	private final char fieldType;
	
	/** How many cycles does this field already exist? */
	private int lifeCycles = 0;
	
	public Field(final char fieldType){
		this.fieldType = fieldType;
	}
	
	public int getLifeCycles() {
		return lifeCycles;
	}

	public void setLifeCycles(int lifeCycles) {
		this.lifeCycles = lifeCycles;
	}

	@Override public String toString(){
		return String.format("%c", fieldType);
	}
}

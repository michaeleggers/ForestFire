package edu.hm.eggers.ss2015.appliedmath.lab03.gameboard;

import java.util.List;

/**
 * Will be amended to ABC...
 * So far it supports basic functionality a gameboard should have
 * 
 * @author Michael Eggers, eggers@hm.edu
 *
 */
public interface GameBoard extends Iterable<Field> {

	Field getEast(int rowIndex, int columnIndex);
	
	Field getSouth(int rowIndex, int columnIndex);
	
	Field getWest(int rowIndex, int columnIndex);
	
	Field getNorth(int rowIndex, int columnIndex);
	
	Field getThisField(int rowIndex, int columnIndex);
	
	List<Field> getBoard();
	
	int getSizeX();
	
	int getSizeY();
	
	void  setField(int rowIndex, int columnIndex, Field field);
}

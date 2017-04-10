package edu.hm.eggers.ss2015.appliedmath.lab03.gameboard;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

// TODO Shift generic functionality to an ABC
// TODO Think about a better data-structure for storing fields.
/**
 * Represents a forest.
 * A forests consists of trees and empty fields.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @version 2015-05-02
 *
 */
public class Forest implements GameBoard, Iterable<Field>{
	
	/** Stores data about the forest in a List.
	 * Locate Field via rowIndex, columnIndex.
	 * Formula for indexing: sizeX * columnIndex + rowIndex.
	 */
	private final List<Field> board = new ArrayList<>();
	
	/** Horizontal size of the forest. */
	private final int sizeX;
	
	/** Vertical size of the forest. */
	private final int sizeY;
	
	/**
	 * Creates a new Forest.
	 * 
	 * @param sizeX Horizontal size of the forest.
	 * @param sizeY Vertical size of the forest.
	 * @param pTree Density of trees depending on a probability. pTree between 0.0 (no trees) and 1.0 (every field is a tree).
	 */
	public Forest(final int sizeX, final int sizeY, final double pTree){
		if(pTree > 1.0 || pTree < 0.0 || sizeX < 1 || sizeY < 1)
			throw  new IllegalArgumentException();
		final int forestSize = sizeX * sizeY;
		
		for(int index = 0; index < forestSize; index++){
			if(Math.random() <  pTree)
				board.add(new Tree());
			else
				board.add(new Empty());
		}
		this.sizeX = sizeX;
		this.sizeY = sizeY;

	}
	
	/**
	 * Custom Ctor.
	 * 
	 * @param sizeX
	 * @param sizeY
	 * @param board
	 */
	public Forest(final int sizeX, final int sizeY, final List<Field> board){
		this.board.addAll(board);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	/**
	 * Copy Ctor.
	 * 
	 * @param forest Forest to copy.
	 */
	public Forest(final Forest forest){
		this.sizeX = forest.getSizeX();
		this.sizeY = forest.getSizeY();
		this.board.addAll(forest.getBoard());
	}
	
	@Override public List<Field> getBoard(){
		return board;
	}

	@Override public int getSizeX() {
		return sizeX;
	}

	@Override public int getSizeY() {
		return sizeY;
	}

	@Override public Field getEast(int rowIndex, int columnIndex){
		if(columnIndex+1 < sizeX)
			return board.get(getSizeX() * rowIndex + columnIndex+1);
		return null;
	}

	@Override public Field getSouth(int rowIndex, int columnIndex){
		if(rowIndex+1 < sizeY)
			return board.get(getSizeX() * (rowIndex+1) + columnIndex);
		return null;
	}

	@Override public Field getWest(int rowIndex, int columnIndex){
		if(columnIndex-1 >= 0)
			return board.get(getSizeX() * rowIndex + columnIndex-1);
		return null;
	}

	@Override public Field getNorth(int rowIndex, int columnIndex){
		if(rowIndex-1 >= 0)
			return board.get(getSizeX() * (rowIndex-1) + columnIndex); 
		return null;
	}

	@Override public Field getThisField(int rowIndex, int columnIndex){
		if(rowIndex < sizeY && columnIndex < sizeX)
			return board.get(getSizeX() * rowIndex + columnIndex);
		return null;
	}
	
	@Override public void setField(final int rowIndex, final int columnIndex, final Field field){
		getBoard().set(getSizeX()*rowIndex + columnIndex, field);
	}
	
	
	// TODO make utility class Boards and let it do the framing-job.
	/** 
	 * Frames an existing Forest with Empty fields.
	 * 
	 * @return Copy of an existing Forest that is now framed.
	 */
	public Forest frame(){
		final List<Field> boardCopy = new ArrayList<>();
		boardCopy.addAll(getBoard());
		
		final List<Field> topBottomFrame = new ArrayList<>();
		for(int index = 0; index < getSizeX()+2; index++)
			topBottomFrame.add(new Empty());

		for(int rowIndex = 0; rowIndex < getSizeY(); rowIndex++){
			boardCopy.add(rowIndex*getSizeX()+2*rowIndex, new Empty());
			boardCopy.add(rowIndex*getSizeX()+getSizeX()+(2*rowIndex)+1, new Empty());
		}
		boardCopy.addAll(0, topBottomFrame);
		boardCopy.addAll(boardCopy.size(), topBottomFrame);
		return new Forest(getSizeX()+2, getSizeY()+2, boardCopy);
	}

	@Override public String toString(){
		StringBuilder result = new StringBuilder();
		for(int rowIndex = 0; rowIndex < getSizeY(); rowIndex++){
			for(int columnIndex = 0; columnIndex < getSizeX(); columnIndex++){
				result.append(getThisField(rowIndex, columnIndex)).append(" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	@Override public ForestIterator iterator(){
		return new ForestIterator();
	}
	
	/**
	 * An Iterator cycles through the Field elements of a Forest.
	 * 
	 * @author Michi
	 *
	 */
	private final class ForestIterator implements Iterator<Field> {
		
		/** Counter necessary to check where we are in the list. */
		private int counter;
		
		public ForestIterator(){
			counter = 0;
		}
		
		@Override public boolean hasNext(){
			return counter < getBoard().size();
		}
		
		@Override public Field next(){
			return getBoard().get(counter++);
		}

	}
	
}

package edu.hm.eggers.ss2015.appliedmath.lab03.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import edu.hm.eggers.ss2015.appliedmath.lab03.ImageLoader;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Empty;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Field;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Fire;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.GameBoard;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Thunderbolt;
import edu.hm.eggers.ss2015.appliedmath.lab03.gameboard.Tree;

/**
 * Draws the GameBoard to a dedicated JComponent
 * 
 * @author Michi
 *
 */
public class DrawingComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage emptyIMG;
	private BufferedImage fireIMG;
	private BufferedImage thunderboltIMG;
	private BufferedImage treeIMG;
	private GameBoard gameboard;
	
	DrawingComponent(final GameBoard gameboard){
		try{
			treeIMG = ImageIO.read(ImageLoader.load("tree.png"));
			emptyIMG = ImageIO.read(ImageLoader.load("empty.png"));
			fireIMG = ImageIO.read(ImageLoader.load("fire.png"));
			thunderboltIMG = ImageIO.read(ImageLoader.load("thunderbolt.png"));
		}
		catch(IOException e){
			System.out.println("path not found");
		}
		this.gameboard = gameboard;
		setSize(gameboard.getSizeX()*32, gameboard.getSizeY()*32);
	}
	
	private GameBoard getGameBoard(){
		return gameboard;
	}
	
	public void update(final GameBoard gameboard){
		this.gameboard = gameboard;
	}

	@Override public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(emptyIMG, null, 0, 0);
		
		final int sizeX = getGameBoard().getSizeX();
		final int sizeY = getGameBoard().getSizeY();

		for(int rowIndex = 0; rowIndex < sizeY; rowIndex++){
			for(int columnIndex = 0; columnIndex < sizeX; columnIndex++){
				final Field actualField = getGameBoard().getThisField(rowIndex, columnIndex);
				if(actualField instanceof Empty)
					g2d.drawImage(emptyIMG, columnIndex*32, rowIndex*32, null);
				if(actualField instanceof Tree)
					g2d.drawImage(treeIMG, columnIndex*32, rowIndex*32, null);
				if(actualField instanceof Fire)
					g2d.drawImage(fireIMG, columnIndex*32, rowIndex*32, null);
				if(actualField instanceof Thunderbolt)
					g2d.drawImage(thunderboltIMG, columnIndex*32, rowIndex*32, null);
			}
		}
	}


}

package de.woock.games.breakout.assets.artefacts;

import java.applet.Applet;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import de.woock.games.breakout.Breakout;
import de.woock.games.breakout.items.impl.Tile;
import de.woock.games.breakout.util.ResourceLocator;

public class Field {
	private final int rowSpace  = 15;
	private final int colSpace  = 30;
	private final int rowOffset = 10;
	
	private Applet applet    = null;
	private List<Tile> tiles = null;
	private int rows         = 0;
	private int cols         = 0;
	
	private List<Image>  images = new ArrayList<Image>();
	private List<String> colors = new ArrayList<String>(); 
	
	
	public Field(Applet applet, int rows, int cols) {
		this.applet = applet;
		this.tiles  = new ArrayList<Tile>(rows*cols);
		this.rows   = rows;
		this.cols   = cols;
		
		colors.add("yellow");
		colors.add("blue");
		colors.add("green");
		colors.add("violett");
		colors.add("purple");
		colors.add("orange");
		colors.add("red");
		
	}


	public Field createField(int level) {
		loadTileImages();
	   	for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				int points = (row + 1) * 20;
				tiles.add(Tile.getTile(applet, images.get(row), points, rowOffset + col * colSpace, 50 + row * rowSpace));
			}
		}
	   	return this;
	}
	
	private void loadTileImages() {
		for (String color : colors) {
			images.add(ResourceLocator.getImage((Breakout)applet, "Tile_" + color + ".jpg")); 
		}
	}
	
	public List<Tile> getTiles() {
		return tiles;
	}
}

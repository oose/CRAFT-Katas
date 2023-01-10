package de.woock.games.breakout.items.impl;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.woock.games.breakout.items.AbstractDrawableItem;

public class Tile extends AbstractDrawableItem {
	
	private Logger log = LogManager.getLogger(Tile.class);

	public  int   points = 0;
	private Token token  = null;
	private Map<Integer, Token> tokens = null;

	private Tile(Applet applet, Image image, int points, int xPos, int yPos) {
		this.applet = applet;
		this.image  = image;
		this.points = points;
		this.xPos   = xPos;
		this.yPos   = yPos;
		this.token  = createToken();
		if (token != null) token.setPosition(xPos, yPos);
	}
	
	public static Tile getTile(Applet applet, Image image, int points, int xPos, int yPos) {
		return new Tile(applet, image, points, xPos, yPos);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, (int)xPos, (int)yPos, applet);
		shape = new Rectangle((int)xPos, (int)yPos, image.getWidth(applet), image.getHeight(applet));
	}

	public boolean intersects(Rectangle item) {
		return shape != null && item != null && shape.intersects(item);
	}

	public Token createToken() {
		if (tokens == null) {
			createTokenList();
		}
		Token token = (new Random().nextInt(10) == 0) 
				    ? tokens.get(new Random().nextInt(9)) 
                    : null;
		if (token != null) log.info("create token: " + token);
				
		return token;
	}
	
	public Token getToken() {
		return token;
	}

	private void createTokenList() {
		tokens = new HashMap<Integer, Token>();
		tokens.put(0, new Token(Color.GREEN , "L", 100)); // shot
		tokens.put(1, new Token(Color.CYAN  , "M",  90)); // multiply
		tokens.put(2, new Token(Color.CYAN  , "S",  80)); // slow
		tokens.put(3, new Token(Color.CYAN  , "T",  70)); // two balls
		tokens.put(4, new Token(Color.RED   , "F",  60)); // fast
		tokens.put(5, new Token(Color.CYAN  , "H",  50)); // hold
		tokens.put(6, new Token(Color.ORANGE, "N",  40)); // narrow racket
		tokens.put(7, new Token(Color.CYAN  , "D",  30)); // double laser
		tokens.put(8, new Token(Color.BLUE  , "W",  20)); // wide racket
	}
}

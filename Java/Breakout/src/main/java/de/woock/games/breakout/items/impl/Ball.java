package de.woock.games.breakout.items.impl;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.woock.games.breakout.Breakout;
import de.woock.games.breakout.items.AbstractDrawableItem;
import de.woock.games.breakout.util.ResourceLocator;

public class Ball extends AbstractDrawableItem {

	Logger log = LogManager.getLogger(Ball.class);

	private final int radius = 10;
	private double xBallSpeed = 1;
	private double yBallSpeed = 1;
	private int yBalldirection = 1;
	private int xBalldirection = 1;

	public boolean running = false;
	public boolean ballOff = false;

	private AudioClip bounce = null;

	public Ball(Breakout breakout, double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;

		xBalldirection = 1;
		yBalldirection = 1;

		ballOff = false;

		bounce = ResourceLocator.getSound(breakout, "hit.au");
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval((int) xPos, (int) yPos, radius, radius);

		shape = new Rectangle((int) xPos, (int) yPos, radius, radius);
	}

	public void moveXY(Rectangle racketShape) {
		xPos += xBallSpeed * xBalldirection;
		if (running) {
			yPos += yBallSpeed * yBalldirection;
		}
		// TODO: sollte in Breakout passieren
		hitRacket(racketShape);
		hitBorder();
	}

	public void freezeOnRacket(Racket racket) {
		xPos = racket.getMiddleX(radius);
		yPos = racket.getOnRacketY(radius);
	}

	public void changeDirectionY() {
		yBalldirection *= -1;

	}

	public boolean hits(Tile tile) {
		boolean didHit = shape != null && tile != null && shape.intersects(tile.getShape());
		if (didHit) {
			log.info("hit Tile");
		}
		return didHit;
	}

	private void hitRacket(Rectangle racketShape) {
		if (shape != null && racketShape != null) {
			if (shape.intersects(racketShape)) {
				bounce.play();
				yBalldirection = -1;
				int racketHalf = racketShape.width / 2;

				// left side
				double d = 1;
				if (racketShape.contains(xPos + radius / 2 + racketHalf, yPos + radius)) {
					d = xPos + radius / 2 - racketShape.x;
					xBalldirection = -1;
					// right side
				} else {
					d = racketShape.x + racketShape.width - xPos + radius / 2;
					xBalldirection = 1;
				}
				xBallSpeed = (-1 / 21.0 * d + 4) / 2;
				yBallSpeed = (5.5 - xBallSpeed) / 2;
			}
		}
	}

	private void hitBorder() {
		if (xPos + radius > Breakout.fieldWidth)
			xBalldirection = -1;
		if (xPos < 0) {
			xBalldirection = 1;
			log.info("hit border: " + xPos);
		}
		if (yPos > Breakout.fieldHight)
			ballOff = true;
		if (yPos < 0)
			yBalldirection = 1;
	}
}

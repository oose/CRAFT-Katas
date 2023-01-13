package de.oose.breakout.items.impl;


import de.oose.breakout.Breakout;
import de.oose.breakout.util.ResourceLocator;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.logging.Logger;


public class Ball extends Circle {

    private final int radius = 10;
    //local variables for x and y position
    //this is not used for moving the ball.
    // instead bx and by from breakout.class is used for moving
    public double x;
    public double y;
    public boolean running = false;
    public boolean ballOff = false;
    //initlializing the logger for all the ball events
    Logger log = Logger.getLogger(Ball.class.getName());
    private double xBallSpeed = 1;
    private double yBallSpeed = 1;
    private int yBalldirection = 1;
    private int xBalldirection = 1;
    private AudioClip bounce = null;

    private Breakout breakout;

    public Ball(double radius, Paint fill, Breakout breakout) {
        super(radius, fill);
        bounce = ResourceLocator.getSound("laser.wav");
        ballOff = false;
        this.breakout = breakout;
    }

    //sets the location of the ball
    //when running also move on y axis.
    // if not, it just moves on x axis. this is use when the ball is fixed on racket
    public void move(double bx, double by, Racket racket) {
        this.x = bx;
        this.y = by;
        this.setLayoutX(this.getLayoutX() + bx);
        if (running) {
            this.setLayoutY(this.getLayoutY() + by);
        }
    }

    //relocates the ball to the center of the racket.
    // the move method is then only moving on x axis, because running is false
    public void freezeOnRacket(Racket racket) {
        relocate(racket.getLayoutX() + racket.getShape().getWidth() / 2, racket.getLayoutY() - radius - 2);
    }

    //tests whether the ball hits left, right or top and then reflects.
    //if the ball hits the bottom of the field the ball is off the field.
    public void hitBorder(Pane canvas) {
        Bounds bounds = canvas.getBoundsInLocal();

        //If the ball reaches the left or right border make the step negative
        if (getLayoutX() <= (bounds.getMinX() + getRadius()) || getLayoutX() >= (bounds.getMaxX() - getRadius())) breakout.bx = -breakout.bx;

        //If the ball reaches the top border make the step negative
        if ((getLayoutY() <= (bounds.getMinY() + getRadius()))) breakout.by = -breakout.by;

        //If the ball reaches the bottom border, it is off the field
        if (getLayoutY() >= (bounds.getMaxY() - getRadius())) ballOff = true;
    }

    //TODO: Should be implemented in Breakout
    public void hitRacket(Breakout breakout, Racket racket) {
        if (racket != null) {
            if (racket.intersects(racket.sceneToLocal(localToScene(getBoundsInLocal())))) {
                bounce.play();

                //hits left side of the racket, then bounce to left
                if ((getLayoutX() >= racket.getLayoutX() && getLayoutX() <= (racket.getLayoutX() + racket.getShape().getWidth() / 2)) && (getLayoutY() + radius >= racket.getLayoutY() && getLayoutY() + radius < racket.getLayoutY() + (racket.getLayoutY() + racket.getShape().getHeight()))) {
                    if (breakout.bx < 0) {
                        breakout.bx *= -1;
                    }
                }

                //hits right side of the racket, then bounce to right
                if ((getLayoutX() >= (racket.getLayoutX() + racket.getShape().getWidth() / 2) && getLayoutX() <= (racket.getLayoutX() + racket.getShape().getWidth())) && (getLayoutY() + radius >= racket.getLayoutY() && getLayoutY() + radius < racket.getLayoutY() + (racket.getLayoutY() + racket.getShape().getHeight()))) {
                    if (breakout.bx > 0) {
                        breakout.bx *= -1;
                    }
                }

                //reverse shot upwards
                breakout.by = -breakout.by;
            }
        }
    }
}

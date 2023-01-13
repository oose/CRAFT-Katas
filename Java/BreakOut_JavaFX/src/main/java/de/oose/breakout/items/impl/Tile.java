package de.oose.breakout.items.impl;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tile extends ImageView {

    Logger log = LogManager.getLogger(getClass());

    private double x;
    private double y;
    public int points = 0;
    private Token token;
    private Map<Integer, Token> tokens = null;

    public Tile(Image image, double x, double y, int points) {
        super(image);
        this.x = x;
        this.y = y;
        this.points = points;
        this.token  = createToken();
        if (token != null) token.setPosition(x,y);
    }

    private Token createToken() {
        if (tokens == null) {
            createTokenList();
        }
        Token token = (new Random().nextInt(10) == 0)
                ? tokens.get(new Random().nextInt(9))
                : null;
        if (token != null) log.info("create token: " + token);

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

    public void draw() {
        this.relocate(x, y);
    }

    public void remove() {
        this.setImage(null);
    }

    public static Tile getTile(Image image, int points, int xPos, int yPos) {
        return new Tile(image, xPos, yPos, points);
    }

    public Token getToken() {
        return token;
    }
}

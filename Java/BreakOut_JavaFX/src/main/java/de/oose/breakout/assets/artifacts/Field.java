package de.oose.breakout.assets.artifacts;

import de.oose.breakout.Breakout;
import de.oose.breakout.items.impl.Tile;
import de.oose.breakout.util.ResourceLocator;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int rowSpace = 15;
    private final int colSpace = 30;
    private final int rowOffset = 10;

    private List<Tile> tiles = null;
    private int rows = 0;
    private int cols = 0;

    public List<Image> images = new ArrayList<>();
    private List<String> colors = new ArrayList<String>();

    public Field(Breakout breakout, int rows, int cols) {
        this.tiles = new ArrayList<Tile>(rows * cols);
        this.rows = rows;
        this.cols = cols;

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
                tiles.add(Tile.getTile(images.get(row), points, rowOffset + col * colSpace, 50 + row * rowSpace));
            }
        }
        return this;
    }

    private void loadTileImages() {
        for (String color : colors) {
            images.add(ResourceLocator.getImage("Tile_" + color + ".jpg"));
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}

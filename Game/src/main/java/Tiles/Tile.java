package Tiles;

import Entities.Drawable;
import Entities.Player;
import GUI.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements Drawable {
    private int posx, posy, dimension;
    private BufferedImage tileImage;

    public Tile(int posx, int posy, int dimension, BufferedImage tileImage) {
        this.posx = posx;
        this.posy = posy;
        this.dimension = dimension;
        this.tileImage = tileImage;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        g.drawImage(tileImage, posx ,posy, dimension, dimension, null);
    }
}

package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileInteractable extends Tile{
    private Rectangle interactArea;

    public TileInteractable(int posx, int posy, int dimension, BufferedImage tileImage) {
        super(posx, posy, dimension, tileImage);
        interactArea=new Rectangle(posx,posy,dimension,dimension);
    }
    public boolean intersect(Rectangle rec){
        return interactArea.intersects(rec);
    }
}

package Tiles;

import Entities.Collider;

import java.awt.image.BufferedImage;

public class TileCollider extends Tile {
    private Collider collider;

    public Collider getCollider() {
        return collider;
    }

    public TileCollider(int posx, int posy, int dimension, BufferedImage tileImage) {
        super(posx, posy, dimension, tileImage);
        collider=new Collider(posx,posy,dimension,dimension);
    }
}

package Entities;

import java.awt.*;
import java.io.Serializable;

public class Collider implements Serializable {
    private Rectangle hitBox;
    private int posx,posy;

    public Collider(int posx, int posy,int width,int height) {
        this.posx = posx;
        this.posy = posy;
        hitBox= new Rectangle(posx,posy,width,height);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void updateHitBox(int posx, int posy){
        hitBox.x=posx;
        hitBox.y=posy;
    }
}

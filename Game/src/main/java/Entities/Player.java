package Entities;

import Controllers.KeyHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player implements Drawable {
    private String name;
    private BufferedImage up1,down1,left1,right1,up2,down2,left2,right2;
    private KeyHandler playerKeyHandler;
    private int switchAnimation;
    private int speed=10;
    private int posX, posY;

    public Player(String name,KeyHandler pkh) {
        this.name = name;
        switchAnimation=0;
        playerKeyHandler= pkh;
        posX=100;
        posY=100;
        setImages();
    }

    private void setImages(){
        try{
        up1= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/up1.png"));
        up2= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/up2.png"));
        down1= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/down1.png"));
        down2= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/down2.png"));
        left1= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/left1.png"));
        left2= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/left2.png"));
        right1= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/right1.png"));
        right2= ImageIO.read(getClass().getResourceAsStream("/Sprites/PlayerSprites/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        if(playerKeyHandler.isUp()){
            posY-=speed;
        }else if(playerKeyHandler.isRight()){
            posX+=speed;
        }else if(playerKeyHandler.isDown()){
            posY+=speed;
        }else if(playerKeyHandler.isLeft()){
            posX-=speed;
        }

    }

    @Override
    public void draw(Graphics2D g) {
        BufferedImage paintMe=up1;
        if(playerKeyHandler.isUp()){
            if(switchAnimation==0){
                paintMe=up1;
            }else{
                paintMe=up2;
            }
            switchAnimation=(switchAnimation+1)%2;
        }else if(playerKeyHandler.isDown()){
            if(switchAnimation==0){
                paintMe=down1;
            }else{
                paintMe=down2;
            }
            switchAnimation=(switchAnimation+1)%2;
        }else if(playerKeyHandler.isLeft()){
            if(switchAnimation==0){
                paintMe=left1;
            }else{
                paintMe=left2;
            }
            switchAnimation=(switchAnimation+1)%2;
        }else if(playerKeyHandler.isRight()){
            if(switchAnimation==0){
                paintMe=right1;
            }else{
                paintMe=right2;
            }
            switchAnimation=(switchAnimation+1)%2;
        }

        g.drawImage(paintMe,posX,posY, 48,48,null);


    }
}

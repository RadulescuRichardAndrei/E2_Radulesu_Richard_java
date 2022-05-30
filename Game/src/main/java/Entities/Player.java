package Entities;

import Controllers.KeyHandler;
import Display.HitBoxCheck;
import Entities.Monsters.Monster;
import Entities.Monsters.MonstersBufferedImages;
import FileSystem.FileManager;
import GUI.BattlePanel;
import GUI.GamePanel;
import Generation.Generate;
import Launcher.GameLauncher;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Player implements Drawable, Serializable {
    private String name;
    private transient BufferedImage up1,down1,left1,right1,up2,down2,left2,right2;
    private transient KeyHandler playerKeyHandler;
    private int switchAnimation;
    private int speed=10,size;
    private int posX, posY;
    private transient Collider collider;
    private int partyCount;
    private int[] partyMonster;
    private List<Monster> playerMonsters;


    public Player(String name,KeyHandler pkh,int positionx,int positiony) {
        playerMonsters=new LinkedList<>();
        partyMonster=new int[]{-1,-1,-1,-1};
        partyCount=0;
        this.name = name;
        switchAnimation=0;
        playerKeyHandler= pkh;
        posX=positionx;
        posY=positiony;
        size=64;
        collider=new Collider(posX,posY,size,size);
        setImages();
        playerMonsters.add(Generate.generateMonster(5,true));
        partyCount=1;
        partyMonster[0]=0;
    }
    public void setPlayerNotSerialiazable(KeyHandler kh){
        MonstersBufferedImages mbi= new MonstersBufferedImages();
        for(Monster m:playerMonsters){
            m.setMonsterSprite(mbi.getSpriteAtIndex(1));
        }

        playerKeyHandler=kh;
        collider=new Collider(posX,posY,size,size);
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
    private void updatePosition(){
        if(playerKeyHandler.isSave())
            FileManager.save(this);
        if(playerKeyHandler.isHeal()){
            for(Monster m:playerMonsters){
                m.resetHealth();
            }
        }
        int tempX=posX,tempY=posY;
        if(playerKeyHandler.isUp()){
            tempY-=speed;
        }else if(playerKeyHandler.isRight()){
            tempX+=speed;
        }else if(playerKeyHandler.isDown()){
            tempY+=speed;
        }else if(playerKeyHandler.isLeft()){
            tempX-=speed;
        }


        Rectangle rec=new Rectangle(tempX,tempY,size,size);
        if(HitBoxCheck.CanMoveHere(rec)){
            posX=tempX;
            posY=tempY;
        }
        if(HitBoxCheck.IsInteractHere(rec) && Math.random()<=0.01 && GamePanel.alive && GamePanel.seconds>10){

            GameLauncher.setPos(posX,posY);
            List<Monster> playerTeam= new LinkedList<>();
            for(int i=0;i<partyCount;i++)
                if(partyMonster[i]!=-1){
                    playerTeam.add(playerMonsters.get(partyMonster[i]));
                }
            int level= playerTeam.stream().map(x->x.getLevel()).reduce(0,Integer::sum);
            FileManager.save(this);
            GamePanel.alive=false;
            GamePanel.seconds=0;
            GameLauncher.switchPanel(playerTeam, Generate.generateEncounter(playerTeam.size(),level/3,false));

        }

    }
    public void removeFromParty(int index){
        partyMonster[index]=-1;
    }
    public void addToParty(int partyPos,int playerMonsterIndex){
        partyMonster[partyPos]=playerMonsterIndex;
    }
    public void addToParty(Monster monster){
        playerMonsters.add(monster);
        if(partyCount<3){
            partyMonster[partyCount]=playerMonsters.indexOf(monster);
            partyCount++;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public void update() {
        updatePosition();
        collider.updateHitBox(posX,posY);
    }

    @Override
    public void draw(Graphics2D g) {
        BufferedImage paintMe=up1;
        if(playerKeyHandler.isUp()){
            if(switchAnimation<=5){
                paintMe=up1;

            }else{
                paintMe=up2;
            }
            switchAnimation=(switchAnimation+1)%10;
        }else if(playerKeyHandler.isDown()){
            if(switchAnimation<=5){
                paintMe=down1;
            }else{
                paintMe=down2;
            }
            switchAnimation=(switchAnimation+1)%10;
        }else if(playerKeyHandler.isLeft()){
            if(switchAnimation<=5){
                paintMe=left1;
            }else{
                paintMe=left2;
            }
            switchAnimation=(switchAnimation+1)%10;
        }else if(playerKeyHandler.isRight()){
            if(switchAnimation<=5){
                paintMe=right1;
            }else{
                paintMe=right2;
            }
            switchAnimation=(switchAnimation+1)%10;
        }

        g.drawImage(paintMe,posX,posY, size,size,null);
    }

}

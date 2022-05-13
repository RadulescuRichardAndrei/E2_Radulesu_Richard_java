package GUI;

import Controllers.KeyHandler;
import Entities.Drawable;
import Entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, Drawable {
    final private int orgTitleSize=16;
    final private int scale=3;
    final private int tileSize=orgTitleSize*scale;
    final private int maxScreenCol=20;
    final private int maxScreenRow=16;
    final private int screenWidth=maxScreenCol*tileSize;
    final private int screenHeight=maxScreenRow*tileSize;
    final private int FPS=60;
    private Thread gameThread;
    private Player gamePlayer;
    private KeyHandler playerKeyHandler= new KeyHandler();

    public int getTileSize() {
        return tileSize;
    }

    public GamePanel(){
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(playerKeyHandler);
        this.setFocusable(true);
    }
    public void startGame(){
        gamePlayer=new Player("Ion",playerKeyHandler);
        gameThread=new Thread(this);
        this.addKeyListener(playerKeyHandler);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval=1000000000 /FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currTime;
        long timer=0;
        int drawCount=0;

        while (gameThread != null){
            currTime=System.nanoTime();
            delta+=(currTime - lastTime) / drawInterval;
            timer+=(currTime-lastTime);
            lastTime=currTime;


            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer>=1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount=0;
                timer=0;
            }

        }

    }
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        draw(g2);
    }

    @Override
    public void update() {
        gamePlayer.update();
    }

    @Override
    public void draw(Graphics2D g) {
        gamePlayer.draw(g);
    }
}

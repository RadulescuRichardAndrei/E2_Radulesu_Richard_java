package GUI;

import Controllers.KeyHandler;
import Entities.Drawable;
import Entities.Player;
import Display.Render;
import FileSystem.FileManager;
import Launcher.GameLauncher;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, Drawable {
    final private static int orgTitleSize=16;
    final private static int scale=3;
    final private static int tileSize=orgTitleSize*scale;
    final private static int maxScreenCol=20;
    final private static int maxScreenRow=16;
    final public static int screenWidth=maxScreenCol*tileSize;
    final public static int screenHeight=maxScreenRow*tileSize;
    final private int FPS=60;
    private Thread gameThread;
    private Player gamePlayer;
    private KeyHandler playerKeyHandler;
    private Render render;
    public static boolean alive;
    public static int seconds=0;


    public int getTileSize() {
        return tileSize;
    }

    public GamePanel(){
        playerKeyHandler= new KeyHandler();
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(playerKeyHandler);
        this.setFocusable(true);
    }
    public void pauseGame(){
        while (GameLauncher.isFighting) {
            gameThread.interrupt();
        }
    }
    public void startGame(){
        alive=true;
        //gamePlayer=new Player("Ion",playerKeyHandler,GameLauncher.lastXPos, GameLauncher.lastYPos);
        //FileManager.save(gamePlayer);
        gamePlayer=FileManager.read();
        gamePlayer.setPlayerNotSerialiazable(playerKeyHandler);
        render=new Render(gamePlayer);
        gameThread=new Thread(this);
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

        while (gameThread != null && alive){
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
                //System.out.println("FPS: " + drawCount);
                drawCount=0;
                timer=0;
                seconds++;
                System.out.println(seconds);
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
        render.update();
    }

    @Override
    public void draw(Graphics2D g) {

       render.draw(g);
    }


}

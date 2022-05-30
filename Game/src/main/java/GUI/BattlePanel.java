package GUI;

import Battles.MonsterBattleManager;
import Entities.Drawable;
import Entities.Monsters.Monster;
import Entities.Monsters.MonsterAbility;
import Launcher.GameLauncher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BattlePanel extends JPanel implements Drawable,Runnable {

    private MonsterBattleManager monsterBattleManager;
    private List<Monster> playerMonsters,enemyMonster;
    private int monsterSpriteSize = (GamePanel.screenWidth - 300) / 5;
    private List<BattleButton> buttons;
    private BattleMouseListener bMouseListener;
    private List<BattleEnemyPosition> enemyPositionList;
    private Thread battleThread;
    final private int FPS=60;
    private int buttonPressed=0;
    private Font myfont=new Font("Arial", Font.BOLD,20);
    private ButtonBufferedImages bbi=new ButtonBufferedImages();
    private static boolean alive=true;

    public BattlePanel(List<Monster> playerMonsters, List<Monster> enemyMonster) {
        setPreferredSize(new Dimension(GamePanel.screenWidth, GamePanel.screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        monsterBattleManager = new MonsterBattleManager(playerMonsters, enemyMonster);
        this.playerMonsters=playerMonsters;
        this.enemyMonster=enemyMonster;
        bMouseListener=new BattleMouseListener();
        this.addMouseListener(bMouseListener);
        buttons=new LinkedList<>();
        enemyPositionList=new LinkedList<>();
        InitEnemyPosition();
        buttons.add(new BattleButton(null,1,bbi.getSpriteAtIndex(0)));
        buttons.add(new BattleButton(null,2,bbi.getSpriteAtIndex(0)));
        buttons.add(new BattleButton(null,3,bbi.getSpriteAtIndex(0)));
        buttons.add(new BattleButton(null,4,bbi.getSpriteAtIndex(0)));
        buttons.add(new BattleButton("Run",GamePanel.screenWidth /2+100,GamePanel.screenHeight-195,5,bbi.getSpriteAtIndex(1)));



    }
    public void startBattle(){
        battleThread=new Thread(this);
        battleThread.start();
    }

    public void setButtonContent(List<MonsterAbility> abilities){
        int i=0;
        for (MonsterAbility ma: abilities){
            buttons.get(i).setContent(ma.getAbilityStats());
            i++;
        }
    }
    public void clearButtonContent(){
        for (BattleButton bt:buttons){
            bt.setContent(null);
        }
    }



    @Override
    public void update() {
        if(monsterBattleManager.getMonsterOnCurrentTurn().isStatus()){
            setButtonContent(monsterBattleManager.getMonsterOnCurrentTurn().getMonsterAbilities());
        }else{
            clearButtonContent();
            Monster lowestHealthMonster=playerMonsters.get(0);
            for(Monster m:playerMonsters)
                if(lowestHealthMonster.getHealth()>m.getHealth())
                    lowestHealthMonster=m;
            int abilityPosition=monsterBattleManager.getMonsterOnCurrentTurn().mostDamageStrategy(lowestHealthMonster);
            if(monsterBattleManager.applyDamageToEnemy(lowestHealthMonster,abilityPosition)){
                removeFromEnemies(lowestHealthMonster);
                monsterBattleManager.removeFromOrder(lowestHealthMonster);
                playerMonsters.remove(lowestHealthMonster);
            }

            monsterBattleManager.nextTurn();



        }
        for(BattleButton bt:buttons)
            if(bt.isClickedOn(bMouseListener.getClickPosX(),bMouseListener.getClickPosY())){
                buttonPressed=bt.getButtonNb();
            }

        int position=0;
        for(BattleEnemyPosition bep:enemyPositionList){
             if(bep.isClickedOn(bMouseListener.getClickPosX(), bMouseListener.getClickPosY()) && buttonPressed!=0 && buttonPressed != 5 && !bep.isDead()){{
                if(monsterBattleManager.applyDamageToEnemy(enemyMonster.get(position),buttonPressed-1)){
                    removeFromEnemies(monsterBattleManager.getMonsterOnCurrentTurn());
                    monsterBattleManager.getMonsterOnCurrentTurn().levelUp(enemyMonster.get(position));
                    bep.setDead(true);
                    monsterBattleManager.removeFromOrder(enemyMonster.get(position));
                    enemyMonster.remove(position);
                    enemyPositionList.remove(bep);
                    enemyPositionList.clear();
                    InitEnemyPosition();

                }
                 monsterBattleManager.nextTurn();
             }
                 buttonPressed=0;
            }
             position++;
        }

        if((buttonPressed==5 || enemyMonster.size()==0 || playerMonsters.size()==0) && alive){
            alive=false;
            GameLauncher.switchPanel();
        }
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        draw(g2);
    }

    @Override
    public void draw(Graphics2D g) {

        drawMiddle(g, this.playerMonsters, GamePanel.screenHeight - 250 - monsterSpriteSize);
        drawMiddle(g, this.enemyMonster, 150);
        drawElements(g);

    }

    private void drawElements(Graphics2D g) {

        g.setColor(Color.GRAY);
        g.fillRect(0, GamePanel.screenHeight - 250, GamePanel.screenWidth, 250);
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(10));
        g.drawRect(0, GamePanel.screenHeight - 245, GamePanel.screenWidth, 250);
        monsterBattleManager.drawTurn(g);
        for (BattleButton bt:buttons)
            bt.draw(g);

    }
    private void removeFromPlayer(Monster m){
        for(Monster m2: playerMonsters)
            if (m.equals(m2)){
                playerMonsters.remove(m);
                break;
            }
    }

    private void removeFromEnemies(Monster m){
        for(Monster m2: enemyMonster)
            if (m.equals(m2)){
                enemyMonster.remove(m);
                break;
            }

    }
    private void InitEnemyPosition(){
        int middle = GamePanel.screenWidth / 2;
        int y=150;
        switch (enemyMonster.size()){
            case 1:
                enemyPositionList.add(new BattleEnemyPosition(middle - monsterSpriteSize / 2,y,monsterSpriteSize));
                break;
            case 2:
                enemyPositionList.add(new BattleEnemyPosition(middle - 25 - monsterSpriteSize,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle + 25,y,monsterSpriteSize));
                break;
            case 3:
                enemyPositionList.add(new BattleEnemyPosition(middle - monsterSpriteSize / 2,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle - monsterSpriteSize / 2 - 50 - monsterSpriteSize,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle + monsterSpriteSize / 2 + 50,y,monsterSpriteSize));
                break;
            case 4:
                enemyPositionList.add(new BattleEnemyPosition(middle - 25 - monsterSpriteSize,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle + 25,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle + 75 + monsterSpriteSize,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle - 25 - monsterSpriteSize,y,monsterSpriteSize));
                break;
            case 5:
                enemyPositionList.add(new BattleEnemyPosition(middle - monsterSpriteSize / 2,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle - monsterSpriteSize / 2 - 50 - monsterSpriteSize,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(middle + monsterSpriteSize / 2 + 50,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(50,y,monsterSpriteSize));
                enemyPositionList.add(new BattleEnemyPosition(GamePanel.screenWidth - 50 - monsterSpriteSize,y,monsterSpriteSize));
                break;
        }
    }

    public void drawMiddle(Graphics2D g, List<Monster> monsters, int y) {
        int middle = GamePanel.screenWidth / 2;
        String content, type;
        g.setColor(Color.PINK);
        g.setFont( myfont);
        switch (monsters.size()) {
            case 1:
                g.drawImage(monsters.get(0).getMonsterSprite(), middle - monsterSpriteSize / 2, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(0).getHealth());
                type= monsters.get(0).getTypings();
                content.concat(type);
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - monsterSpriteSize / 2,y);
                break;
            case 2:
                g.drawImage(monsters.get(0).getMonsterSprite(), middle - 25 - monsterSpriteSize, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(0).getHealth());
                type= monsters.get(0).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - 25 - monsterSpriteSize,y);

                g.drawImage(monsters.get(1).getMonsterSprite(), middle + 25, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(1).getHealth());
                type= monsters.get(1).getTypings();

                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle + 25,y);
                break;
            case 3:
                g.drawImage(monsters.get(0).getMonsterSprite(), middle - monsterSpriteSize / 2, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(0).getHealth());
                type= monsters.get(0).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - monsterSpriteSize / 2,y);

                g.drawImage(monsters.get(1).getMonsterSprite(), middle - monsterSpriteSize / 2 - 50 - monsterSpriteSize, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(1).getHealth());
                type= monsters.get(1).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - monsterSpriteSize / 2 - 50 - monsterSpriteSize,y);

                g.drawImage(monsters.get(2).getMonsterSprite(), middle + monsterSpriteSize / 2 + 50, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(2).getHealth());
                type= monsters.get(2).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle + monsterSpriteSize / 2 + 50,y);
                break;
            case 4:
                g.drawImage(monsters.get(0).getMonsterSprite(), middle - 25 - monsterSpriteSize, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(0).getHealth());
                type= monsters.get(0).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - 25 - monsterSpriteSize,y);

                g.drawImage(monsters.get(1).getMonsterSprite(), middle + 25, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(1).getHealth());
                type= monsters.get(1).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle + 25,y);

                g.drawImage(monsters.get(2).getMonsterSprite(), middle + 75 + monsterSpriteSize, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(2).getHealth());
                type= monsters.get(2).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle + 75 + monsterSpriteSize,y);

                g.drawImage(monsters.get(3).getMonsterSprite(), middle - 75 - 2 * monsterSpriteSize, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(3).getHealth());
                type= monsters.get(3).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - 75 - 2 * monsterSpriteSize,y);
                break;
            case 5:
                g.drawImage(monsters.get(0).getMonsterSprite(), middle - monsterSpriteSize / 2, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(0).getHealth());
                type= monsters.get(0).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - monsterSpriteSize / 2,y);

                g.drawImage(monsters.get(1).getMonsterSprite(), middle - monsterSpriteSize / 2 - 50 - monsterSpriteSize, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(1).getHealth());
                type= monsters.get(1).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle - monsterSpriteSize / 2 - 50 - monsterSpriteSize,y);

                g.drawImage(monsters.get(2).getMonsterSprite(), middle + monsterSpriteSize / 2 + 50, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(2).getHealth());
                type= monsters.get(2).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),middle + monsterSpriteSize / 2 + 50,y);

                g.drawImage(monsters.get(3).getMonsterSprite(), 50, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(3).getHealth());
                type= monsters.get(3).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),50,y);

                g.drawImage(monsters.get(4).getMonsterSprite(), GamePanel.screenWidth - 50 - monsterSpriteSize, y, monsterSpriteSize, monsterSpriteSize, null);
                content=Integer.toString((int)monsters.get(4).getHealth());
                type= monsters.get(4).getTypings();
                g.drawChars(content.concat(type).toCharArray(),0,content.length()+type.length(),GamePanel.screenWidth - 50 - monsterSpriteSize,y);
                break;
        }

    }

    @Override
    public void run() {
        double drawInterval=1000000000 /FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currTime;
        long timer=0;
        int drawCount=0;


        while (battleThread != null){
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
                drawCount=0;
                timer=0;
            }

        }
    }
}

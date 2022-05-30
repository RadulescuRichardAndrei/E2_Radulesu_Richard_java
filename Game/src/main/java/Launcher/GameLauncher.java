package Launcher;

import Battles.DamageTable;
import Entities.Monsters.Monster;
import GUI.BattlePanel;
import GUI.GamePanel;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class GameLauncher {
    public static JFrame window;
    public static int lastXPos=100,lastYPos=100;
    public static DamageTable dt=new DamageTable();
    public static boolean isFighting;

    public static void main(String[] args){
        window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MonsterTowerDungeon");
        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);
        gamePanel.startGame();
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
    public static void switchPanel(){
        window.getContentPane().removeAll();
        window.getContentPane().invalidate();
        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);
        gamePanel.startGame();
        window.getContentPane().revalidate();
        window.getContentPane().repaint();
        window.setVisible(true);
    }


    public static void switchPanel(List<Monster> playerTeam, List<Monster> generateEncounter) {
        window.getContentPane().removeAll();
        window.getContentPane().invalidate();
        BattlePanel bp= new BattlePanel(playerTeam,generateEncounter);
        bp.startBattle();
        window.add(bp);
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    public static void setPos(int xpos,int ypos){
        lastXPos=xpos;
        lastYPos=ypos;
    }
}

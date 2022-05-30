package Testing;

import Battles.DamageTable;
import Entities.Monsters.Monster;
import Entities.Monsters.MonsterAbility;
import Entities.Monsters.MonsterType;
import Entities.Monsters.MonstersBufferedImages;
import GUI.BattlePanel;
import GUI.GamePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args){
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MonsterTowerDungeon");

        List<Monster> monsterList=new ArrayList<>();
        MonstersBufferedImages mbi=new MonstersBufferedImages();
        DamageTable dT=new DamageTable();
        List<MonsterAbility> ma=new LinkedList<>();
        ma.add(new MonsterAbility(MonsterType.WATER,40,10));
        ma.add(new MonsterAbility(MonsterType.THUNDER,50,10));
        ma.add(new MonsterAbility(MonsterType.WATER,20,20));
        ma.add(new MonsterAbility(MonsterType.THUNDER,10,30));
        monsterList.add(new Monster("serpelii",mbi.getSpriteAtIndex(1),new LinkedList<>(Arrays.asList(MonsterType.WATER,MonsterType.THUNDER)),10,ma,true));
        monsterList.add(new Monster("serpelii",mbi.getSpriteAtIndex(1),new LinkedList<>(Arrays.asList(MonsterType.WATER,MonsterType.THUNDER)),10,ma,true));
        monsterList.add(new Monster("serpelii",mbi.getSpriteAtIndex(1),new LinkedList<>(Arrays.asList(MonsterType.WATER,MonsterType.THUNDER)),10,ma,true));
        monsterList.add(new Monster("serpelii",mbi.getSpriteAtIndex(1),new LinkedList<>(Arrays.asList(MonsterType.WATER,MonsterType.THUNDER)),10,ma,true));

        List<Monster> enemyList=new ArrayList<>();
        enemyList.add(new Monster("serpelii2",mbi.getSpriteAtIndex(0),new LinkedList<>(Arrays.asList(MonsterType.WATER,MonsterType.THUNDER)),1,ma,false));
        enemyList.add(new Monster("serpelii2",mbi.getSpriteAtIndex(0),new LinkedList<>(Arrays.asList(MonsterType.WATER,MonsterType.THUNDER)),1,ma,false));

        BattlePanel gamePanel=new BattlePanel(monsterList,enemyList);
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

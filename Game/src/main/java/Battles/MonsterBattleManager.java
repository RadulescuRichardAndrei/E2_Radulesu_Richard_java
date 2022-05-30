package Battles;

import Entities.Monsters.Monster;
import Entities.Monsters.MonsterType;
import GUI.GamePanel;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonsterBattleManager {

    private int turn,size;
    private Map<Integer, Monster> orderMonsterFight;
    private int monsterSpriteSize = (GamePanel.screenWidth - 300) / 5;

    public MonsterBattleManager(List<Monster> playerMonsters, List<Monster> enemyMonsters) {
        turn=0;
        orderMonsterFight=new HashMap<>();
        size=playerMonsters.size()+enemyMonsters.size();
        orderMonster(playerMonsters,enemyMonsters);
    }

    public Map<Integer, Monster> getOrderMonsterFight() {
        return orderMonsterFight;
    }
    public Monster getMonsterOnCurrentTurn(){

        while (!orderMonsterFight.containsKey(turn)){
            turn++;
            if(turn>= size)
                turn=0;
        }

        return orderMonsterFight.get(turn);
    }
    public void nextTurn(){
        turn++;
       while (!orderMonsterFight.containsKey(turn)){
           turn++;
           if(turn>= size)
               turn=0;
       }
    }
    public int getPosition(Monster m){

       for (int i=0;i<size;i++) {
           if (orderMonsterFight.containsKey(i) && m.equals(orderMonsterFight.get(i)))
               return i;
       }

       return -1;
    }
    public void defeated(int position){
        orderMonsterFight.remove(position);
    }
    public boolean applyDamageToEnemy(Monster m, int abilityNB){
        int position=getPosition(m);

        double dmg= getMonsterOnCurrentTurn().chooseAbilityBattle(orderMonsterFight.get(position),abilityNB);
        if(dmg>= orderMonsterFight.get(position).getHealth()){
            //enemy is dead
            orderMonsterFight.get(position).setHealth(-1);
            return true;
        }
        else{
            orderMonsterFight.get(position).decreaseHealthBy(dmg);
            return false;
        }
    }
    public void removeFromOrder(Monster m){
        int position=getPosition(m);
        orderMonsterFight.remove(position);
        if(turn>=orderMonsterFight.size()) turn=0;
    }


    private void orderMonster(List<Monster> playerMonsters, List<Monster> enemyMonsters){
        playerMonsters.sort(Comparator.comparing(Monster::getMonsterSpeed));
        enemyMonsters.sort(Comparator.comparing(Monster::getMonsterSpeed));

        int i,j,order;
        for ( i = 0, j = 0, order=0; i < playerMonsters.size() && j < enemyMonsters.size(); ) {
            if(playerMonsters.get(i).getMonsterSpeed()>enemyMonsters.get(j).getMonsterSpeed()){
                orderMonsterFight.put(order,playerMonsters.get(i));
                order++;
                i++;
            }
            else {
                orderMonsterFight.put(order,enemyMonsters.get(j));
                order++;
                j++;
            }
        }
        while (i<playerMonsters.size()){
            orderMonsterFight.put(order,playerMonsters.get(i));
            order++;
            i++;
        }
        while (j<enemyMonsters.size()){
            orderMonsterFight.put(order,enemyMonsters.get(j));
            order++;
            j++;
        }

    }

    public void drawTurn(Graphics2D g){
        if(!getMonsterOnCurrentTurn().isStatus())
            g.setColor(Color.RED);
        else
            g.setColor(Color.GREEN);

        int xpos= GamePanel.screenWidth/2-monsterSpriteSize/2;
        int size=monsterSpriteSize/4*3;
        g.drawImage(getMonsterOnCurrentTurn().getMonsterSprite(),xpos,20,size,size,null);
        g.drawRect(xpos,20,size,size);

    }



}

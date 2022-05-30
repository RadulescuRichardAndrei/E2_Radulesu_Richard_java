package Entities.Monsters;

import java.io.Serializable;

public class MonsterBaseStat implements Serializable {
    private int health, defence, attack, speed;

    public MonsterBaseStat(String monsterName) {
        //read from xmlFile stats base on monsterName
        health=10;
        defence=1;
        attack=1;
        speed=10;
    }

    public int getHealth() {
        return health;
    }

    public int getDefence() {
        return defence;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }
}

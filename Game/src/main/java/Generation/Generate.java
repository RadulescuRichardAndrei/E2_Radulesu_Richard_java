package Generation;

import Entities.Monsters.Monster;
import Entities.Monsters.MonsterAbility;
import Entities.Monsters.MonsterType;
import Entities.Monsters.MonstersBufferedImages;
import com.github.javafaker.Faker;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Generate {

    public static List<Monster> generateEncounter(int nb,int level,boolean status){

        List<Monster> encounter=new LinkedList<>();
        for (int i=0;i<nb;i++)
            encounter.add(generateMonster(level,status));

        return encounter;
    }

    public static Monster generateMonster(int level,boolean status) {
        Faker fk = new Faker();
        MonstersBufferedImages mbi = new MonstersBufferedImages();
        List<MonsterType> mt = new LinkedList<>();

        mt.add(MonsterType.values()[(int) (Math.random() * MonsterType.values().length)]);
        if(Math.random()<=0.5){
            MonsterType m=MonsterType.values()[(int) (Math.random() * MonsterType.values().length)];
            while (mt.contains(m)){
                m=MonsterType.values()[(int) (Math.random() * MonsterType.values().length)];
            }
            mt.add(m);
        }

        int mLevel=level + (int)(Math.random()*(level-1));

        List<MonsterAbility> ma=new LinkedList<>();
        ma.add(generateMonsterAbility(mt));
        for (int i=1;i<4;i++)
            if(Math.random()<=0.8)
                ma.add(generateMonsterAbility(mt));

        return new Monster(fk.pokemon().name().toString(), mbi.getSpriteAtIndex(0), mt,mLevel,ma,status);
    }

    public static MonsterAbility generateMonsterAbility(List<MonsterType> mt){
        int index= (int) (Math.random() * mt.size());
        int power= 10 + (int)(Math.random()*50);
        int uses=10+ (int)(Math.random()*20);
        return new MonsterAbility(mt.get(index),power,uses);
    }

}

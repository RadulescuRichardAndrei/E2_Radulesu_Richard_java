package Entities.Monsters;

import Launcher.GameLauncher;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class Monster implements Serializable {
    private int ID;
    private String nickname;
    private String name;
    private transient BufferedImage monsterSprite;
    private List<MonsterType> monsterType;
    private MonsterBaseStat baseStats;
    private int level;
    private boolean status;
    private double Health;
    private double monsterExp;

    private List<MonsterAbility> monsterAbilities;


    public Monster(String name, BufferedImage monsterSprite, List<MonsterType> monsterType, int level, List<MonsterAbility> monsterAbilities,boolean status) {
        this.name = name;
        this.monsterSprite = monsterSprite;
        this.monsterType = monsterType;
        this.level = level;
        this.monsterAbilities = monsterAbilities;
        this.monsterExp = 0;
        baseStats = new MonsterBaseStat("serpe");
        resetHealth();
        ID = (int) (Math.random() * 10000 + name.length());
        this.status=status;
    }

    public void setMonsterSprite(BufferedImage bi) {

        this.monsterSprite =bi;
    }

    public boolean isDead() {
        if (Health <= 0)
            return true;
        return false;
    }

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        Health = health;
    }

    public String getTypings() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        monsterType.stream().forEach(monsterType1 -> {
            sb.append(monsterType1.toString().substring(0, 2));
            sb.append(" ");
        });
        return sb.toString();
    }

    public Monster(List<MonsterType> monsterType, int level, List<MonsterAbility> monsterAbilities) {
        this.monsterType = monsterType;
        this.level = level;
        this.monsterAbilities = monsterAbilities;
    }

    public void decreaseHealthBy(double damage) {
        Health -= damage;
    }

    public BufferedImage getMonsterSprite() {
        return monsterSprite;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void resetHealth() {
        Health = 100 + level * baseStats.getHealth();
    }

    public int getLevel() {
        return level;
    }

    public List<MonsterAbility> getMonsterAbilities() {
        return monsterAbilities;
    }

    public List<MonsterType> getMonsterType() {
        return monsterType;
    }


    public MonsterBaseStat getBaseStats() {
        return baseStats;
    }

    public int getMonsterSpeed() {
        return level * baseStats.getSpeed();
    }

    public double chooseAbilityBattle(Monster enemy, int abilityNumber) {
        if (monsterAbilities.get(abilityNumber).getUses() <= 0)
            return 0;
        double multiplier = enemy.getMonsterType().stream().mapToDouble(monsterType ->
                GameLauncher.dt.getDamageMultiplierTable().get(monsterAbilities.get(abilityNumber).getAbilityType())
                .get(monsterType)).reduce(1, (a, b) -> a * b);
        monsterAbilities.get(abilityNumber).decrementUses();
        return monsterAbilities.get(abilityNumber).getAbilityPower() * multiplier
                + level * baseStats.getAttack() - enemy.getLevel() * enemy.getBaseStats().getDefence();
    }


    public double expNeeded() {
        double expression = (Math.log(level + 1) + Math.log(level)) / 2;
        return 100 * expression;
    }

    public void levelUp(Monster enemy) {
        if (level >= 60 && level <= 86) {
            double exp = 50 * ((double)(60 / level) + (60 / (double)(level + 1))) / 2;
        }


        double exp1 = (((double)(level - 1) / level + (double) level / (level + 1)) / 2) * 100;
        double exp2 = (((double)(20 / level) + (20 / (double)(level + 1))) / 2) * 100;

        if (exp1 > exp2)
            monsterExp += exp2;
        else
            monsterExp += exp1;

        while (monsterExp >= expNeeded()) {
            monsterExp -= expNeeded();
            level++;
        }
    }

    public int mostDamageStrategy(Monster enemy) {

        double maximumDamage = 0;
        int bestAbilityPos = 0;
        for (int i = 0; i < getMonsterAbilities().size(); i++) {
            double dmg = chooseAbilityBattle(enemy, i);
            if (dmg > maximumDamage) {
                maximumDamage = dmg;
                bestAbilityPos = i;
            }
        }
        return bestAbilityPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return ID == monster.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "ID=" + ID +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package Battles;

import Entities.Monsters.MonsterType;

import java.util.HashMap;
import java.util.Map;

public class DamageTable {
    private Map<MonsterType, Map<MonsterType, Double>> DamageMultiplierTable;

    public DamageTable() {
        SetDamageMultiplierTable();
    }

    public Map<MonsterType, Map<MonsterType, Double>> getDamageMultiplierTable() {
        return DamageMultiplierTable;
    }

    private void SetDamageMultiplierTable() {
        DamageMultiplierTable = new HashMap<>();

        DamageMultiplierTable.put(MonsterType.WATER,
                damageTo(MonsterType.FIRE, MonsterType.EARTH, MonsterType.WATER,
                        MonsterType.GRASS, MonsterType.THUNDER, MonsterType.LIGHT, MonsterType.DARK));

        DamageMultiplierTable.put(MonsterType.FIRE,
                damageTo(MonsterType.GRASS, MonsterType.THUNDER, MonsterType.FIRE,
                        MonsterType.EARTH, MonsterType.WATER, MonsterType.LIGHT, MonsterType.DARK));

        DamageMultiplierTable.put(MonsterType.GRASS,
                damageTo(MonsterType.EARTH, MonsterType.WATER, MonsterType.GRASS,
                        MonsterType.THUNDER, MonsterType.FIRE, MonsterType.LIGHT, MonsterType.DARK));

        DamageMultiplierTable.put(MonsterType.EARTH,
                damageTo(MonsterType.THUNDER, MonsterType.FIRE, MonsterType.EARTH,
                        MonsterType.WATER, MonsterType.GRASS, MonsterType.LIGHT, MonsterType.DARK));

        DamageMultiplierTable.put(MonsterType.THUNDER,
                damageTo(MonsterType.WATER, MonsterType.GRASS, MonsterType.THUNDER,
                        MonsterType.FIRE, MonsterType.EARTH, MonsterType.LIGHT, MonsterType.DARK));

        DamageMultiplierTable.put(MonsterType.LIGHT,
                damageToLightOrDark(MonsterType.WATER, MonsterType.FIRE, MonsterType.GRASS,
                        MonsterType.THUNDER, MonsterType.EARTH, MonsterType.LIGHT, MonsterType.DARK));
        DamageMultiplierTable.put(MonsterType.DARK,
                damageToLightOrDark(MonsterType.WATER, MonsterType.FIRE, MonsterType.GRASS,
                        MonsterType.THUNDER, MonsterType.EARTH, MonsterType.DARK, MonsterType.LIGHT));
    }

    private Map<MonsterType, Double> damageToLightOrDark(MonsterType m1, MonsterType m2, MonsterType m3, MonsterType m4, MonsterType m5, MonsterType m6, MonsterType m7) {
        Map<MonsterType, Double> helper = new HashMap<>();
        helper.put(m1, 1.0);
        helper.put(m2, 1.0);
        helper.put(m3, 1.0);
        helper.put(m4, 1.);
        helper.put(m5, 1.0);
        helper.put(m6, 1.0);
        helper.put(m7, 4.0);
        return helper;
    }

    private Map<MonsterType, Double> damageTo(MonsterType m1, MonsterType m2, MonsterType m3, MonsterType m4, MonsterType m5, MonsterType m6, MonsterType m7) {
        Map<MonsterType, Double> helper = new HashMap<>();
        helper.put(m1, 4.0);
        helper.put(m2, 2.0);
        helper.put(m3, 1.0);
        helper.put(m4, 0.5);
        helper.put(m5, 0.25);
        helper.put(m6, 1.0);
        helper.put(m7, 1.0);
        return helper;
    }
}

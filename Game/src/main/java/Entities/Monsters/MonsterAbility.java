package Entities.Monsters;

import java.io.Serializable;

public class MonsterAbility implements Serializable {
    private String abilityName;
    private Enum abilityType;
    private int abilityPower;
    private int totalUses;
    private int uses;


    public MonsterAbility(Enum abilityType, int abilityPower, int totalUses) {
        abilityName="test";
        this.abilityType = abilityType;
        this.abilityPower = abilityPower;
        this.totalUses = totalUses;
        uses=totalUses;
    }
    public String getAbilityStats(){
        return abilityName + ": " + abilityPower + "PW " +  abilityType.toString().substring(0,2) + " (" + uses + "/" + totalUses +")";
    }


    public String getAbilityName() {
        return abilityName;
    }

    public void resetUses(){uses=totalUses;}
    public void decrementUses(){this.uses--;}
    public Enum getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(Enum abilityType) {
        this.abilityType = abilityType;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        this.abilityPower = abilityPower;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }
}

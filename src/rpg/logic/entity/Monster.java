package rpg.logic.entity;

import rpg.logic.enums.MonsterID;
import rpg.logic.item.LootItem;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
    private final MonsterID ID;

    private String name;
    private int maxDamage;
    private int rewardExperiencePoints;
    private int rewardGold;

    private List<LootItem> lootTable;

    public Monster(MonsterID id, String name, int maxDamage, int rewardExperiencePoints, int rewardGold, int currentHitPoints, int maxHitPoints){
        super(currentHitPoints, maxHitPoints);
        ID = id;
        this.name = name;
        this.maxDamage = maxDamage;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;

        lootTable = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxDamage(int maxDamage){
        this.maxDamage = maxDamage;
    }

    public void setRewardExperiencePoints(int rewardExperiencePoints){
        this.rewardExperiencePoints = rewardExperiencePoints;
    }

    public void setRewardGold(int rewardGold){
        this.rewardGold = rewardGold;
    }

    public void setLootTable(List<LootItem> lootTable) {
        this.lootTable = lootTable;
    }

    public MonsterID getID(){
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getMaxDamage(){
        return maxDamage;
    }

    public int getRewardExperiencePoints(){
        return rewardExperiencePoints;
    }

    public int getRewardGold(){
        return rewardGold;
    }

    public List<LootItem> getLootTable(){
        return lootTable;
    }
}

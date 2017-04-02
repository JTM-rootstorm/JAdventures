package logic.entity;

import logic.item.LootItem;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
    private final int ID;

    private String name;
    private int maxDamage;
    private int rewardExperiencePoints;
    private int rewardGold;

    private List<LootItem> lootTable;

    public Monster(int id, String name, int maxDamage, int rewardExperiencePoints,
                   int rewardGold, int currentHitPoints, int maxHitPoints){
        super(currentHitPoints, maxHitPoints);
        ID = id;
        this.name = name;
        this.maxDamage = maxDamage;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;

        lootTable = new ArrayList<>();
    }

    public int getID(){
        return ID;
    }

    public String getName() {
        return name;
    }

    int getMaxDamage(){
        return maxDamage;
    }

    int getRewardExperiencePoints(){
        return rewardExperiencePoints;
    }

    int getRewardGold(){
        return rewardGold;
    }

    public List<LootItem> getLootTable(){
        return lootTable;
    }
}

package rpg.logic.entity;

import rpg.logic.Location;
import rpg.logic.item.InventoryItem;
import rpg.logic.quests.PlayerQuest;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    private int gold;
    private int expPoints;
    private int level;

    private List<InventoryItem> inventory;
    private List<PlayerQuest> quests;

    private Location currentLocation;

    public Player(int currentHitPoints, int maxHitPoints, int gold, int expPoints, int level){
        super(currentHitPoints, maxHitPoints);
        this.gold = gold;
        this.expPoints = expPoints;
        this.level = level;
        inventory = new ArrayList<>();
        quests = new ArrayList<>();
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public void setExpPoints(int expPoints){
        this.expPoints = expPoints;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public void setQuests(List<PlayerQuest> quests) {
        this.quests = quests;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void addExperiencePoints(int xp){
        expPoints += xp;
    }

    public void addGold(int gold){
        this.gold += gold;
    }

    public int getGold(){
        return gold;
    }

    public int getExpPoints(){
        return expPoints;
    }

    public int getLevel(){
        return level;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public List<PlayerQuest> getQuests() {
        return quests;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}

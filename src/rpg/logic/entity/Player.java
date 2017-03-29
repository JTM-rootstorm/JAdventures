package rpg.logic.entity;

import rpg.logic.Location;
import rpg.logic.item.InventoryItem;
import rpg.logic.item.Item;
import rpg.logic.quests.PlayerQuest;
import rpg.logic.quests.Quest;
import rpg.logic.quests.QuestCompletionItem;

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

    public Boolean hasThisQuest(Quest quest){
        for(PlayerQuest pq : quests){
            if(pq.getDetails().getID() == quest.getID()){
                return true;
            }
        }

        return false;
    }

    public Boolean completedThisQuest(Quest quest){
        for(PlayerQuest pq : quests){
            if(pq.getDetails().getID() == quest.getID()){
                return pq.isCompleted();
            }
        }

        return false;
    }

    public Boolean hasAllQuestCompletionItems(Quest quest){
        for(QuestCompletionItem qci : quest.getQuestCompletionItems()){
            boolean foundItemInPlayerInv = false;

            for(InventoryItem item : inventory){
                if(item.getDetails().getID() == qci.getDetails().getID()){
                    foundItemInPlayerInv = true;

                    if(item.getQuantity() < qci.getQuantity()){
                        return false;
                    }
                }
            }

            if(!foundItemInPlayerInv){
                return false;
            }
        }

        return true;
    }

    public void removeQuestCompletionItems(Quest quest){
        for(QuestCompletionItem qci : quest.getQuestCompletionItems()){
            for(InventoryItem item : inventory){
                if(item.getDetails().getID() == qci.getDetails().getID()){
                    item.setQuantity(item.getQuantity() - qci.getQuantity());
                    break;
                }
            }
        }
    }

    public void addItemToInventory(Item itemToAdd){
        for(InventoryItem item : inventory){
            if(item.getDetails().getID() == itemToAdd.getID()){
                item.incrementQuantity();
                return;
            }
        }

        inventory.add(new InventoryItem(itemToAdd, 1));
    }

    public void markQuestAsCompleted(Quest quest){
        for(PlayerQuest pq : quests){
            if(pq.getDetails().getID() == quest.getID()){
                pq.setCompleted(true);
                return;
            }
        }
    }
}

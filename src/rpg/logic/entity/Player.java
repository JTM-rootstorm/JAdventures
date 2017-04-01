package rpg.logic.entity;

import rpg.logic.Location;
import rpg.logic.World;
import rpg.logic.item.InventoryItem;
import rpg.logic.item.Item;
import rpg.logic.item.weapon.Weapon;
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

    private Location currentLocation = null;

    private Weapon currentWeapon = null;

    public Player(int currentHitPoints, int maxHitPoints, int gold, int expPoints, int level){
        super(currentHitPoints, maxHitPoints);
        setGold(gold);
        setExpPoints(expPoints);
        setLevel(level);
        inventory = new ArrayList<>();
        quests = new ArrayList<>();
    }

    public void setGold(int gold){
        this.gold = gold;
        World.sendObserverNotification("plr_gold");
    }

    public void fireInitMessages(){
        World.sendObserverNotification("plr_gold");
        World.sendObserverNotification("plr_exp");
        World.sendObserverNotification("plr_curhp");
        World.sendObserverNotification("plr_lvl");
    }

    public void setExpPoints(int expPoints){
        this.expPoints = expPoints;
        World.sendObserverNotification("plr_exp");
    }

    @Override
    public void setCurrentHitPoints(int hitPoints){
        super.setCurrentHitPoints(hitPoints);
        World.sendObserverNotification("plr_curhp");
    }

    public void setLevel(int level){
        this.level = level;
        World.sendObserverNotification("plr_lvl");
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

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void addExperiencePoints(int xp){
        expPoints += xp;
    }

    public void addGold(int gold){
        setGold(this.gold + gold);
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

    public Weapon getCurrentWeapon() {
        return currentWeapon;
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

        World.sendObserverNotification("plr_inv_additem");
    }

    public void addItemToInventory(Item itemToAdd){
        for(InventoryItem item : inventory){
            if(item.getDetails().getID() == itemToAdd.getID()){
                item.incrementQuantity();
                return;
            }
        }

        inventory.add(new InventoryItem(itemToAdd, 1));

        World.sendObserverNotification("plr_inv_additem");
    }

    public void addQuestToList(PlayerQuest quest){
        quests.add(quest);
        World.sendObserverNotification("plr_quest");
    }

    public void markQuestAsCompleted(Quest quest){
        for(PlayerQuest pq : quests){
            if(pq.getDetails().getID() == quest.getID()){
                pq.setCompleted();
                return;
            }
        }
    }
}

package rpg.logic;

import rpg.logic.entity.Monster;
import rpg.logic.enums.LocationID;
import rpg.logic.item.Item;
import rpg.logic.quests.Quest;

public class Location {
    private final LocationID ID;
    private transient String name;
    private transient String description;

    private transient Item itemRequiredToEnter;
    private transient Quest questAvailableHere;
    private transient Monster monsterLivingHere;

    private transient Location locationToNorth = null;
    private transient Location locationToEast = null;
    private transient Location locationToSouth = null;
    private transient Location locationToWest = null;

    public Location(LocationID id, String name, String description, Item itemRequiredToEnter, Quest questAvailableHere,
                    Monster monsterLivingHere){
        ID = id;
        this.name = name;
        this.description = description;
        this.itemRequiredToEnter = itemRequiredToEnter;
        this.questAvailableHere = questAvailableHere;
        this.monsterLivingHere = monsterLivingHere;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setItemRequiredToEnter(Item itemRequiredToEnter) {
        this.itemRequiredToEnter = itemRequiredToEnter;
    }

    public void setQuestAvailableHere(Quest questAvailableHere) {
        this.questAvailableHere = questAvailableHere;
    }

    public void setMonsterLivingHere(Monster monsterLivingHere) {
        this.monsterLivingHere = monsterLivingHere;
    }

    void setLocationToNorth(Location locationToNorth) {
        this.locationToNorth = locationToNorth;
    }

    void setLocationToEast(Location locationToEast) {
        this.locationToEast = locationToEast;
    }

    void setLocationToSouth(Location locationToSouth) {
        this.locationToSouth = locationToSouth;
    }

    void setLocationToWest(Location locationToWest) {
        this.locationToWest = locationToWest;
    }

    LocationID getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Item getItemRequiredToEnter() {
        return itemRequiredToEnter;
    }

    public Quest getQuestAvailableHere() {
        return questAvailableHere;
    }

    public Monster getMonsterLivingHere() {
        return monsterLivingHere;
    }

    public Location getLocationToNorth() {
        return locationToNorth;
    }

    public Location getLocationToEast() {
        return locationToEast;
    }

    public Location getLocationToSouth() {
        return locationToSouth;
    }

    public Location getLocationToWest() {
        return locationToWest;
    }

    @Override
    public String toString(){
        return getName();
    }
}

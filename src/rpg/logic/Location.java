package rpg.logic;

import com.sun.istack.internal.Nullable;
import rpg.logic.entity.Monster;
import rpg.logic.enums.LocationID;
import rpg.logic.item.Item;
import rpg.logic.quests.Quest;

public class Location {
    private final LocationID ID;
    private String name;
    private String description;

    private Item itemRequiredToEnter;
    private Quest questAvailableHere;
    private Monster monsterLivingHere;

    private Location locationToNorth;
    private Location locationToEast;
    private Location locationToSouth;
    private Location locationToWest;

    public Location(LocationID id, String name, String description, Item itemRequiredToEnter, Quest questAvailableHere,
                    Monster monsterLivingHere){
        ID = id;
        this.name = name;
        this.description = description;
        this.itemRequiredToEnter = itemRequiredToEnter;
        this.questAvailableHere = questAvailableHere;
        this.monsterLivingHere = monsterLivingHere;
    }

    /*public void setDescription(String description){
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

    public void setLocationToNorth(Location locationToNorth) {
        this.locationToNorth = locationToNorth;
    }*/

    public void setLocationToEast(Location locationToEast) {
        this.locationToEast = locationToEast;
    }

    public void setLocationToSouth(Location locationToSouth) {
        this.locationToSouth = locationToSouth;
    }

    public void setLocationToWest(Location locationToWest) {
        this.locationToWest = locationToWest;
    }

    public LocationID getID(){
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

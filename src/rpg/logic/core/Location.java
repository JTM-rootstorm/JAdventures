package rpg.logic.core;

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
                    Monster monsterLivingHere) {
        ID = id;
        this.name = name;
        this.description = description;
        this.itemRequiredToEnter = itemRequiredToEnter;
        this.questAvailableHere = questAvailableHere;
        this.monsterLivingHere = monsterLivingHere;
    }

    LocationID getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
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

    void setLocationToNorth(Location locationToNorth) {
        this.locationToNorth = locationToNorth;
    }

    public Location getLocationToEast() {
        return locationToEast;
    }

    void setLocationToEast(Location locationToEast) {
        this.locationToEast = locationToEast;
    }

    public Location getLocationToSouth() {
        return locationToSouth;
    }

    void setLocationToSouth(Location locationToSouth) {
        this.locationToSouth = locationToSouth;
    }

    public Location getLocationToWest() {
        return locationToWest;
    }

    void setLocationToWest(Location locationToWest) {
        this.locationToWest = locationToWest;
    }

    @Override
    public String toString() {
        return getName();
    }
}
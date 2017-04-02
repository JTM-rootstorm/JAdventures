package logic.core;

import com.google.gson.annotations.Expose;
import logic.entity.Monster;
import logic.item.Item;
import logic.quests.Quest;

public class Location {
    @Expose private int ID;
    @Expose private String name;
    @Expose private String description;

    @Expose private Integer itemRequiredToEnter;
    @Expose private Integer questAvailableHere;
    @Expose private Integer monsterLivingHere;

    @Expose private Integer locationToNorth = null;
    @Expose private Integer locationToEast = null;
    @Expose private Integer locationToSouth = null;
    @Expose private Integer locationToWest = null;

    public Location(int id, String name, String description, Integer itemRequiredToEnter, Integer questAvailableHere,
                    Integer monsterLivingHere) {
        ID = id;
        this.name = name;
        this.description = description;
        this.itemRequiredToEnter = itemRequiredToEnter;
        this.questAvailableHere = questAvailableHere;
        this.monsterLivingHere = monsterLivingHere;
    }

    public Location(int id, String name, String description, Integer itemRequiredToEnter, Integer questAvailableHere,
                    Integer monsterLivingHere, Integer locationToNorth, Integer locationToEast,
                    Integer locationToSouth, Integer locationToWest) {
        ID = id;
        this.name = name;
        this.description = description;
        this.itemRequiredToEnter = itemRequiredToEnter;
        this.questAvailableHere = questAvailableHere;
        this.monsterLivingHere = monsterLivingHere;
        this.locationToNorth = locationToNorth;
        this.locationToEast = locationToEast;
        this.locationToSouth = locationToSouth;
        this.locationToWest = locationToWest;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Item getItemRequiredToEnter() {
        return World.ItemByID(itemRequiredToEnter);
    }

    public Quest getQuestAvailableHere() {
        return World.QuestByID(questAvailableHere);
    }

    public Monster getMonsterLivingHere() {
        return World.MonsterByID(monsterLivingHere);
    }

    public Location getLocationToNorth() {
        return World.LocationByID(locationToNorth);
    }

    void setLocationToNorth(int locationToNorth) {
        this.locationToNorth = locationToNorth;
    }

    public Location getLocationToEast() {
        return World.LocationByID(locationToEast);
    }

    void setLocationToEast(int locationToEast) {
        this.locationToEast = locationToEast;
    }

    public Location getLocationToSouth() {
        return World.LocationByID(locationToSouth);
    }

    void setLocationToSouth(int locationToSouth) {
        this.locationToSouth = locationToSouth;
    }

    public Location getLocationToWest() {
        return World.LocationByID(locationToWest);
    }

    void setLocationToWest(int locationToWest) {
        this.locationToWest = locationToWest;
    }

    @Override
    public String toString() {
        return getName();
    }
}

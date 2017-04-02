package logic.builders;

import logic.core.Location;

public class LocationBuilder {
    private int ID;
    private String name;
    private String description;

    private Integer itemRequiredToEnter = null;
    private Integer questAvailableHere = null;
    private Integer monsterLivingHere = null;

    private Integer locationToNorth = null;
    private Integer locationToEast = null;
    private Integer locationToSouth = null;
    private Integer locationToWest = null;

    public LocationBuilder() {
    }

    public Location buildLocation() {
        return new Location(ID, name, description, itemRequiredToEnter, questAvailableHere, monsterLivingHere);
    }

    public LocationBuilder id(int id) {
        ID = id;
        return this;
    }

    public LocationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public LocationBuilder description(String description) {
        this.description = description;
        return this;
    }

    public LocationBuilder itemRequiredToEnter(Integer item) {
        itemRequiredToEnter = item;
        return this;
    }

    public LocationBuilder questAvailableHere(Integer quest) {
        questAvailableHere = quest;
        return this;
    }

    public LocationBuilder monsterLivingHere(Integer monster) {
        monsterLivingHere = monster;
        return this;
    }

    public Location buildLocationFromJSONInut() {
        return new Location(ID, name, description,itemRequiredToEnter,
                questAvailableHere, monsterLivingHere, locationToNorth, locationToEast, locationToSouth, locationToWest);
    }
}

package logic.builders;

import logic.core.Location;
import logic.entity.Monster;
import logic.enums.LocationID;
import logic.item.Item;
import logic.quests.Quest;

public class LocationBuilder {
    private LocationID ID;
    private String name;
    private String description;

    private Item itemRequiredToEnter = null;
    private Quest questAvailableHere = null;
    private Monster monsterLivingHere = null;

    public LocationBuilder() {
    }

    public Location buildLocation() {
        return new Location(ID, name, description, itemRequiredToEnter, questAvailableHere, monsterLivingHere);
    }

    public LocationBuilder id(LocationID id) {
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

    public LocationBuilder itemRequiredToEnter(Item item) {
        itemRequiredToEnter = item;
        return this;
    }

    public LocationBuilder questAvailableHere(Quest quest) {
        questAvailableHere = quest;
        return this;
    }

    public LocationBuilder monsterLivingHere(Monster monster) {
        monsterLivingHere = monster;
        return this;
    }
}

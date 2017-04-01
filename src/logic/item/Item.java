package logic.item;

import logic.enums.ItemID;

public class Item {
    private final ItemID ID;
    private final String name;
    private final String namePlural;

    public Item(ItemID id, String name, String namePlural){
        ID = id;
        this.name = name;
        this.namePlural = namePlural;
    }

    public ItemID getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getNamePlural(){
        return namePlural;
    }

    @Override
    public String toString(){
        return name;
    }
}

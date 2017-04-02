package logic.item;

import com.google.gson.annotations.Expose;

public class Item {
    @Expose private int ID;
    @Expose private String name;
    @Expose private String namePlural;

    public Item(int id, String name, String namePlural){
        ID = id;
        this.name = name;
        this.namePlural = namePlural;
    }

    public int getID(){
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

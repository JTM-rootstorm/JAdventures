package logic.item;

public class Item {
    private final int ID;
    private final String name;
    private final String namePlural;

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

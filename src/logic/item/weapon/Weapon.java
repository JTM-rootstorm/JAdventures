package logic.item.weapon;

import logic.item.Item;

public class Weapon extends Item {
    private int minDamage;
    private int maxDamage;

    public Weapon(int id, String name, String namePlural, int minDamage, int maxDamage){
        super(id, name, namePlural);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getMinDamage(){
        return minDamage;
    }

    public int getMaxDamage(){
        return maxDamage;
    }
}

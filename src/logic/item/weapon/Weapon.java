package logic.item.weapon;

import com.google.gson.annotations.Expose;
import logic.item.Item;

public class Weapon extends Item {
    @Expose private int minDamage;
    @Expose private int maxDamage;

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

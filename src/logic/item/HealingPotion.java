package logic.item;

import com.google.gson.annotations.Expose;

public class HealingPotion extends Item {

    @Expose private int amountToHeal;

    public HealingPotion(int id, String name, String namePlural, int amountToHeal){
        super(id, name, namePlural);

        this.amountToHeal = amountToHeal;
    }

    public int getAmountToHeal(){
        return amountToHeal;
    }
}

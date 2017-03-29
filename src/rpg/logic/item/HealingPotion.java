package rpg.logic.item;

import rpg.logic.enums.ItemID;

public class HealingPotion extends Item {

    private final int amountToHeal;

    public HealingPotion(ItemID id, String name, String namePlural, int amountToHeal){
        super(id, name, namePlural);

        this.amountToHeal = amountToHeal;
    }

    public int getAmountToHeal(){
        return amountToHeal;
    }
}

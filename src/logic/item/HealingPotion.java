package logic.item;

public class HealingPotion extends Item {

    private final int amountToHeal;

    public HealingPotion(int id, String name, String namePlural, int amountToHeal){
        super(id, name, namePlural);

        this.amountToHeal = amountToHeal;
    }

    public int getAmountToHeal(){
        return amountToHeal;
    }
}

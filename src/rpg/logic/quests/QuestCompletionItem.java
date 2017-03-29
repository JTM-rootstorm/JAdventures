package rpg.logic.quests;

import rpg.logic.item.Item;

public class QuestCompletionItem {
    private Item details;
    private int quantity;

    public QuestCompletionItem(Item item, int quantity){
        details = item;
        this.quantity = quantity;
    }

    public Item getDetails(){
        return details;
    }

    public int getQuantity(){
        return quantity;
    }
}

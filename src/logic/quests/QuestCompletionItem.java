package logic.quests;

import com.google.gson.annotations.Expose;
import logic.core.World;
import logic.item.Item;

public class QuestCompletionItem {
    @Expose private int details;
    @Expose private int quantity;

    public QuestCompletionItem(int item, int quantity){
        details = item;
        this.quantity = quantity;
    }

    public Item getDetails(){
        return World.ItemByID(details);
    }

    public int getQuantity(){
        return quantity;
    }
}

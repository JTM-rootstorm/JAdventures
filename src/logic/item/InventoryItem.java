package logic.item;

import logic.core.World;

public class InventoryItem {
    private int details;
    private int quantity;

    public InventoryItem(int item, int quantity){
        details = item;
        this.quantity = quantity;
    }

    public Item getDetails() {
        return World.ItemByID(details);
    }

    public void setDetails(int details) {
        this.details = details;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        World.sendObserverNotification("plr_inv_additem");
    }

    public void incrementQuantity(){
        setQuantity(quantity + 1);
    }
}

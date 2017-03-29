package rpg.logic.item;

public class InventoryItem {
    private Item details;
    private int quantity;

    public InventoryItem(Item item, int quantity){
        details = item;
        this.quantity = quantity;
    }

    public Item getDetails() {
        return details;
    }

    public void setDetails(Item details) {
        this.details = details;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity(){
        quantity++;
    }
}

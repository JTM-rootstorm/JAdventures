package rpg.logic.item;

public class LootItem {
    private Item details;
    private int dropPercentage;
    private boolean isDefaultItem;

    public LootItem(Item item, int dropPercentage, boolean isDefaultItem){
        details = item;
        this.dropPercentage = dropPercentage;
        this.isDefaultItem = isDefaultItem;
    }

    public Item getDetails() {
        return details;
    }

    public void setDetails(Item details) {
        this.details = details;
    }

    public int getDropPercentage() {
        return dropPercentage;
    }

    public void setDropPercentage(int dropPercentage) {
        this.dropPercentage = dropPercentage;
    }

    public boolean isDefaultItem() {
        return isDefaultItem;
    }

    public void setDefaultItem(boolean defaultItem) {
        isDefaultItem = defaultItem;
    }
}

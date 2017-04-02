package logic.item;

import com.google.gson.annotations.Expose;
import logic.core.World;

public class LootItem {
    @Expose private Integer details;
    @Expose private int dropPercentage;
    @Expose private boolean isDefaultItem;

    public LootItem(Integer item, int dropPercentage, boolean isDefaultItem){
        details = item;
        this.dropPercentage = dropPercentage;
        this.isDefaultItem = isDefaultItem;
    }

    public Item getDetails() {
        return World.ItemByID(details);
    }

    public void setDetails(Integer details) {
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

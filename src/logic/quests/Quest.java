package logic.quests;

import com.google.gson.annotations.Expose;
import logic.core.World;
import logic.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    @Expose private final int ID;
    @Expose private String name;
    @Expose private String description;

    @Expose private int rewardExperiencePoints;
    @Expose private int rewardGold;
    @Expose private List<QuestCompletionItem> questCompletionItems;
    @Expose private int rewardItem;

    public Quest(int id, String name, String description, int rewardExperiencePoints, int rewardGold){
        ID = id;
        this.name = name;
        this.description = description;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;
        questCompletionItems = new ArrayList<>();
    }

    public Quest(int id, String name, String description, int rewardExperiencePoints, int rewardGold,
                 List<QuestCompletionItem> questCompletionItems, int rewardItem){
        ID = id;
        this.name = name;
        this.description = description;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;
        this.questCompletionItems = questCompletionItems;
        this.rewardItem = rewardItem;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRewardExperiencePoints(){
        return rewardExperiencePoints;
    }

    public int getRewardGold(){
        return rewardGold;
    }

    public Item getRewardItem(){
        return World.ItemByID(rewardItem);
    }

    public void setRewardItem(int item){
        rewardItem = item;
    }

    public List<QuestCompletionItem> getQuestCompletionItems(){
        return questCompletionItems;
    }
}

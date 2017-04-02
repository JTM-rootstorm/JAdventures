package logic.quests;

import logic.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    private final int ID;
    private String name;
    private String description;

    private int rewardExperiencePoints;
    private int rewardGold;
    private List<QuestCompletionItem> questCompletionItems;
    private Item rewardItem;

    public Quest(int id, String name, String description, int rewardExperiencePoints, int rewardGold){
        ID = id;
        this.name = name;
        this.description = description;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;
        questCompletionItems = new ArrayList<>();
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
        return rewardItem;
    }

    public void setRewardItem(Item item){
        rewardItem = item;
    }

    public List<QuestCompletionItem> getQuestCompletionItems(){
        return questCompletionItems;
    }
}

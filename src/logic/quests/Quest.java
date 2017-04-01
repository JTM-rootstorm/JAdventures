package logic.quests;

import logic.enums.QuestID;
import logic.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    private final QuestID ID;
    private String name;
    private String description;

    private int rewardExperiencePoints;
    private int rewardGold;
    private List<QuestCompletionItem> questCompletionItems;
    private Item rewardItem;

    public Quest(QuestID id, String name, String description, int rewardExperiencePoints, int rewardGold){
        ID = id;
        this.name = name;
        this.description = description;
        this.rewardExperiencePoints = rewardExperiencePoints;
        this.rewardGold = rewardGold;
        questCompletionItems = new ArrayList<>();
    }

    public QuestID getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRewardExperiencePoints(){
        return rewardExperiencePoints;
    }

    public void setRewardExperiencePoints(int rewardExperiencePoints) {
        this.rewardExperiencePoints = rewardExperiencePoints;
    }

    public int getRewardGold(){
        return rewardGold;
    }

    public void setRewardGold(int rewardGold) {
        this.rewardGold = rewardGold;
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

    public void setQuestCompletionItems(List<QuestCompletionItem> questCompletionItems) {
        this.questCompletionItems = questCompletionItems;
    }
}

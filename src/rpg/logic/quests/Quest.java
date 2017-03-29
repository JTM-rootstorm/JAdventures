package rpg.logic.quests;

import rpg.logic.enums.QuestID;
import rpg.logic.item.Item;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRewardExperiencePoints(int rewardExperiencePoints) {
        this.rewardExperiencePoints = rewardExperiencePoints;
    }

    public void setRewardGold(int rewardGold) {
        this.rewardGold = rewardGold;
    }

    public void setQuestCompletionItems(List<QuestCompletionItem> questCompletionItems) {
        this.questCompletionItems = questCompletionItems;
    }

    public void setRewardItem(Item item){
        rewardItem = item;
    }

    public QuestID getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
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

    public List<QuestCompletionItem> getQuestCompletionItems(){
        return questCompletionItems;
    }
}

package logic.builders;

import logic.quests.Quest;
import logic.quests.QuestCompletionItem;

import java.util.ArrayList;
import java.util.List;

public class QuestBuilder {
    private int ID;
    private String name;
    private String description;

    private int rewardExperiencePoints = 0;
    private int rewardGold = 0;
    private List<QuestCompletionItem> questCompletionItems = null;
    private Integer rewardItem = null;

    public QuestBuilder() { }

    public Quest buildQuest(){
        return new Quest(ID, name, description, rewardExperiencePoints, rewardGold);
    }

    public QuestBuilder id(Integer id){
        ID = id;
        return this;
    }

    public QuestBuilder name(String name){
        this.name = name;
        return this;
    }

    public QuestBuilder description(String description){
        this.description = description;
        return this;
    }

    public QuestBuilder rewardXP(Integer rewardExperiencePoints){
        this.rewardExperiencePoints = rewardExperiencePoints;
        return this;
    }

    public QuestBuilder rewardGold(Integer rewardGold){
        this.rewardGold = rewardGold;
        return this;
    }

    public Quest buildQuestFromJSON(){
        return new Quest(ID, name, description, rewardExperiencePoints, rewardGold,
                makeQuestListFromJSONInput(questCompletionItems), rewardItem);
    }

    private List<QuestCompletionItem> makeQuestListFromJSONInput(List<QuestCompletionItem> questCompletionItems){
        List<QuestCompletionItem> resultList = new ArrayList<>();

        for(QuestCompletionItem qci : questCompletionItems){
            resultList.add(new QuestCompletionItem(qci.getDetails().getID(), qci.getQuantity()));
        }

        return resultList;
    }
}

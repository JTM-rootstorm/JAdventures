package rpg.logic.builders.quest;

import rpg.logic.enums.QuestID;
import rpg.logic.quests.Quest;
import rpg.logic.quests.QuestCompletionItem;

public class QuestBuilder {
    private QuestID ID;
    private String name;
    private String description;

    private int rewardExperiencePoints = 0;
    private int rewardGold = 0;

    public QuestBuilder() { }

    public Quest buildQuest(){
        return new Quest(ID, name, description, rewardExperiencePoints, rewardGold);
    }

    public QuestBuilder id(QuestID id){
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

    public QuestBuilder rewardXP(int rewardExperiencePoints){
        this.rewardExperiencePoints = rewardExperiencePoints;
        return this;
    }

    public QuestBuilder rewardGold(int rewardGold){
        this.rewardGold = rewardGold;
        return this;
    }
}

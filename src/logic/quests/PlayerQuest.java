package logic.quests;

import logic.core.World;

public class PlayerQuest {
    private int details;
    private boolean isCompleted;

    public PlayerQuest(int quest){
        details = quest;
        isCompleted = false;
    }

    public Quest getDetails() {
        return World.QuestByID(details);
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        isCompleted = true;
        World.sendObserverNotification("plr_quest");
    }
}

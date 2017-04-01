package rpg.logic.quests;

import rpg.logic.World;

public class PlayerQuest {
    private Quest details;
    private boolean isCompleted;

    public PlayerQuest(Quest quest){
        details = quest;
        isCompleted = false;
    }

    public Quest getDetails() {
        return details;
    }

    public void setDetails(Quest details) {
        this.details = details;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        isCompleted = true;
        World.sendObserverNotification("plr_quest");
    }
}

package rpg.logic.quests;

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

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

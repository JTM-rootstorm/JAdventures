package logic.enums;

public enum QuestID {
    CLEAR_ALCHEMIST_GARDEN(0), CLEAR_FARMERS_FIELD(1);

    private int value;

    QuestID(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

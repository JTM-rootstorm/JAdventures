package logic.enums;

public enum MonsterID {
    RAT(0), SNAKE(1), GIANT_SPIDER(2);

    private int value;

    MonsterID(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

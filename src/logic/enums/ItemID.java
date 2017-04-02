package logic.enums;

public enum ItemID {
    RUSTY_SWORD(0), RAT_TAIL(1), PIECE_OF_FUR(2), SNAKE_FANG(3), SNAKE_SKIN(4), CLUB(5), HEALING_POTION(6),
    SPIDER_FANG(7), SPIDER_SILK(8), ADVENTURER_PASS(9);

    private int value;

    ItemID(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

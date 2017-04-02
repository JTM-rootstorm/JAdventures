package logic.enums;

public enum LocationID {
    HOME(0), TOWN_SQUARE(1), GUARD_POST(2), ALCHEMIST_HUT(3), ALCHEMISTS_GARDEN(4), FARMHOUSE(5), FARM_FIELD(6),
    BRIDGE(7), SPIDER_FIELD(8);

    private int value;

    LocationID(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

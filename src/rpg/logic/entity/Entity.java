package rpg.logic.entity;

public class Entity {
    private int currentHitPoints;
    private int maxHitPoints;

    public Entity(int currentHitPoints, int maxHitPoints){
        this.currentHitPoints = currentHitPoints;
        this.maxHitPoints = maxHitPoints;
    }

    public void setCurrentHitPoints(int hitPoints){
        currentHitPoints = hitPoints;
    }

    public void setMaxHitPoints(int hitPoints){
        maxHitPoints = hitPoints;
    }

    public int getCurrentHitPoints(){
        return currentHitPoints;
    }

    public int getMaxHitPoints(){
        return maxHitPoints;
    }
}

package logic.entity;

import com.google.gson.annotations.Expose;

public class Entity{
    @Expose private int currentHitPoints;
    @Expose private int maxHitPoints;

    Entity(int currentHitPoints, int maxHitPoints){
        this.currentHitPoints = currentHitPoints;
        this.maxHitPoints = maxHitPoints;
    }

    public int getCurrentHitPoints(){
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int hitPoints){
        currentHitPoints = hitPoints;
    }

    int getMaxHitPoints(){
        return maxHitPoints;
    }

    public void setMaxHitPoints(int hitPoints){
        maxHitPoints = hitPoints;
    }
}

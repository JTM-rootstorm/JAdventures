/*
   JAdventure - A Java-based RPG
   Copyright (C) 2017  TehGuy

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package logic.entity;

import com.google.gson.annotations.Expose;
import logic.core.Location;
import logic.core.World;
import logic.item.HealingPotion;
import logic.item.InventoryItem;
import logic.item.Item;
import logic.item.Weapon;
import logic.quests.PlayerQuest;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    @Expose private int gold;
    @Expose private int expPoints;
    @Expose private int level;

    @Expose private List<InventoryItem> inventory;
    @Expose private List<PlayerQuest> quests;

    @Expose private Integer currentLocation;

    private Weapon currentWeapon = null;

    public Player(int currentHitPoints, int maxHitPoints, List<Integer> stats){
        super(currentHitPoints, maxHitPoints, stats);
        gold = 0;
        expPoints = 0;
        level = 1;
        inventory = new ArrayList<>();
        quests = new ArrayList<>();
    }

    @Override
    public void setCurrentHitPoints(int hitPoints) {
        super.setCurrentHitPoints(hitPoints);
        World.sendObserverNotification("plr_curhp");
    }

    public void addHitPoints(int amountToAdd){
       setCurrentHitPoints(super.getCurrentHitPoints() + amountToAdd);
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amountToAdd){
        gold += amountToAdd;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void addExperiencePoints(int xp) {
        expPoints += xp;
    }

    public int getLevel() {
        return level;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public List<PlayerQuest> getQuests() {
        return quests;
    }

    public Location getCurrentLocation() {
        return World.LocationByID(currentLocation);
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation.getID();
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Object currentWeapon) {
        if (currentWeapon instanceof Weapon) {
            this.currentWeapon = (Weapon) currentWeapon;
        }
    }

    public void addItemToInventory(Item itemToAdd) {
        for (InventoryItem item : inventory) {
            if (item.getDetails().getID() == itemToAdd.getID()) {
                item.incrementQuantity();
                return;
            }
        }

        inventory.add(new InventoryItem(itemToAdd.getID(), 1));

        World.sendObserverNotification("plr_inv_additem");
    }

    public List<Object> getWeapons() {
        List<Object> weapons = new ArrayList<>();

        for (InventoryItem item : inventory) {
            if (item.getDetails() instanceof Weapon && item.getQuantity() > 0) {
                weapons.add(item.getDetails());
            }
        }

        return weapons;
    }

    public List<Object> getPotions() {
        List<Object> potions = new ArrayList<>();

        for (InventoryItem item : inventory) {
            if (item.getDetails() instanceof HealingPotion && item.getQuantity() > 0) {
                potions.add(item.getDetails());
            }
        }

        return potions;
    }
}

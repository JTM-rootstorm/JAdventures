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

package logic.item;

import com.google.gson.annotations.Expose;
import logic.core.World;

public class LootItem {
    @Expose private Integer details;
    @Expose private int dropPercentage;
    @Expose private boolean isDefaultItem;

    public LootItem(Integer item, int dropPercentage, boolean isDefaultItem){
        details = item;
        this.dropPercentage = dropPercentage;
        this.isDefaultItem = isDefaultItem;
    }

    public Item getDetails() {
        return World.ItemByID(details);
    }

    public void setDetails(Integer details) {
        this.details = details;
    }

    public int getDropPercentage() {
        return dropPercentage;
    }

    public void setDropPercentage(int dropPercentage) {
        this.dropPercentage = dropPercentage;
    }

    public boolean isDefaultItem() {
        return isDefaultItem;
    }

    public void setDefaultItem(boolean defaultItem) {
        isDefaultItem = defaultItem;
    }
}

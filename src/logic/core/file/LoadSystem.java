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

package logic.core.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.core.Location;
import logic.entity.Monster;
import logic.entity.Player;
import logic.item.HealingPotion;
import logic.item.Item;
import logic.item.Weapon;
import logic.quests.Quest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class LoadSystem extends FileSystemInit {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    private LoadSystem(){

    }

    public static Player loadPlayer(){
        String saveFilePath = jarPathOnSystem + "/save/";
        File save = new File(saveFilePath);
        if(save.isDirectory()){
            save = new File(saveFilePath + "Player.json");
            if(save.exists()){
                try(FileReader saveFileReader = new FileReader(save)){
                    return gson.fromJson(saveFileReader, Player.class);
                } catch (IOException ignored) {

                }
            }
        }
        return createDefaultPlayerOnLoadFail();
    }

    private static Player createDefaultPlayerOnLoadFail(){
        return new Player(10, 10, Arrays.asList(10, 10, 10, 10, 10, 10));
    }

    public static Monster loadMonster(int id){
        return gson.fromJson(SQLiteJDBCDriverConnection.selectObjectFromDatabase(id, "Monster", false).get(0), Monster.class);
    }

    public static Location loadLocation(int id){
        return gson.fromJson(SQLiteJDBCDriverConnection.selectObjectFromDatabase(id, "Location", false).get(0), Location.class);
    }

    public static Item loadItem(int id){
        List<String> resultList = SQLiteJDBCDriverConnection.selectObjectFromDatabase(id, "Item", true);

        if(Integer.parseInt(resultList.get(1)) == 1){
            return gson.fromJson(resultList.get(0), Weapon.class);
        }
        else if(Integer.parseInt(resultList.get(1)) == 2){
            return gson.fromJson(resultList.get(0), HealingPotion.class);
        }

        return gson.fromJson(resultList.get(0), Item.class);
    }

    public static Quest loadQuest(int id){
        return gson.fromJson(SQLiteJDBCDriverConnection.selectObjectFromDatabase(id, "Quest", false).get(0), Quest.class);
    }
}

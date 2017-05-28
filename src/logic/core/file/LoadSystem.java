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
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<Location> loadLocations() throws UnsupportedEncodingException {
        List<Location> locationList = load("/locations/", Location.class);

        return locationList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }

    public static List<Monster> loadMonsters() throws UnsupportedEncodingException {
        List<Monster> monsterList = load("/monsters/", Monster.class);

        return monsterList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }

    public static List<Item> loadItemList() throws UnsupportedEncodingException {
        List<Item> itemList = load("/items/general/", Item.class);
        itemList.addAll(load("/items/weapons/", Weapon.class));
        itemList.addAll(load("/items/potions/", HealingPotion.class));

        return itemList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }

    public static List<Quest> loadQuests() throws UnsupportedEncodingException {
        List<Quest> questList = load("/quests/", Quest.class);

        return questList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }

    private static <T> List<T> load(String resourcePath, Class<T> objectClassType) throws UnsupportedEncodingException{
        File saveFile = new File(getResourcePath(resourcePath));

        List<T> objectList = new ArrayList<>();

        try(Stream<Path> paths = Files.walk(Paths.get(saveFile.toURI()))){
            paths.forEach(filePath -> {
                if(Files.isRegularFile(filePath)){
                    try(FileReader saveFileReader = new FileReader(filePath.toString())){
                        objectList.add(gson.fromJson(saveFileReader, objectClassType));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectList;
    }
}

package logic.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.entity.Monster;
import logic.entity.Player;
import logic.item.HealingPotion;
import logic.item.Item;
import logic.item.weapon.Weapon;
import logic.quests.Quest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LoadSystem extends FileSystemInit {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    private LoadSystem(){}

    static Player loadPlayer(){
        String saveFilePath = jarPathOnSystem + "/save/";
        File save = new File(saveFilePath);
        if(save.isDirectory()){
            save = new File(saveFilePath + "Player.json");
            if(save.exists()){
                try(FileReader saveFileReader = new FileReader(save)){
                    return gson.fromJson(saveFileReader, Player.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new Player(10, 10, 20, 0, 1);
    }

    static List<Location> loadLocations(){
        List<Location> locationList = new ArrayList<>();
        File saveFile = new File(dataPath + "/locations/");

        try(Stream<Path> paths = Files.walk(Paths.get(saveFile.toURI()))){
            paths.forEach(filePath -> {
                if(Files.isRegularFile(filePath)){
                    try(FileReader saveFileReader = new FileReader(filePath.toFile())) {
                        locationList.add(gson.fromJson(saveFileReader, Location.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locationList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }

    static List<Monster> loadMonsters(){
        List<Monster> monsterList = new ArrayList<>();
        File saveFile = new File(dataPath + "/monsters/");

        try(Stream<Path> paths = Files.walk(Paths.get(saveFile.toURI()))){
            paths.forEach(filePath -> {
                if(Files.isRegularFile(filePath)){
                    try(FileReader saveFileReader = new FileReader(filePath.toString())) {
                        monsterList.add(gson.fromJson(saveFileReader, Monster.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return monsterList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }

    static List<Item> loadItemList(){
        List<Item> itemList = new ArrayList<>();
        File saveFile = new File(dataPath + "/items/");

        try(Stream<Path> paths = Files.walk(Paths.get(saveFile.toURI()))){
            paths.forEach(filePath -> {
                if(Files.isRegularFile(filePath)){
                    try(FileReader saveFileReader = new FileReader(filePath.toString())) {
                        if(filePath.toString().contains("_Weapon_")){
                            itemList.add(gson.fromJson(saveFileReader, Weapon.class));
                        }
                        else if(filePath.toString().contains("_Potion")){
                            itemList.add(gson.fromJson(saveFileReader, HealingPotion.class));
                        }
                        else{
                            itemList.add(gson.fromJson(saveFileReader, Item.class));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }

    static List<Quest> loadQuests(){
        List<Quest> questList = new ArrayList<>();
        File saveFile = new File(dataPath + "/quests/");

        try(Stream<Path> paths = Files.walk(Paths.get(saveFile.toURI()))){
            paths.forEach(filePath -> {
                if(Files.isRegularFile(filePath)){
                    try(FileReader saveFileReader = new FileReader(filePath.toString())) {
                        questList.add(gson.fromJson(saveFileReader, Quest.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questList.stream()
                .sorted(Comparator.comparing(o1 -> ((Integer) o1.getID()))).collect(Collectors.toList());
    }
}

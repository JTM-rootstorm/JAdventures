package logic.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.entity.Monster;
import logic.entity.Player;
import logic.item.Item;
import logic.item.weapon.Weapon;
import logic.quests.Quest;
import main.GameRunner;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class LoadSystem {
    private static Gson gson = new GsonBuilder().serializeNulls().create();
    private static String jarPathOnSystem;
    private static String dataPath;

    private LoadSystem(){}

    static void init(){
        try {
            jarPathOnSystem = FilenameUtils.getPath(
                    URLDecoder.decode(GameRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
                            "UTF-8"));

            dataPath = jarPathOnSystem + "/data/";

            File temp = new File(dataPath);
            temp.mkdir();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

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
        return null;
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

        return locationList;
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

        return monsterList;
    }

    static List<Item> loadItemList(){
        List<Item> itemList = new ArrayList<>();
        File saveFile = new File(dataPath + "/items/");

        try(Stream<Path> paths = Files.walk(Paths.get(saveFile.toURI()))){
            paths.forEach(filePath -> {
                if(Files.isRegularFile(filePath)){
                    try(FileReader saveFileReader = new FileReader(filePath.toString())) {
                        if(filePath.toString().contains("weapons")){
                            itemList.add(gson.fromJson(saveFileReader, Weapon.class));
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

        return itemList;
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

        return questList;
    }
}

package logic.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SaveSystem extends FileSystemInit{
    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private SaveSystem(){}

    static void savePlayer(Player player){
        String saveFilePath = jarPathOnSystem + "/save/";
        try {
            File save = new File(saveFilePath);
            save.mkdir();

            save = new File(saveFilePath + player.getClass().getSimpleName() + ".json");
            save.createNewFile();

            FileWriter saveFileWriter = new FileWriter(save);

            String jsonString = gson.toJson(player);

            saveFileWriter.write(jsonString);
            saveFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

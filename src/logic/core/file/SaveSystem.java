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
import logic.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveSystem extends FileSystemInit{
    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private SaveSystem(){}

    public static void savePlayer(Player player){
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

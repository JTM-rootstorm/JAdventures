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

import main.GameRunner;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileSystemInit {
    static String jarPathOnSystem;

    public static void init(){
        try {
            jarPathOnSystem = FilenameUtils.getPath(
                    URLDecoder.decode(GameRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
                            "UTF-8"));

            String dataPath = jarPathOnSystem + "/data/";

            File temp = new File(dataPath);
            temp.mkdir();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static String getResourcePath(String resource) throws UnsupportedEncodingException {
        return URLDecoder.decode(GameRunner.class.getResource(resource).getPath(), "UTF-8");
    }
}

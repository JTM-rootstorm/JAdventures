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

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteJDBCDriverConnection {

    private static Connection dbConnection = null;

    private SQLiteJDBCDriverConnection(){

    }

    public static void connect() {
        try {
            String url = formatURL();
            dbConnection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void disconnect(){
        try{
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("SqlResolve")
    static List<String> selectObjectFromDatabase(int id, String tableName, boolean gettingItem){
        String sql;
        List<String> resultList = new ArrayList<>();

        if(!gettingItem){
            sql = "Select jsonString FROM " + tableName + " WHERE id = " + Integer.toString(id);
        }
        else{
            sql = "Select jsonString, itemMetadata FROM " + tableName + " WHERE id = " + Integer.toString(id);
        }

        try (Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            resultList.add(resultSet.getString("jsonString"));

            if(gettingItem){
                resultList.add(Integer.toString(resultSet.getInt("itemMetadata")));
            }

            return resultList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String formatURL() throws UnsupportedEncodingException {
        return "jdbc:sqlite:" + FileSystemInit.getResourcePath("/db/GameData.db");
    }
}

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

package ui;

import logic.core.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;

public class MainFrame extends JFrame{
    private static GameFrame gameFrame = null;
    private static InventoryFrame inventoryFrame = null;
    private static QuestFrame questFrame = null;

    private JMenuBar menuBar = new JMenuBar();
    private static JDesktopPane desktopPane = new JDesktopPane();
    private MainFrame thisFrame = this;


    private MainFrame(){
        super("JAdventure");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(50, 50, screenSize.width - 100, screenSize.height - 100);


        setContentPane(desktopPane);
        setJMenuBar(createMenuBar());
    }

    public static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);

        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.setResizable(true);
    }

    private JMenuBar createMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(createMenuItem("New", KeyEvent.VK_N, "new",
                actionEvent -> {
                    if (gameFrame == null){
                        //createGameUI();
                        createCharacterCreationWindow();
                    }
                    else{
                        if(!gameFrame.isVisible()){
                            gameFrame.setVisible(true);
                        }
                    }
                }));

        fileMenu.add(createMenuItem("Quit", KeyEvent.VK_Q, "quit",
                actionEvent -> thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING))));

        menuBar.add(fileMenu);

        JMenu characterMenu = new JMenu("Character");
        characterMenu.setMnemonic(KeyEvent.VK_C);

        characterMenu.add(createMenuItem("Inventory", KeyEvent.VK_I, "inventory",
                actionEvent -> {
                    if(gameFrame != null){
                        if(inventoryFrame == null){
                            createInventoryWindow();
                        }
                        else{
                            if(!inventoryFrame.isVisible()){
                                inventoryFrame.setVisible(true);
                            }
                        }
                    }
                }));

        characterMenu.add(createMenuItem("Quests", KeyEvent.VK_U, "quests",
                actionEvent -> {
                    if(gameFrame != null){
                        if(questFrame == null){
                            createQuestWindow();
                        }
                        else{
                            if(!questFrame.isVisible()){
                                questFrame.setVisible(true);
                            }
                        }
                    }
                }));

        menuBar.add(characterMenu);

        return menuBar;
    }

    private JMenuItem createMenuItem(String itemName, int shortcutKey, String commandName, ActionListener actionListener){
        JMenuItem menuItem = new JMenuItem(itemName);
        menuItem.setMnemonic(shortcutKey);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcutKey, InputEvent.ALT_MASK));
        menuItem.setActionCommand(commandName);
        menuItem.addActionListener(actionListener);

        return menuItem;
    }

    static void createGameUI(){
        gameFrame = new GameFrame();
        gameFrame.show();
        desktopPane.add(gameFrame);

        try {
            gameFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void createInventoryWindow(){
        inventoryFrame = new InventoryFrame();
        inventoryFrame.show();
        desktopPane.add(inventoryFrame);

        World.sendObserverNotification("plr_inv_additem");
    }

    private void createQuestWindow(){
        questFrame = new QuestFrame();
        questFrame.show();
        desktopPane.add(questFrame);

        World.sendObserverNotification("plr_quest");
    }

    private void createCharacterCreationWindow(){
        CharacterCreationFrame characterCreationFrame = new CharacterCreationFrame();
        characterCreationFrame.show();
        desktopPane.add(characterCreationFrame);

        try {
            characterCreationFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}

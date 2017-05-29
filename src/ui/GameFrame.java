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

import logic.core.CombatLogic;
import logic.core.LocationLogic;
import logic.core.World;
import ui.components.GameButton;
import ui.components.GameCboBox;
import ui.components.GameLabel;
import ui.components.GameTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static ui.ElementCreator.*;

class GameFrame extends JInternalFrame {
    private JPanel gamePanel;

    private GameLabel lblHitPoints;
    private GameLabel lblGold;
    private GameLabel lblExperience;
    private GameLabel lblLevel;

    private GameCboBox<Object> cboWeapons;
    private GameCboBox<Object> cboPotions;

    private GameTextArea rtbLocation;
    private GameTextArea rtbMessages;

    GameFrame(){
        super("Main Window", false, true, false, true);

        final int WIDTH = 560;
        final int HEIGHT = 690;

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));

        setLocation(100, 100);

        gamePanel = new JPanel();

        gamePanel.setOpaque(true);
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setLayout(null);

        setContentPane(gamePanel);

        try {
            World.init();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        init();
        World.finishInit();
    }

    private void init(){
        initLabels();
        initComboBoxes();
        initButtons();
        initTextBoxes();
    }

    private void initLabels() {
        gamePanel.add(createNormalLabel("Hit Points: ", 18, 20, 100, 10));
        gamePanel.add(createNormalLabel("Gold: ", 18, 46, 50, 10));
        gamePanel.add(createNormalLabel("Experience: ", 18, 74, 100, 15));
        gamePanel.add(createNormalLabel("Level: ", 18, 100, 50, 10));
        gamePanel.add(createNormalLabel("Select Action", 440, 531, 110, 10));

        lblHitPoints = createGameLabel(19, message -> {
            if (message.equals("plr_curhp")) {
                lblHitPoints.setText(Integer.toString(World.getPlayer().getCurrentHitPoints()));
            }
        });
        gamePanel.add(lblHitPoints);

        lblGold = createGameLabel(45, message -> {
            if (message.equals("plr_gold")) {
                lblGold.setText(Integer.toString(World.getPlayer().getGold()));
            }
        });
        gamePanel.add(lblGold);

        lblExperience = createGameLabel(73, message -> {
            if (message.equals("plr_exp")) {
                lblExperience.setText(Integer.toString(World.getPlayer().getExpPoints()));
            }
        });
        gamePanel.add(lblExperience);

        lblLevel = createGameLabel(99, message -> {
            if (message.equals("plr_lvl")) {
                lblLevel.setText(Integer.toString(World.getPlayer().getLevel()));
            }
        });
        gamePanel.add(lblLevel);
    }

    private void initButtons() {
        GameButton btnUseWeapon = createGameButton("Use", 443, 559,
                e -> CombatLogic.useWeapon(cboWeapons.getSelectedItem()));
        btnUseWeapon.addObserver(message -> {
            if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getMonsterLivingHere() == null
                        || World.getPlayer().getWeapons().size() == 0) {
                    btnUseWeapon.setVisible(false);
                } else {
                    btnUseWeapon.setVisible(true);
                }
            }
        });
        gamePanel.add(btnUseWeapon);

        GameButton btnUsePotion = createGameButton("Use", 443, 593,
                e -> CombatLogic.drinkPotion(cboPotions.getSelectedItem()));
        btnUsePotion.addObserver(message -> {
            if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getMonsterLivingHere() == null
                        || World.getPlayer().getPotions().size() == 0) {
                    btnUsePotion.setVisible(false);
                } else {
                    btnUsePotion.setVisible(true);
                }
            }
        });
        gamePanel.add(btnUsePotion);

        GameButton btnNorth = createGameButton("North", 316, 433, e -> LocationLogic.moveNorth());
        btnNorth.addObserver(message -> {
            if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getLocationToNorth() == null) {
                    btnNorth.setVisible(false);
                } else {
                    btnNorth.setVisible(true);
                }
            }
        });
        gamePanel.add(btnNorth);

        GameButton btnEast = createGameButton("East", 396, 457, e -> LocationLogic.moveEast());
        btnEast.addObserver(message -> {
            if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getLocationToEast() == null) {
                    btnEast.setVisible(false);
                } else {
                    btnEast.setVisible(true);
                }
            }
        });
        gamePanel.add(btnEast);

        GameButton btnSouth = createGameButton("South", 316, 487, e -> LocationLogic.moveSouth());
        btnSouth.addObserver(message -> {
            if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getLocationToSouth() == null) {
                    btnSouth.setVisible(false);
                } else {
                    btnSouth.setVisible(true);
                }
            }
        });
        gamePanel.add(btnSouth);

        GameButton btnWest = createGameButton("West", 235, 457, e -> LocationLogic.moveWest());
        btnWest.addObserver(message -> {
            if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getLocationToWest() == null) {
                    btnWest.setVisible(false);
                } else {
                    btnWest.setVisible(true);
                }
            }
        });
        gamePanel.add(btnWest);
    }

    @SuppressWarnings("unchecked")
    private void initComboBoxes() {
        cboWeapons = new GameCboBox<>();
        cboWeapons.setLocation(192, 559);
        cboWeapons.setSize(150, 20);
        cboWeapons.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                World.getPlayer().setCurrentWeapon(e.getItem());
            }
        });
        cboWeapons.addObserver(message -> {
            if (message.equals("plr_inv_additem")) {
                List<Object> weapons = World.getPlayer().getWeapons();

                if (weapons.size() == 0) {
                    cboWeapons.setVisible(false);
                } else {
                    Object[] weaps = new Object[weapons.size()];
                    weaps = weapons.toArray(weaps);
                    cboWeapons.setModel(new DefaultComboBoxModel<>(weaps));

                    if (World.getPlayer().getCurrentWeapon() != null) {
                        cboWeapons.setSelectedItem(World.getPlayer().getCurrentWeapon());
                    } else {
                        cboWeapons.setSelectedIndex(0);
                    }
                }
            }
            else if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getMonsterLivingHere() == null
                        || World.getPlayer().getWeapons().size() == 0) {
                    cboWeapons.setVisible(false);
                } else {
                    cboWeapons.setVisible(true);
                }
            }
        });
        gamePanel.add(cboWeapons);

        cboPotions = new GameCboBox<>();
        cboPotions.setLocation(192, 593);
        cboPotions.setSize(150, 20);
        cboPotions.addObserver(message -> {
            if (message.equals("plr_inv_additem")) {
                List<Object> healingPotions = World.getPlayer().getPotions();

                if (healingPotions.size() == 0) {
                    cboPotions.setVisible(false);
                } else {
                    Object[] pots = new Object[healingPotions.size()];
                    pots = healingPotions.toArray(pots);
                    cboPotions.setModel(new DefaultComboBoxModel<>(pots));
                    cboPotions.setSelectedIndex(0);
                }
            }
            else if (message.equals("plr_move")) {
                if (World.getPlayer().getCurrentLocation().getMonsterLivingHere() == null
                        || World.getPlayer().getPotions().size() == 0) {
                    cboPotions.setVisible(false);
                } else {
                    cboPotions.setVisible(true);
                }
            }
        });
        gamePanel.add(cboPotions);
    }

    private void initTextBoxes() {
        rtbLocation = new GameTextArea();
        rtbLocation.setEditable(false);
        rtbLocation.addObserver((type, message) -> {
            if (type.equals("location")) {
                rtbLocation.append(message);
            }
        });
        JScrollPane locPane = new JScrollPane(rtbLocation);
        locPane.setLocation(170, 19);
        locPane.setSize(360, 105);
        locPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gamePanel.add(locPane);

        rtbMessages = new GameTextArea();
        rtbMessages.setEditable(false);
        rtbMessages.addObserver(((type, message) -> {
            if (type.equals("message")) {
                rtbMessages.append(message);
            }
        }));
        JScrollPane spane = new JScrollPane(rtbMessages);
        spane.setLocation(170, 130);
        spane.setSize(360, 286);
        spane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gamePanel.add(spane);
    }
}

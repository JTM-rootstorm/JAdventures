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
import ui.components.GameButton;
import ui.components.GameCboBox;
import ui.components.GameLabel;
import ui.components.GameTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
        JLabel label1 = new JLabel("Hit Points: ");
        label1.setLocation(18, 20);
        label1.setSize(100, 10);
        gamePanel.add(label1);

        JLabel label2 = new JLabel("Gold: ");
        label2.setLocation(18, 46);
        label2.setSize(50, 10);
        gamePanel.add(label2);

        JLabel label3 = new JLabel("Experience: ");
        label3.setLocation(18, 74);
        label3.setSize(100, 15);
        gamePanel.add(label3);

        JLabel label4 = new JLabel("Level: ");
        label4.setLocation(18, 100);
        label4.setSize(50, 10);
        gamePanel.add(label4);

        JLabel label5 = new JLabel("Select Action");
        label5.setLocation(440, 531);
        label5.setSize(110, 10);
        gamePanel.add(label5);

        lblHitPoints = new GameLabel();
        lblHitPoints.setLocation(110, 19);
        lblHitPoints.setSize(50, 10);
        lblHitPoints.addObserver(message -> {
            if (message.equals("plr_curhp")) {
                lblHitPoints.setText(Integer.toString(World.getPlayer().getCurrentHitPoints()));
            }
        });
        gamePanel.add(lblHitPoints);

        lblGold = new GameLabel();
        lblGold.setLocation(110, 45);
        lblGold.setSize(50, 10);
        lblGold.addObserver(message -> {
            if (message.equals("plr_gold")) {
                lblGold.setText(Integer.toString(World.getPlayer().getGold()));
            }
        });
        gamePanel.add(lblGold);

        lblExperience = new GameLabel();
        lblExperience.setLocation(110, 73);
        lblExperience.setSize(50, 10);
        lblExperience.addObserver(message -> {
            if (message.equals("plr_exp")) {
                lblExperience.setText(Integer.toString(World.getPlayer().getExpPoints()));
            }
        });
        gamePanel.add(lblExperience);

        lblLevel = new GameLabel();
        lblLevel.setLocation(110, 99);
        lblLevel.setSize(50, 10);
        lblLevel.addObserver(message -> {
            if (message.equals("plr_lvl")) {
                lblLevel.setText(Integer.toString(World.getPlayer().getLevel()));
            }
        });
        gamePanel.add(lblLevel);
    }

    private void initButtons() {
        final int BUTTON_WIDTH = 100;
        final int BUTTON_HEIGHT = 20;

        GameButton btnUseWeapon = new GameButton("Use");
        btnUseWeapon.setLocation(443, 559);
        btnUseWeapon.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnUseWeapon.addActionListener(e -> World.getPlayer().useWeapon(cboWeapons.getSelectedItem()));
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

        GameButton btnUsePotion = new GameButton("Use");
        btnUsePotion.setLocation(443, 593);
        btnUsePotion.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnUsePotion.addActionListener(e -> World.getPlayer().drinkPotion(cboPotions.getSelectedItem()));
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

        GameButton btnNorth = new GameButton("North");
        btnNorth.setLocation(316, 433);
        btnNorth.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnNorth.addActionListener(e -> World.getPlayer().moveNorth());
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

        GameButton btnEast = new GameButton("East");
        btnEast.setLocation(396, 457);
        btnEast.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnEast.addActionListener(e -> World.getPlayer().moveEast());
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

        GameButton btnSouth = new GameButton("South");
        btnSouth.setLocation(316, 487);
        btnSouth.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSouth.addActionListener(e -> World.getPlayer().moveSouth());
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

        GameButton btnWest = new GameButton("West");
        btnWest.setLocation(235, 457);
        btnWest.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnWest.addActionListener(e -> World.getPlayer().moveWest());
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
        add(locPane);

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

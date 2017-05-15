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

import javax.swing.*;
import java.awt.*;

class CharacterCreationFrame extends JInternalFrame {
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11;
    private JLabel lblAbsStr, lblAbsDex, lblAbsCon, lblAbsInt, lblAbsWis, lblAbsCha;
    private JLabel lblAbsStrMod, lblAbsDexMod, lblAbsConMod, lblAbsIntMod, lblAbsWisMod, lblAbsChaMod;
    private JLabel lblAbsStrRaceMod, lblAbsDexRaceMod, lblAbsConRaceMod, lblAbsIntRaceMod, lblAbsWisRaceMod, lblAbsChaRaceMod;

    private JComboBox<String> cboRace, cboRollType;

    private JButton btnRollAbs, btnSetRace, btnDone;

    private JCheckBox chkStrRaceMod, chkDexRaceMod, chkConRaceMod, chkIntRaceMod, chkWisRaceMod;

    private JPanel mainPanel;

    CharacterCreationFrame(){
        super("Character Creation", false, true, false, true);
        setSize(572, 660);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        initLabels();
        initAbsLabels();
        initCboBoxes();
        initButtons();
        initChkBoxes();

        add(mainPanel);
        setVisible(true);
    }

    private void initLabels(){
        label1 = new JLabel("Strength: ");
        label1.setLocation(34, 102);
        label1.setSize(79, 20);
        mainPanel.add(label1);

        label2 = new JLabel("Dexterity: ");
        label2.setLocation(34, 142);
        label2.setSize(79, 20);
        mainPanel.add(label2);

        label3 = new JLabel("Constitution: ");
        label3.setLocation(34, 182);
        label3.setSize(98, 20);
        mainPanel.add(label3);

        label4 = new JLabel("Intelligence: ");
        label4.setLocation(34, 222);
        label4.setSize(94, 20);
        mainPanel.add(label4);

        label5 = new JLabel("Wisdom: ");
        label5.setLocation(34, 262);
        label5.setSize(70, 20);
        mainPanel.add(label5);

        label6 = new JLabel("Charisma: ");
        label6.setLocation(34, 302);
        label6.setSize(80, 20);
        mainPanel.add(label6);

        label7 = new JLabel("Ability Score");
        label7.setLocation(280, 57);
        label7.setSize(96, 20);
        mainPanel.add(label7);

        label8 = new JLabel("ABS Mod");
        label8.setLocation(408, 57);
        label8.setSize(77, 20);
        mainPanel.add(label8);

        label9 = new JLabel("Racial Mod");
        label9.setLocation(147, 57);
        label9.setSize(88, 20);
        mainPanel.add(label9);

        label10 = new JLabel("Race: ");
        label10.setLocation(34, 388);
        label10.setSize(51, 20);
        mainPanel.add(label10);

        label11 = new JLabel("Roll Type:");
        label11.setLocation(111, 506);
        label11.setSize(78, 20);
        mainPanel.add(label11);
    }

    private void initAbsLabels(){
        Dimension emptyDim = new Dimension(20, 20);

        lblAbsStr = new JLabel("", JLabel.CENTER);
        lblAbsStr.setLocation(326, 102);
        lblAbsStr.setSize(emptyDim);
        mainPanel.add(lblAbsStr);

        lblAbsDex = new JLabel("", JLabel.CENTER);
        lblAbsDex.setLocation(326, 142);
        lblAbsDex.setSize(emptyDim);
        mainPanel.add(lblAbsDex);

        lblAbsCon = new JLabel("", JLabel.CENTER);
        lblAbsCon.setLocation(326, 182);
        lblAbsCon.setSize(emptyDim);
        mainPanel.add(lblAbsCon);

        lblAbsInt = new JLabel("", JLabel.CENTER);
        lblAbsInt.setLocation(326, 222);
        lblAbsInt.setSize(emptyDim);
        mainPanel.add(lblAbsInt);

        lblAbsWis = new JLabel("", JLabel.CENTER);
        lblAbsWis.setLocation(326, 262);
        lblAbsWis.setSize(emptyDim);
        mainPanel.add(lblAbsWis);

        lblAbsCha = new JLabel("", JLabel.CENTER);
        lblAbsCha.setLocation(326, 302);
        lblAbsCha.setSize(emptyDim);
        mainPanel.add(lblAbsCha);

        lblAbsStrMod = new JLabel("", JLabel.CENTER);
        lblAbsStrMod.setLocation(442, 102);
        lblAbsStrMod.setSize(emptyDim);
        mainPanel.add(lblAbsStrMod);

        lblAbsDexMod = new JLabel("", JLabel.CENTER);
        lblAbsDexMod.setLocation(442, 142);
        lblAbsDexMod.setSize(emptyDim);
        mainPanel.add(lblAbsDexMod);

        lblAbsConMod = new JLabel("", JLabel.CENTER);
        lblAbsConMod.setLocation(442, 182);
        lblAbsConMod.setSize(emptyDim);
        mainPanel.add(lblAbsConMod);

        lblAbsIntMod = new JLabel("", JLabel.CENTER);
        lblAbsIntMod.setLocation(442, 222);
        lblAbsIntMod.setSize(emptyDim);
        mainPanel.add(lblAbsIntMod);

        lblAbsWisMod = new JLabel("", JLabel.CENTER);
        lblAbsWisMod.setLocation(442, 262);
        lblAbsWisMod.setSize(emptyDim);
        mainPanel.add(lblAbsWisMod);

        lblAbsChaMod = new JLabel("", JLabel.CENTER);
        lblAbsChaMod.setLocation(442, 302);
        lblAbsChaMod.setSize(emptyDim);
        mainPanel.add(lblAbsChaMod);

        lblAbsStrRaceMod = new JLabel("", JLabel.CENTER);
        lblAbsStrRaceMod.setLocation(192, 102);
        lblAbsStrRaceMod.setSize(emptyDim);
        mainPanel.add(lblAbsStrRaceMod);

        lblAbsDexRaceMod = new JLabel("", JLabel.CENTER);
        lblAbsDexRaceMod.setLocation(192, 142);
        lblAbsDexRaceMod.setSize(emptyDim);
        mainPanel.add(lblAbsDexRaceMod);

        lblAbsConRaceMod = new JLabel("", JLabel.CENTER);
        lblAbsConRaceMod.setLocation(192, 182);
        lblAbsConRaceMod.setSize(emptyDim);
        mainPanel.add(lblAbsConRaceMod);

        lblAbsIntRaceMod = new JLabel("", JLabel.CENTER);
        lblAbsIntRaceMod.setLocation(192, 222);
        lblAbsIntRaceMod.setSize(emptyDim);
        mainPanel.add(lblAbsIntRaceMod);

        lblAbsWisRaceMod = new JLabel("", JLabel.CENTER);
        lblAbsWisRaceMod.setLocation(192, 262);
        lblAbsWisRaceMod.setSize(emptyDim);
        mainPanel.add(lblAbsWisRaceMod);

        lblAbsChaRaceMod = new JLabel("", JLabel.CENTER);
        lblAbsChaRaceMod.setLocation(192, 302);
        lblAbsChaRaceMod.setSize(emptyDim);
        mainPanel.add(lblAbsChaRaceMod);
    }

    private void initCboBoxes(){
        String[] races = {"Dwarf - Hill", "Dwarf - Moutain", "Elf - High", "Elf - Wood", "Halfling - Lightfoot",
                "Halfling - Stout", "Human", "Dragonborn", "Gnome - Forest", "Gnome - Rock", "Half-Elf", "Half-Orc",
                "Tiefling"};
        DefaultComboBoxModel<String> raceModel = new DefaultComboBoxModel<>(races);
        cboRace = new JComboBox<>();
        cboRace.setModel(raceModel);
        cboRace.setLocation(116, 383);
        cboRace.setSize(180, 28);
        mainPanel.add(cboRace);

        String[] rollTypes = {"3d6", "2d6+6", "4d6 drop lowest 1", "5d6 drop lowest 2"};
        DefaultComboBoxModel<String> rollModel = new DefaultComboBoxModel<>(rollTypes);
        cboRollType = new JComboBox<>();
        cboRollType.setModel(rollModel);
        cboRollType.setLocation(207, 498);
        cboRollType.setSize(108, 28);
        mainPanel.add(cboRollType);
    }

    private void initButtons(){
        btnRollAbs = new JButton("Roll!");
        btnRollAbs.setLocation(218, 425);
        btnRollAbs.setSize(160, 65);
        mainPanel.add(btnRollAbs);

        btnSetRace = new JButton("Set");
        btnSetRace.setLocation(306, 380);
        btnSetRace.setSize(112, 35);
        mainPanel.add(btnSetRace);

        btnDone = new JButton("Done");
        btnDone.setLocation(233, 553);
        btnDone.setSize(126, 62);
        mainPanel.add(btnDone);
    }

    private void initChkBoxes(){
        Dimension boxDim = new Dimension(22, 21);

        chkStrRaceMod = new JCheckBox();
        chkStrRaceMod.setLocation(508, 102);
        chkStrRaceMod.setSize(boxDim);
        chkStrRaceMod.setEnabled(false);
        mainPanel.add(chkStrRaceMod);

        chkDexRaceMod = new JCheckBox();
        chkDexRaceMod.setLocation(508, 142);
        chkDexRaceMod.setSize(boxDim);
        chkDexRaceMod.setEnabled(false);
        mainPanel.add(chkDexRaceMod);

        chkConRaceMod = new JCheckBox();
        chkConRaceMod.setLocation(508, 182);
        chkConRaceMod.setSize(boxDim);
        chkConRaceMod.setEnabled(false);
        mainPanel.add(chkConRaceMod);

        chkIntRaceMod = new JCheckBox();
        chkIntRaceMod.setLocation(508, 222);
        chkIntRaceMod.setSize(boxDim);
        chkIntRaceMod.setEnabled(false);
        mainPanel.add(chkIntRaceMod);

        chkWisRaceMod = new JCheckBox();
        chkWisRaceMod.setLocation(508, 262);
        chkWisRaceMod.setSize(boxDim);
        chkWisRaceMod.setEnabled(false);
        mainPanel.add(chkWisRaceMod);
    }
}

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
import logic.core.dice.DiceRoller;
import logic.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ui.ElementCreator.createNormalLabel;

class CharacterCreationFrame extends JInternalFrame {
    private JLabel lblAbsStr, lblAbsDex, lblAbsCon, lblAbsInt, lblAbsWis, lblAbsCha, lblTotalAbsRoll;
    private JLabel lblAbsStrMod, lblAbsDexMod, lblAbsConMod, lblAbsIntMod, lblAbsWisMod, lblAbsChaMod;
    private JLabel lblAbsStrRaceMod, lblAbsDexRaceMod, lblAbsConRaceMod, lblAbsIntRaceMod, lblAbsWisRaceMod, lblAbsChaRaceMod;

    private JComboBox<String> cboRace, cboRollType;

    private JCheckBox chkStrRaceMod, chkDexRaceMod, chkConRaceMod, chkIntRaceMod, chkWisRaceMod;

    private JPanel mainPanel;

    private int halfElfModTotal = 2;
    private int str, dex, con, intel, wis, cha;

    CharacterCreationFrame(){
        super("Character Creation", false, true, false, true);
        setSize(572, 660);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        initLabels();
        initAbsLabels();
        initCboBoxes();
        initButtons();
        initChkBoxes();
        updateRaceMods((String)cboRace.getSelectedItem());
        updateStats();

        add(mainPanel);
        setVisible(true);
    }

    private void initLabels(){
        mainPanel.add(createNormalLabel("Strength: ", 34, 102, 79, 20));
        mainPanel.add(createNormalLabel("Dexterity: ", 34, 142, 79, 20));
        mainPanel.add(createNormalLabel("Constitution: ", 34, 182, 98, 20));
        mainPanel.add(createNormalLabel("Intelligence: ", 34, 222, 94, 20));
        mainPanel.add(createNormalLabel("Wisdom: ", 34, 262, 70, 20));
        mainPanel.add(createNormalLabel("Charisma: ", 34, 302, 80, 20));
        mainPanel.add(createNormalLabel("Ability Score", 280, 57, 96, 20));
        mainPanel.add(createNormalLabel("ABS Mod", 408, 57, 77, 20));
        mainPanel.add(createNormalLabel("Racial Mod", 147, 57, 88, 20));
        mainPanel.add(createNormalLabel("Race: ", 34, 388, 51, 20));
        mainPanel.add(createNormalLabel("Roll Type:", 111, 506, 78, 20));
    }

    private void initAbsLabels(){
        lblAbsStr = createABSLabel(326, 102);
        lblAbsDex = createABSLabel(326, 142);
        lblAbsCon = createABSLabel(326, 182);
        lblAbsInt = createABSLabel(326, 222);
        lblAbsWis = createABSLabel(326, 262);
        lblAbsCha = createABSLabel(326, 302);

        lblTotalAbsRoll = createABSLabel(326, 342);

        lblAbsStrMod = createABSLabel(442, 102);
        lblAbsDexMod = createABSLabel(442, 142);
        lblAbsConMod = createABSLabel(442, 182);
        lblAbsIntMod = createABSLabel(442, 222);
        lblAbsWisMod = createABSLabel(442, 262);
        lblAbsChaMod = createABSLabel(442, 302);

        lblAbsStrRaceMod = createABSLabel(192, 102);
        lblAbsDexRaceMod = createABSLabel(192, 142);
        lblAbsConRaceMod = createABSLabel(192, 182);
        lblAbsIntRaceMod = createABSLabel(192, 222);
        lblAbsWisRaceMod = createABSLabel(192, 262);
        lblAbsChaRaceMod = createABSLabel(192, 302);
    }

    private JLabel createABSLabel(int x, int y){
        Dimension emptyDim = new Dimension(20, 20);

        JLabel tempLabel = new JLabel("0", JLabel.CENTER);
        tempLabel.setLocation(x, y);
        tempLabel.setSize(emptyDim);
        mainPanel.add(tempLabel);

        return tempLabel;
    }

    private void initCboBoxes(){
        String[] races = {"Dwarf - Hill", "Dwarf - Moutain", "Elf - High", "Elf - Wood", "Halfling - Lightfoot",
                "Halfling - Stout", "Human", "Dragonborn", "Gnome - Forest", "Gnome - Rock", "Half-Elf", "Half-Orc",
                "Tiefling"};
        DefaultComboBoxModel<String> raceModel = new DefaultComboBoxModel<>(races);
        cboRace = createComboBox(raceModel, 116, 383, 180,
                actionEvent -> updateRaceMods((String)cboRace.getSelectedItem()));

        String[] rollTypes = {"3d6", "2d6+6", "4d6 drop lowest 1", "5d6 drop lowest 2"};
        DefaultComboBoxModel<String> rollModel = new DefaultComboBoxModel<>(rollTypes);
        cboRollType = createComboBox(rollModel, 207, 502, 158, null);
    }

    private JComboBox<String> createComboBox(DefaultComboBoxModel<String> boxModel, int loc_x, int loc_y, int size_x,
                                             ActionListener actionListener){
        JComboBox<String> tempBox = new JComboBox<>();
        tempBox.setModel(boxModel);
        tempBox.setLocation(loc_x, loc_y);
        tempBox.setSize(size_x, 28);
        tempBox.addActionListener(actionListener);
        mainPanel.add(tempBox);

        return tempBox;
    }

    private void initButtons(){
        JButton btnRollAbs = new JButton("Roll!");
        btnRollAbs.setLocation(218, 425);
        btnRollAbs.setSize(160, 65);
        btnRollAbs.addActionListener(actionEvent -> rollStats());
        mainPanel.add(btnRollAbs);

        JButton btnDone = new JButton("Done");
        btnDone.setLocation(233, 553);
        btnDone.setSize(126, 62);
        btnDone.addActionListener(actionEvent -> {
            finalizeStats();
            passPlayerObjectAndStartGame();
        });
        mainPanel.add(btnDone);
    }

    private void initChkBoxes(){
        chkStrRaceMod = createCheckBox(102, actionEvent -> handleCheckBoxesForHalfElf(chkStrRaceMod));
        chkDexRaceMod = createCheckBox(142, actionEvent -> handleCheckBoxesForHalfElf(chkDexRaceMod));
        chkConRaceMod = createCheckBox(182, actionEvent -> handleCheckBoxesForHalfElf(chkDexRaceMod));
        chkIntRaceMod = createCheckBox(222, actionEvent -> handleCheckBoxesForHalfElf(chkIntRaceMod));
        chkWisRaceMod = createCheckBox(262, actionEvent -> handleCheckBoxesForHalfElf(chkWisRaceMod));
    }

    private JCheckBox createCheckBox(int loc_y, ActionListener actionListener){
        Dimension boxDim = new Dimension(22, 21);

        JCheckBox tempBox = new JCheckBox();
        tempBox.setLocation(508, loc_y);
        tempBox.setSize(boxDim);
        tempBox.setEnabled(false);
        tempBox.addActionListener(actionListener);
        mainPanel.add(tempBox);

        return tempBox;
    }

    private void updateRaceMods(String race){
        disableCheckBoxesForHalfElf();

        switch (race) {
            case "Dwarf - Hill":
                setRaceModLabels(0, 0, 2, 0, 1, 0);
                break;
            case "Dwarf - Moutain":
                setRaceModLabels(2, 0, 2, 0, 0, 0);
                break;
            case "Elf - High":
                setRaceModLabels(0, 2, 0, 1, 0, 0);
                break;
            case "Elf - Wood":
                setRaceModLabels(0, 2, 0, 0, 1, 0);
                break;
            case "Halfling - Lightfoot":
                setRaceModLabels(0, 2, 0, 0, 1, 0);
                break;
            case "Halfling - Stout":
                setRaceModLabels(0, 2, 1, 0, 0, 0);
                break;
            case "Human":
                setRaceModLabels(1, 1, 1, 1, 1, 1);
                break;
            case "Dragonborn":
                setRaceModLabels(2, 0, 0, 0, 0, 1);
                break;
            case "Gnome - Forest":
                setRaceModLabels(0, 1, 0, 2, 0, 0);
                break;
            case "Gnome - Rock":
                setRaceModLabels(0, 0, 1, 2, 0, 0);
                break;
            case "Half-Elf":
                setRaceModLabels(0, 0, 0, 0, 0, 2);
                enableCheckBoxesForHalfElf();
                break;
            case "Half-Orc":
                setRaceModLabels(2, 0, 1, 0, 0, 0);
                break;
            case "Tiefling":
                setRaceModLabels(0, 0, 0, 1, 0, 2);
                break;
                default:
                    setRaceModLabels(0, 0, 0, 0, 0, 0);
                    disableCheckBoxesForHalfElf();
                    break;
        }

        updateStats();
    }

    private void setRaceModLabels(int str, int dex, int con, int intel, int wis, int cha){
        lblAbsStrRaceMod.setText(Integer.toString(str));
        lblAbsDexRaceMod.setText(Integer.toString(dex));
        lblAbsConRaceMod.setText(Integer.toString(con));
        lblAbsIntRaceMod.setText(Integer.toString(intel));
        lblAbsWisRaceMod.setText(Integer.toString(wis));
        lblAbsChaRaceMod.setText(Integer.toString(cha));
    }

    private void resetCheckBox(JCheckBox chkBox){
        chkBox.setEnabled(false);
        chkBox.setSelected(false);
    }

    private void disableCheckBoxesForHalfElf(){
        resetCheckBox(chkStrRaceMod);
        resetCheckBox(chkDexRaceMod);
        resetCheckBox(chkConRaceMod);
        resetCheckBox(chkIntRaceMod);
        resetCheckBox(chkWisRaceMod);
    }

    private void enableCheckBoxesForHalfElf(){
        chkStrRaceMod.setEnabled(true);
        chkDexRaceMod.setEnabled(true);
        chkConRaceMod.setEnabled(true);
        chkIntRaceMod.setEnabled(true);
        chkWisRaceMod.setEnabled(true);
    }

    private void handleCheckBoxesForHalfElf(JCheckBox box){
        if (box.isSelected()){
            halfElfModTotal--;
            if (box == chkStrRaceMod){
                lblAbsStrRaceMod.setText("1");
            }
            else if (box == chkDexRaceMod){
                lblAbsDexRaceMod.setText("1");
            }
            else if (box == chkConRaceMod){
                lblAbsConRaceMod.setText("1");
            }
            else if (box == chkIntRaceMod){
                lblAbsIntRaceMod.setText("1");
            }
            else if (box == chkWisRaceMod){
                lblAbsWisRaceMod.setText("1");
            }
        }
        else {
            halfElfModTotal++;
            if (box == chkStrRaceMod){
                lblAbsStrRaceMod.setText("0");
            }
            else if (box == chkDexRaceMod){
                lblAbsDexRaceMod.setText("0");
            }
            else if (box == chkConRaceMod){
                lblAbsConRaceMod.setText("0");
            }
            else if (box == chkIntRaceMod){
                lblAbsIntRaceMod.setText("0");
            }
            else if (box == chkWisRaceMod){
                lblAbsWisRaceMod.setText("0");
            }
        }

        if (halfElfModTotal < 0){
            box.setSelected(false);
        }

        updateStats();
    }

    private void updateStats(){
        updateAbsLabelInfo(lblAbsStr, str, lblAbsStrRaceMod, lblAbsStrMod);
        updateAbsLabelInfo(lblAbsDex, dex, lblAbsDexRaceMod, lblAbsDexMod);
        updateAbsLabelInfo(lblAbsCon, con, lblAbsConRaceMod, lblAbsConMod);
        updateAbsLabelInfo(lblAbsInt, intel, lblAbsIntRaceMod, lblAbsIntMod);
        updateAbsLabelInfo(lblAbsWis, wis, lblAbsWisRaceMod, lblAbsWisMod);
        updateAbsLabelInfo(lblAbsCha, cha, lblAbsChaRaceMod, lblAbsChaMod);
        updateTotalAbsValueLabel();
    }

    private void updateAbsLabelInfo(JLabel absLabel, int abs, JLabel absRaceModLabel, JLabel absModLabel){
        absLabel.setText(Integer.toString(abs + Integer.parseInt(absRaceModLabel.getText())));
        absModLabel.setText(Integer.toString((int)Math.floor((Double.parseDouble(absLabel.getText()) - 10.0d) / 2.0d)));
    }

    private void updateTotalAbsValueLabel(){
        int value = str + Integer.parseInt(lblAbsStrRaceMod.getText()) + dex + Integer.parseInt(lblAbsDexRaceMod.getText())
                + con + Integer.parseInt(lblAbsConRaceMod.getText()) + intel + Integer.parseInt(lblAbsIntRaceMod.getText())
                + wis + Integer.parseInt(lblAbsWisRaceMod.getText()) + cha + Integer.parseInt(lblAbsChaRaceMod.getText());

        lblTotalAbsRoll.setText(Integer.toString(value));
    }

    private void rollStats(){
        if (cboRollType.getSelectedItem().equals("3d6")){
            assignRolls(3);
        }
        else if (cboRollType.getSelectedItem().equals("2d6+6")){
            assignRolls(2);
        }
        else if (cboRollType.getSelectedItem().equals("4d6 drop lowest 1")){
            assignRolls(4);
        }
        else if (cboRollType.getSelectedItem().equals("5d6 drop lowest 2")){
            assignRolls(5);
        }

        updateStats();
    }

    private void assignRolls(int numberOfDiceRolled){
        str = DiceRoller.rollAbilityScore(numberOfDiceRolled);
        dex = DiceRoller.rollAbilityScore(numberOfDiceRolled);
        con = DiceRoller.rollAbilityScore(numberOfDiceRolled);
        intel = DiceRoller.rollAbilityScore(numberOfDiceRolled);
        wis = DiceRoller.rollAbilityScore(numberOfDiceRolled);
        cha = DiceRoller.rollAbilityScore(numberOfDiceRolled);
    }

    private List<Integer> finalizeStats(){
        List<Integer> finalStatArray = new ArrayList<>();

        str += Integer.parseInt(lblAbsStrRaceMod.getText());
        dex += Integer.parseInt(lblAbsDexRaceMod.getText());
        con += Integer.parseInt(lblAbsConRaceMod.getText());
        intel += Integer.parseInt(lblAbsIntRaceMod.getText());
        wis += Integer.parseInt(lblAbsWisRaceMod.getText());
        cha += Integer.parseInt(lblAbsChaRaceMod.getText());

        finalStatArray.addAll(Arrays.asList(str, dex, con, intel, wis, cha));
        return finalStatArray;
    }

    private void passPlayerObjectAndStartGame(){
        World.setPlayer(new Player(10, 10, finalizeStats()));
        MainFrame.createGameUI();
        dispose();
    }
}

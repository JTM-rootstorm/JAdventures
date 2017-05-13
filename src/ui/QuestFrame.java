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
import logic.quests.PlayerQuest;
import ui.components.GameTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class QuestFrame extends JInternalFrame {
    private GameTable dgvQuests;

    QuestFrame(){
        super("Quests", false, true, false, true);
        setResizable(false);
        setSize(322, 210);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        JPanel mainPanel = new JPanel();

        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        String[] questCol = {"Name", "Done?"};
        DefaultTableModel questModel = new DefaultTableModel(questCol, 0);
        dgvQuests = new GameTable(questModel);
        dgvQuests.setDragEnabled(false);
        dgvQuests.setCellSelectionEnabled(false);
        dgvQuests.setEnabled(true);
        dgvQuests.addObserver(message -> {
            if (message.equals("plr_quest")) {
                DefaultTableModel uQuestModel = (DefaultTableModel) dgvQuests.getModel();
                uQuestModel.setRowCount(0);

                for (PlayerQuest quest : World.getPlayer().getQuests()) {
                    uQuestModel.addRow(new Object[]{quest.getDetails().getName(), quest.isCompleted()});
                }

                dgvQuests.setModel(uQuestModel);
                dgvQuests.revalidate();
            }
        });
        JScrollPane qPane = new JScrollPane(dgvQuests);
        qPane.setSize(320, 189);
        mainPanel.add(qPane);

        add(mainPanel);

        setVisible(true);
    }
}

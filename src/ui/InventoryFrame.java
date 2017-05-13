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
import logic.item.InventoryItem;
import ui.components.GameTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class InventoryFrame extends JInternalFrame {
    private GameTable dgvInventory;

    InventoryFrame(){
        super("Inventory", false, true, false, true);
        setResizable(false);
        setSize(313, 340);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        String[] invCol = {"Name", "Quantity"};
        DefaultTableModel invTableModel = new DefaultTableModel(invCol, 0);
        dgvInventory = new GameTable(invTableModel);
        dgvInventory.setDragEnabled(false);
        dgvInventory.setCellSelectionEnabled(false);
        dgvInventory.setEnabled(false);
        dgvInventory.addObserver(message -> {
            if (message.equals("plr_inv_additem")) {
                DefaultTableModel uInvModel = (DefaultTableModel) dgvInventory.getModel();
                uInvModel.setRowCount(0);

                for (InventoryItem item : World.getPlayer().getInventory()) {
                    if (item.getQuantity() > 0) {
                        uInvModel.addRow(new Object[]{item.getDetails().getName(), item.getQuantity()});
                    }
                }

                dgvInventory.setModel(uInvModel);
                dgvInventory.revalidate();
            }
        });
        JScrollPane invPane = new JScrollPane(dgvInventory);
        invPane.setSize(312, 309);
        mainPanel.add(invPane);

        add(mainPanel);

        setVisible(true);
    }
}

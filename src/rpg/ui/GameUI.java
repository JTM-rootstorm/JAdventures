package rpg.ui;

import rpg.logic.Location;
import rpg.logic.World;
import rpg.logic.entity.Monster;
import rpg.logic.entity.Player;
import rpg.logic.enums.ItemID;
import rpg.logic.enums.LocationID;
import rpg.logic.item.HealingPotion;
import rpg.logic.item.InventoryItem;
import rpg.logic.item.LootItem;
import rpg.logic.item.weapon.Weapon;
import rpg.logic.quests.PlayerQuest;
import rpg.logic.quests.QuestCompletionItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameUI {
    private JFrame gameFrame;
    private JPanel gamePanel;

    private JLabel lblHitPoints;
    private JLabel lblGold;
    private JLabel lblExperience;
    private JLabel lblLevel;

    private JComboBox<Weapon> cboWeapons;
    private JComboBox<HealingPotion> cboPotions;

    private JTextArea rtbLocation;
    private JTextArea rtbMessages;

    private JTable dgvInventory;
    private JTable dgvQuests;

    private JButton btnUseWeapon;
    private JButton btnUsePotion;
    private JButton btnNorth;
    private JButton btnEast;
    private JButton btnSouth;
    private JButton btnWest;

    private Player _player;
    private Monster _currentMonster;

    public GameUI(){
        initFrame();
        initLabels();
        initButtons();
        initPlayer();
        initComboBoxes();
        initTextBoxes();
        initDataTables();

        moveTo(World.LocationByID(LocationID.HOME));
    }

    private void initFrame() {
        final int WIDTH = 735;
        final int HEIGHT = 690;

        gameFrame = new JFrame();
        gamePanel = new JPanel();

        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        gameFrame.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        gameFrame.setResizable(false);

        gamePanel.setOpaque(true);
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setLayout(null);

        gameFrame.setContentPane(gamePanel);

        gameFrame.setVisible(true);
        gameFrame.setEnabled(true);
    }

    private void initLabels() {
        JLabel label1 = new JLabel("Hit Points: ");
        label1.setLocation(18,20);
        label1.setSize(100,10);
        gamePanel.add(label1);

        JLabel label2 = new JLabel("Gold: ");
        label2.setLocation(18,46);
        label2.setSize(50,10);
        gamePanel.add(label2);

        JLabel label3 = new JLabel("Experience: ");
        label3.setLocation(18,74);
        label3.setSize(100,15);
        gamePanel.add(label3);

        JLabel label4 = new JLabel("Level: ");
        label4.setLocation(18,100);
        label4.setSize(50,10);
        gamePanel.add(label4);

        JLabel label5 = new JLabel("Select Action");
        label5.setLocation(617,531);
        label5.setSize(110,10);
        gamePanel.add(label5);

        lblHitPoints = new JLabel();
        lblHitPoints.setLocation(110,19);
        lblHitPoints.setSize(50,10);
        gamePanel.add(lblHitPoints);

        lblGold = new JLabel();
        lblGold.setLocation(110,45);
        lblGold.setSize(50,10);
        gamePanel.add(lblGold);

        lblExperience = new JLabel();
        lblExperience.setLocation(110,73);
        lblExperience.setSize(50,10);
        gamePanel.add(lblExperience);

        lblLevel = new JLabel();
        lblLevel.setLocation(110,99);
        lblLevel.setSize(50,10);
        gamePanel.add(lblLevel);
    }

    private void initButtons() {
        final int BUTTON_WIDTH = 100;
        final int BUTTON_HEIGHT = 20;

        btnUseWeapon = new JButton("Use");
        btnUseWeapon.setLocation(620,559);
        btnUseWeapon.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(btnUseWeapon);

        btnUsePotion = new JButton("Use");
        btnUsePotion.setLocation(620,593);
        btnUsePotion.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(btnUsePotion);

        btnNorth = new JButton("North");
        btnNorth.setLocation(493,433);
        btnNorth.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnNorth.addActionListener(e -> moveTo(_player.getCurrentLocation().getLocationToNorth()));
        gamePanel.add(btnNorth);

        btnEast = new JButton("East");
        btnEast.setLocation(573,457);
        btnEast.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnEast.addActionListener(e -> moveTo(_player.getCurrentLocation().getLocationToEast()));
        gamePanel.add(btnEast);

        btnSouth = new JButton("South");
        btnSouth.setLocation(493,487);
        btnSouth.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSouth.addActionListener(e -> moveTo(_player.getCurrentLocation().getLocationToSouth()));
        gamePanel.add(btnSouth);

        btnWest = new JButton("West");
        btnWest.setLocation(412,457);
        btnWest.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnWest.addActionListener(e -> moveTo(_player.getCurrentLocation().getLocationToWest()));
        gamePanel.add(btnWest);
    }

    private void initPlayer() {
        _player = new Player(10,10,20,0,1);

        lblHitPoints.setText(Integer.toString(_player.getCurrentHitPoints()));
        lblGold.setText(Integer.toString(_player.getGold()));
        lblExperience.setText(Integer.toString(_player.getExpPoints()));
        lblLevel.setText(Integer.toString(_player.getLevel()));
    }

    private void initComboBoxes() {
        cboWeapons = new JComboBox<>();
        cboWeapons.setLocation(369,559);
        cboWeapons.setSize(150,20);
        gamePanel.add(cboWeapons);

        cboPotions = new JComboBox<>();
        cboPotions.setLocation(369,593);
        cboPotions.setSize(150,20);
        gamePanel.add(cboPotions);
    }

    private void initTextBoxes(){
        rtbLocation = new JTextArea();
        rtbLocation.setEditable(false);
        JScrollPane locPane = new JScrollPane(rtbLocation);
        locPane.setLocation(347,19);
        locPane.setSize(360,105);
        locPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gamePanel.add(locPane);

        rtbMessages = new JTextArea();
        rtbMessages.setEditable(false);
        JScrollPane spane = new JScrollPane(rtbMessages);
        spane.setLocation(347,130);
        spane.setSize(360,286);
        spane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gamePanel.add(spane);
    }

    private void initDataTables(){
        String[] invCol = {"Name", "Quantity"};
        DefaultTableModel invTableModel = new DefaultTableModel(invCol, 0);
        dgvInventory = new JTable(invTableModel);
        dgvInventory.setDragEnabled(false);
        dgvInventory.setCellSelectionEnabled(false);
        dgvInventory.setEnabled(false);
        JScrollPane invPane = new JScrollPane(dgvInventory);
        invPane.setLocation(16,130);
        invPane.setSize(312,309);
        gamePanel.add(invPane);

        String[] questCol = {"Name", "Done?"};
        DefaultTableModel questModel = new DefaultTableModel(questCol, 0);
        dgvQuests = new JTable(questModel);
        dgvQuests.setDragEnabled(false);
        dgvQuests.setCellSelectionEnabled(false);
        dgvQuests.setEnabled(true);
        JScrollPane qPane = new JScrollPane(dgvQuests);
        qPane.setLocation(16,446);
        qPane.setSize(312,189);
        gamePanel.add(qPane);
    }

    private void moveTo(Location newLocation){
        if(newLocation.getItemRequiredToEnter() != null){
            boolean playerHadRequiredItem = false;

            for(InventoryItem item : _player.getInventory()){
                if(item.getDetails().getID() == newLocation.getItemRequiredToEnter().getID()){
                    playerHadRequiredItem = true;
                    break;
                }
            }

            if(!playerHadRequiredItem){
                rtbMessages.append("\nYou must have a " + newLocation.getItemRequiredToEnter().getName()
                        + " to enter this location\n");
                return;
            }
        }

        _player.setCurrentLocation(newLocation);

        btnNorth.setVisible((newLocation.getLocationToNorth() != null));
        btnEast.setVisible((newLocation.getLocationToEast() != null));
        btnSouth.setVisible((newLocation.getLocationToSouth() != null));
        btnWest.setVisible((newLocation.getLocationToWest() != null));

        rtbLocation.append("\n\n" + newLocation.getName() + "\n" + newLocation.getDescription());

        _player.setCurrentHitPoints(_player.getMaxHitPoints());

        lblHitPoints.setText(Integer.toString(_player.getCurrentHitPoints()));

        if(newLocation.getQuestAvailableHere() != null){
            boolean playerAlreadyHasQuest = false;
            boolean playerAlreadyCompletedQuest = false;

            for(PlayerQuest quest : _player.getQuests()){
                if(quest.getDetails().getID() == newLocation.getQuestAvailableHere().getID()){
                    playerAlreadyHasQuest = true;

                    if(quest.isCompleted()){
                        playerAlreadyCompletedQuest = true;
                    }
                }
            }

            if(playerAlreadyHasQuest){
                if(!playerAlreadyCompletedQuest){
                    boolean playerHasAllItemsToCompleteQuest = true;

                    for(QuestCompletionItem qci : newLocation.getQuestAvailableHere().getQuestCompletionItems()){
                        boolean foundItemsInPlayerInventory = false;

                        for(InventoryItem item : _player.getInventory()){
                            if(item.getDetails().getID() == qci.getDetails().getID()){
                                foundItemsInPlayerInventory = true;

                                if(item.getQuantity() < qci.getQuantity()){
                                    playerHasAllItemsToCompleteQuest = false;
                                    break;
                                }

                                break;
                            }
                        }

                        if(!foundItemsInPlayerInventory){
                            playerHasAllItemsToCompleteQuest = false;
                            break;
                        }
                    }

                    if(playerHasAllItemsToCompleteQuest){
                        rtbMessages.append("You complete the \'"
                                + newLocation.getQuestAvailableHere().getName() + "\' quest.\n");

                        for(QuestCompletionItem qci : newLocation.getQuestAvailableHere().getQuestCompletionItems()){
                            for(InventoryItem item : _player.getInventory()){
                                if(item.getDetails().getID() == qci.getDetails().getID()){
                                    item.setQuantity(item.getQuantity() - qci.getQuantity());
                                    break;
                                }
                            }
                        }

                        rtbMessages.append("You receive: \n"
                                + Integer.toString(newLocation.getQuestAvailableHere().getRewardExperiencePoints())
                                + " XP points\n"
                                + Integer.toString(newLocation.getQuestAvailableHere().getRewardGold()) + " gold\n"
                                + newLocation.getQuestAvailableHere().getRewardItem().getName() + "\n\n");

                        _player.addExperiencePoints(newLocation.getQuestAvailableHere().getRewardExperiencePoints());
                        _player.addGold(newLocation.getQuestAvailableHere().getRewardGold());

                        boolean addedItemToPlayerInventory = false;

                        for(InventoryItem item : _player.getInventory()){
                            if(item.getDetails().getID() == newLocation.getQuestAvailableHere().getRewardItem().getID()){
                                item.incrementQuantity();
                                addedItemToPlayerInventory = true;
                                break;
                            }
                        }

                        if(!addedItemToPlayerInventory){
                            _player.getInventory()
                                    .add(new InventoryItem(newLocation.getQuestAvailableHere().getRewardItem(), 1));
                        }

                        for(PlayerQuest playerQuest : _player.getQuests()){
                            if(playerQuest.getDetails().getID() == newLocation.getQuestAvailableHere().getID()){
                                playerQuest.setCompleted(true);
                                break;
                            }
                        }
                    }
                }
            }
            else{
                rtbMessages.append("You receive the \'"
                        + newLocation.getQuestAvailableHere().getName() + "\' quest.\n"
                        + "To complete it, return with:\n");

                for(QuestCompletionItem qci : newLocation.getQuestAvailableHere().getQuestCompletionItems()){
                    if(qci.getQuantity() == 1){
                        rtbMessages.append(Integer.toString(qci.getQuantity())
                                + " " + qci.getDetails().getName() + "\n");
                    }
                    else{
                        rtbMessages.append(Integer.toString(qci.getQuantity())
                                + " " + qci.getDetails().getNamePlural() + "\n");
                    }
                }

                rtbMessages.append("\n");

                _player.getQuests().add(new PlayerQuest(newLocation.getQuestAvailableHere()));
            }
        }

        if(newLocation.getMonsterLivingHere() != null){
            rtbMessages.append("You see a " + newLocation.getMonsterLivingHere().getName()
                    + "\n");

            Monster standardMonster = World.MonsterByID(newLocation.getMonsterLivingHere().getID());

            _currentMonster = new Monster(standardMonster.getID(), standardMonster.getName(), standardMonster.getMaxDamage(),
                    standardMonster.getRewardExperiencePoints(), standardMonster.getRewardGold(),
                    standardMonster.getCurrentHitPoints(), standardMonster.getMaxHitPoints());

            for(LootItem li : standardMonster.getLootTable()){
                _currentMonster.getLootTable().add(li);
            }

            cboWeapons.setVisible(true);
            cboPotions.setVisible(true);
            btnUseWeapon.setVisible(true);
            btnUsePotion.setVisible(true);
        }
        else{
            cboWeapons.setVisible(false);
            cboPotions.setVisible(false);
            btnUseWeapon.setVisible(false);
            btnUsePotion.setVisible(false);
        }

        DefaultTableModel invModel = (DefaultTableModel) dgvInventory.getModel();
        invModel.setRowCount(0);

        for(InventoryItem item : _player.getInventory()){
            if(item.getQuantity() > 0){
                invModel.addRow(new Object[] {item.getDetails().getName(), item.getQuantity()});
            }
        }

        dgvInventory.setModel(invModel);
        dgvInventory.revalidate();

        DefaultTableModel questModel = (DefaultTableModel) dgvQuests.getModel();
        questModel.setRowCount(0);

        for(PlayerQuest quest : _player.getQuests()){
            questModel.addRow(new Object[] {quest.getDetails().getName(), quest.isCompleted()});
        }

        java.util.List<Weapon> weapons = new ArrayList<>();

        for(InventoryItem item : _player.getInventory()){
            if(item.getDetails() instanceof Weapon){
                if(item.getQuantity() > 0){
                    weapons.add((Weapon)item.getDetails());
                }
            }
        }

        if(weapons.size() == 0){
            cboWeapons.setVisible(false);
            btnUseWeapon.setVisible(false);
        }
        else{
            Weapon[] weaps = new Weapon[weapons.size()];
            weaps = weapons.toArray(weaps);
            cboWeapons.setModel(new DefaultComboBoxModel<>(weaps));
            cboWeapons.setSelectedIndex(0);
        }

        List<HealingPotion> healingPotions = new ArrayList<>();

        for(InventoryItem item : _player.getInventory()){
            if(item.getDetails() instanceof HealingPotion){
                if(item.getQuantity() > 0){
                    healingPotions.add((HealingPotion)item.getDetails());
                }
            }
        }

        if(healingPotions.size() == 0){
            cboPotions.setVisible(false);
            btnUsePotion.setVisible(false);
        }
        else{
            HealingPotion[] pots = new HealingPotion[healingPotions.size()];
            pots = healingPotions.toArray(pots);
            cboPotions.setModel(new DefaultComboBoxModel<>(pots));
            cboPotions.setSelectedIndex(0);
        }
    }
}

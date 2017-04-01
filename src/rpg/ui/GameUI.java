package rpg.ui;

import org.jetbrains.annotations.NotNull;
import rpg.logic.Location;
import rpg.logic.RandomNumberGenerator;
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
import rpg.ui.components.GameCboBox;
import rpg.ui.components.GameLabel;
import rpg.ui.components.GameTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class GameUI {
    private JPanel gamePanel;

    private GameLabel lblHitPoints;
    private GameLabel lblGold;
    private GameLabel lblExperience;
    private GameLabel lblLevel;

    private GameCboBox<Weapon> cboWeapons;
    private GameCboBox<HealingPotion> cboPotions;

    private JTextArea rtbLocation;
    private JTextArea rtbMessages;

    private GameTable dgvInventory;
    private GameTable dgvQuests;

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

        _player = World.getPlayer();

        initLabels();
        initButtons();
        initComboBoxes();
        initTextBoxes();
        initDataTables();

        _player.addItemToInventory(World.ItemByID(ItemID.RUSTY_SWORD));
        moveTo(World.LocationByID(LocationID.HOME));
    }

    private void initFrame() {
        final int WIDTH = 735;
        final int HEIGHT = 690;

        JFrame gameFrame = new JFrame();
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

        lblHitPoints = new GameLabel();
        lblHitPoints.setLocation(110,19);
        lblHitPoints.setSize(50,10);
        lblHitPoints.addObserver(message -> {
            if(message.equals("plr_curhp")){
                lblHitPoints.setText(Integer.toString(_player.getCurrentHitPoints()));
            }
        });
        gamePanel.add(lblHitPoints);

        lblGold = new GameLabel();
        lblGold.setLocation(110,45);
        lblGold.setSize(50,10);
        lblGold.addObserver(message -> {
            if(message.equals("plr_gold")){
                lblGold.setText(Integer.toString(_player.getGold()));
            }
        });
        gamePanel.add(lblGold);

        lblExperience = new GameLabel();
        lblExperience.setLocation(110,73);
        lblExperience.setSize(50,10);
        lblExperience.addObserver(message -> {
            if(message.equals("plr_exp")){
                lblExperience.setText(Integer.toString(_player.getExpPoints()));
            }
        });
        gamePanel.add(lblExperience);

        lblLevel = new GameLabel();
        lblLevel.setLocation(110,99);
        lblLevel.setSize(50,10);
        lblLevel.addObserver(message -> {
            if(message.equals("plr_lvl")){
                lblLevel.setText(Integer.toString(_player.getLevel()));
            }
        });
        gamePanel.add(lblLevel);

        _player.fireInitMessages();
    }

    private void initButtons() {
        final int BUTTON_WIDTH = 100;
        final int BUTTON_HEIGHT = 20;

        btnUseWeapon = new JButton("Use");
        btnUseWeapon.setLocation(620,559);
        btnUseWeapon.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnUseWeapon.addActionListener(e -> fightMonster());
        gamePanel.add(btnUseWeapon);

        btnUsePotion = new JButton("Use");
        btnUsePotion.setLocation(620,593);
        btnUsePotion.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnUsePotion.addActionListener(e -> drinkPotion());
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

        JButton btnSave = new JButton("Save");
        btnSave.setLocation(620, 630);
        btnSave.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btnSave.addActionListener(e -> saveGame());
        gamePanel.add(btnSave);
    }

    @SuppressWarnings("unchecked")
    private void initComboBoxes() {
        cboWeapons = new GameCboBox<>();
        cboWeapons.setLocation(369,559);
        cboWeapons.setSize(150,20);
        cboWeapons.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                _player.setCurrentWeapon((Weapon)e.getItem());
            }
        });
        cboWeapons.addObserver(message -> {
            if(message.equals("plr_inv_additem")){
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

                    if(_player.getCurrentWeapon() != null){
                        cboWeapons.setSelectedItem(_player.getCurrentWeapon());
                    }
                    else{
                        cboWeapons.setSelectedIndex(0);
                    }
                }
            }
        });
        gamePanel.add(cboWeapons);

        cboPotions = new GameCboBox<>();
        cboPotions.setLocation(369,593);
        cboPotions.setSize(150,20);
        cboPotions.addObserver(message -> {
            if(message.equals("plr_inv_additem")){
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
        });
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
        dgvInventory = new GameTable(invTableModel);
        dgvInventory.setDragEnabled(false);
        dgvInventory.setCellSelectionEnabled(false);
        dgvInventory.setEnabled(false);
        dgvInventory.addObserver(message -> {
            if(message.equals("plr_inv_additem")){
                DefaultTableModel uInvModel = (DefaultTableModel) dgvInventory.getModel();
                uInvModel.setRowCount(0);

                for(InventoryItem item : _player.getInventory()){
                    if(item.getQuantity() > 0){
                        uInvModel.addRow(new Object[] {item.getDetails().getName(), item.getQuantity()});
                    }
                }

                dgvInventory.setModel(uInvModel);
                dgvInventory.revalidate();
            }
        });
        JScrollPane invPane = new JScrollPane(dgvInventory);
        invPane.setLocation(16,130);
        invPane.setSize(312,309);
        gamePanel.add(invPane);

        String[] questCol = {"Name", "Done?"};
        DefaultTableModel questModel = new DefaultTableModel(questCol, 0);
        dgvQuests = new GameTable(questModel);
        dgvQuests.setDragEnabled(false);
        dgvQuests.setCellSelectionEnabled(false);
        dgvQuests.setEnabled(true);
        dgvQuests.addObserver(message -> {
            if(message.equals("plr_quest")){
                DefaultTableModel uQuestModel = (DefaultTableModel) dgvQuests.getModel();
                uQuestModel.setRowCount(0);

                for(PlayerQuest quest : _player.getQuests()){
                    uQuestModel.addRow(new Object[] {quest.getDetails().getName(), quest.isCompleted()});
                }

                dgvQuests.setModel(uQuestModel);
                dgvQuests.revalidate();
            }
        });
        JScrollPane qPane = new JScrollPane(dgvQuests);
        qPane.setLocation(16,446);
        qPane.setSize(312,189);
        gamePanel.add(qPane);
    }

    private void moveTo(Location newLocation){
        if(!hasRequiredItemToEnter(newLocation)){
            rtbMessages.append("\nYou must have a " + newLocation.getItemRequiredToEnter().getName()
                    + " to enter this location\n");
            return;
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
            boolean playerAlreadyHasQuest = _player.hasThisQuest(newLocation.getQuestAvailableHere());
            boolean playerAlreadyCompletedQuest = _player.completedThisQuest(newLocation.getQuestAvailableHere());

            if(playerAlreadyHasQuest){
                if(!playerAlreadyCompletedQuest){
                    boolean playerHasAllItemsToCompleteQuest =
                            _player.hasAllQuestCompletionItems(newLocation.getQuestAvailableHere());

                    if(playerHasAllItemsToCompleteQuest){
                        rtbMessages.append("You complete the \'"
                                + newLocation.getQuestAvailableHere().getName() + "\' quest.\n");

                        _player.removeQuestCompletionItems(newLocation.getQuestAvailableHere());

                        rtbMessages.append("You receive: \n"
                                + Integer.toString(newLocation.getQuestAvailableHere().getRewardExperiencePoints())
                                + " XP points\n"
                                + Integer.toString(newLocation.getQuestAvailableHere().getRewardGold()) + " gold\n"
                                + newLocation.getQuestAvailableHere().getRewardItem().getName() + "\n\n");

                        _player.addExperiencePoints(newLocation.getQuestAvailableHere().getRewardExperiencePoints());
                        _player.addGold(newLocation.getQuestAvailableHere().getRewardGold());

                        _player.addItemToInventory(newLocation.getQuestAvailableHere().getRewardItem());

                        _player.markQuestAsCompleted(newLocation.getQuestAvailableHere());
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

                _player.addQuestToList(new PlayerQuest(newLocation.getQuestAvailableHere()));
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
    }

    @NotNull
    private Boolean hasRequiredItemToEnter(Location location){
        if(location.getItemRequiredToEnter() == null){
            return true;
        }

        for(InventoryItem item : _player.getInventory()){
            if(item.getDetails().getID() == location.getItemRequiredToEnter().getID()){
                return true;
            }
        }

        return false;
    }

    private void fightMonster(){
        Weapon currentWeapon = (Weapon)cboWeapons.getSelectedItem();

        int damageToMonster = RandomNumberGenerator.NumberBetween(currentWeapon.getMinDamage(),
                currentWeapon.getMaxDamage());

        _currentMonster.setCurrentHitPoints(_currentMonster.getCurrentHitPoints() - damageToMonster);

        rtbMessages.append("You hit the " + _currentMonster.getName() + " for " + damageToMonster + " points.\n");

        if(_currentMonster.getCurrentHitPoints() <= 0){
            rtbMessages.append("\nYou defeated the " + _currentMonster.getName() + "\n");

            _player.setExpPoints(_player.getExpPoints() + _currentMonster.getRewardExperiencePoints());
            rtbMessages.append("You receive " + _currentMonster.getRewardExperiencePoints() + " experience points.\n");

            _player.setGold(_player.getGold() + _currentMonster.getRewardGold());
            rtbMessages.append("You receive " + _currentMonster.getRewardGold() + " gold.\n");

            List<InventoryItem> lootedItems = new ArrayList<>();

            for(LootItem lootItem : _currentMonster.getLootTable()){
                if(RandomNumberGenerator.NumberBetween(1, 100) <= lootItem.getDropPercentage()){
                    lootedItems.add(new InventoryItem(lootItem.getDetails(), 1));
                }
            }

            if(lootedItems.size() == 0){
                for(LootItem lootItem : _currentMonster.getLootTable()){
                    if(lootItem.isDefaultItem()){
                        lootedItems.add(new InventoryItem(lootItem.getDetails(), 1));
                    }
                }
            }

            for(InventoryItem item : lootedItems){
                _player.addItemToInventory(item.getDetails());

                if(item.getQuantity() == 1){
                    rtbMessages.append("You loot " + item.getQuantity() + " " + item.getDetails().getName() + "\n");
                }
                else{
                    rtbMessages.append("You loot " + item.getQuantity() + " " + item.getDetails().getNamePlural() + "\n");
                }
            }

            rtbMessages.append("\n");

            moveTo(_player.getCurrentLocation());
        }
        else{
            monsterAttack();
        }
    }

    private void monsterAttack(){
        int damageToPlayer = RandomNumberGenerator.NumberBetween(0, _currentMonster.getMaxDamage());

        rtbMessages.append("The " + _currentMonster.getName() + " did " + damageToPlayer + " points of damage.\n");

        _player.setCurrentHitPoints(_player.getCurrentHitPoints() - damageToPlayer);

        if(_player.getCurrentHitPoints() <= 0){
            rtbMessages.append("The " + _currentMonster.getName() + " killed you.\n");

            moveTo(World.LocationByID(LocationID.HOME));
        }
    }

    private void drinkPotion(){
        HealingPotion potion = (HealingPotion)cboPotions.getSelectedItem();

        _player.setCurrentHitPoints(_player.getCurrentHitPoints() + potion.getAmountToHeal());

        if(_player.getCurrentHitPoints() > _player.getMaxHitPoints()){
            _player.setCurrentHitPoints(_player.getMaxHitPoints());
        }

        for(InventoryItem item : _player.getInventory()){
            if(item.getDetails().getID() == potion.getID()){
                item.setQuantity(item.getQuantity() - 1);
                break;
            }
        }

        rtbMessages.append("You drink a " + potion.getName() + "\n");

        monsterAttack();
    }

    private void saveGame(){
        World.saveObjectToJson(_player, true);
    }
}

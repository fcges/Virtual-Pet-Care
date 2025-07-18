package view;

import controller.PetController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import pet.Action;
import pet.BodyEquip;
import pet.HeadEquip;
import pet.HealthStatus;
import pet.MoodEnum;


/**
 * The PetView class represents the graphical user interface for the Virtual Pet application.
 * It extends JFrame and contains components to display the pet's health status, mood, and image,
 * as well as buttons for interacting with the pet.
 */
public class PetView extends JFrame {

  private final Font smallFont;
  private final JLabel backgroundLabel;
  private final JLabel petHeadLabel;
  private final JLabel petBodyLabel;
  private final JLabel coinLabel;
  private final JLabel moodLabel;
  private final JLabel hungerLabel;
  private final JLabel socialLabel;
  private final JLabel hygieneLabel;
  private final JLabel sleepLabel;
  private final JPanel itemList;
  private final JButton feedButton;
  private final JButton playButton;
  private final JButton cleanButton;
  private final JButton sleepButton;
  private final JButton shopButton;
  private final JButton inventoryButton;
  private final JButton stepButton;
  private final JButton upButton;
  private final JButton downButton;
  private final JButton confirmButton;
  private final JButton cancelButton;
  private JLabel[] itemLabels;

  /**
   * Constructs a new PetView with all UI components.
   * Sets up the window layout, buttons, labels, and mood menu.
   */
  public PetView() {
    Font defaultFont = new Font("Arial", Font.PLAIN, 35);
    try {
      InputStream is = getClass().getResourceAsStream("/fonts/DinkieBitmap-9pxDemo.ttf");
      if (is != null) {
        defaultFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(35f);
      }
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
    this.smallFont = defaultFont.deriveFont(20f);
    setTitle("Virtual Pet Care");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 800); // Increase the size of the window
    //setResizable(false);
    setLocation(500, 50);

    //region Background
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setPreferredSize(new Dimension(800, 800));
    this.backgroundLabel  = new JLabel(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/Background_normal.png"))));
    backgroundLabel.setBounds(0, 0, 800, 800);
    JLabel frameLabel = new JLabel(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/Frame.png"))));
    frameLabel.setBounds(0, 0, 800, 800);
    this.petBodyLabel = new JLabel(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/petBody_NONE.png"))));
    petBodyLabel.setBounds(0, 0, 800, 800);
    this.petHeadLabel = new JLabel(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/petHead_NONE.png"))));
    petHeadLabel.setBounds(0, 0, 800, 800);
    layeredPane.add(backgroundLabel);
    layeredPane.add(petBodyLabel);
    layeredPane.add(petHeadLabel);
    layeredPane.add(frameLabel);
    //endregion

    //region Information Text
    this.itemList = new JPanel();
    itemList.setLayout(new BoxLayout(itemList, BoxLayout.Y_AXIS));
    itemList.setOpaque(false);
    itemList.setBounds(430, 150, 250, 340);

    this.coinLabel = new JLabel("<html>Coin<br>Loading...");
    coinLabel.setFont(defaultFont);
    coinLabel.setBounds(125, 15, 200, 100);
    this.moodLabel = new JLabel("<html>Mood<br>Loading...");
    moodLabel.setFont(defaultFont);
    moodLabel.setBounds(450, 15, 200, 100);
    this.hungerLabel = new JLabel("<html>Hunger<br>Loading...");
    hungerLabel.setFont(defaultFont);
    hungerLabel.setBounds(110, 510, 200, 100);
    this.socialLabel = new JLabel("<html>Social<br>Loading...");
    socialLabel.setFont(defaultFont);
    socialLabel.setBounds(350, 510, 200, 100);
    this.hygieneLabel = new JLabel("<html>Hygiene<br>Loading...");
    hygieneLabel.setFont(defaultFont);
    hygieneLabel.setBounds(110, 600, 200, 100);
    this.sleepLabel = new JLabel("<html>Sleep<br>Loading...");
    sleepLabel.setFont(defaultFont);
    sleepLabel.setBounds(360, 600, 200, 100);

    layeredPane.add(coinLabel);
    layeredPane.add(moodLabel);
    layeredPane.add(hungerLabel);
    layeredPane.add(socialLabel);
    layeredPane.add(hygieneLabel);
    layeredPane.add(sleepLabel);
    layeredPane.add(itemList);
    //endregion

    //region Buttons
    JButton quitButton = new JButton();
    quitButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/quitButton_normal.png"))));
    quitButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/quitButton_pressed.png"))));
    quitButton.setBounds(690, 15, 96, 96);
    quitButton.setBorderPainted(false);
    quitButton.setFocusPainted(false);
    quitButton.addActionListener(e -> System.exit(0));
    this.feedButton = new JButton();
    feedButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/feedButton_normal.png"))));
    feedButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/feedButton_pressed.png"))));
    feedButton.setBounds(490, 530, 144, 72);
    feedButton.setBorderPainted(false);
    feedButton.setFocusPainted(false);
    this.playButton = new JButton();
    playButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/playButton_normal.png"))));
    playButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/playButton_pressed.png"))));
    playButton.setBounds(640, 530, 144, 72);
    playButton.setBorderPainted(false);
    playButton.setFocusPainted(false);
    this.cleanButton = new JButton();
    cleanButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/cleanButton_normal.png"))));
    cleanButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/cleanButton_pressed.png"))));
    cleanButton.setBounds(490, 610, 144, 72);
    cleanButton.setBorderPainted(false);
    cleanButton.setFocusPainted(false);
    this.sleepButton = new JButton();
    sleepButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/sleepButton_normal.png"))));
    sleepButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/sleepButton_pressed.png"))));
    sleepButton.setBounds(640, 610, 144, 72);
    sleepButton.setBorderPainted(false);
    sleepButton.setFocusPainted(false);
    this.shopButton = new JButton();
    shopButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/shopButton_normal.png"))));
    shopButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/shopButton_pressed.png"))));
    shopButton.setBounds(20, 130, 96, 96);
    shopButton.setBorderPainted(false);
    shopButton.setFocusPainted(false);
    this.inventoryButton = new JButton();
    inventoryButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/inventoryButton_normal.png"))));
    inventoryButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/inventoryButton_pressed.png"))));
    inventoryButton.setBounds(20, 236, 96, 96);
    inventoryButton.setBorderPainted(false);
    inventoryButton.setFocusPainted(false);
    this.stepButton = new JButton();
    stepButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/stepButton_normal.png"))));
    stepButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/stepButton_pressed.png"))));
    stepButton.setBounds(20, 410, 96, 96);
    stepButton.setBorderPainted(false);
    stepButton.setFocusPainted(false);
    this.upButton = new JButton();
    upButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/upArrowButton_normal.png"))));
    upButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/upArrowButton_pressed.png"))));
    upButton.setBounds(710, 140, 64, 96);
    upButton.setBorderPainted(false);
    upButton.setContentAreaFilled(false);
    upButton.setFocusPainted(false);
    this.downButton = new JButton();
    downButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/downArrowButton_normal.png"))));
    downButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/downArrowButton_pressed.png"))));
    downButton.setBounds(710, 250, 64, 96);
    downButton.setBorderPainted(false);
    downButton.setContentAreaFilled(false);
    downButton.setFocusPainted(false);
    this.confirmButton = new JButton();
    confirmButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/confirmButton_normal.png"))));
    confirmButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/confirmButton_pressed.png"))));
    confirmButton.setBounds(710, 360, 64, 64);
    confirmButton.setBorderPainted(false);
    confirmButton.setFocusPainted(false);
    this.cancelButton = new JButton();
    cancelButton.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/cancelButton_normal.png"))));
    cancelButton.setPressedIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/cancelButton_pressed.png"))));
    cancelButton.setBounds(710, 440, 64, 64);
    cancelButton.setBorderPainted(false);
    cancelButton.setFocusPainted(false);

    layeredPane.add(quitButton);
    layeredPane.add(feedButton);
    layeredPane.add(playButton);
    layeredPane.add(cleanButton);
    layeredPane.add(sleepButton);
    layeredPane.add(shopButton);
    layeredPane.add(inventoryButton);
    layeredPane.add(stepButton);
    layeredPane.add(upButton);
    layeredPane.add(downButton);
    layeredPane.add(confirmButton);
    layeredPane.add(cancelButton);
    //endregion

    //region Set Layers
    layeredPane.setLayer(backgroundLabel, 0);
    layeredPane.setLayer(petBodyLabel, 1);
    layeredPane.setLayer(petHeadLabel, 2);
    layeredPane.setLayer(itemList, 2);
    layeredPane.setLayer(frameLabel, 3);
    layeredPane.setLayer(coinLabel, 4);
    layeredPane.setLayer(moodLabel, 4);
    layeredPane.setLayer(hungerLabel, 4);
    layeredPane.setLayer(socialLabel, 4);
    layeredPane.setLayer(hygieneLabel, 4);
    layeredPane.setLayer(sleepLabel, 4);
    layeredPane.setLayer(quitButton, 5);
    layeredPane.setLayer(feedButton, 5);
    layeredPane.setLayer(playButton, 5);
    layeredPane.setLayer(cleanButton, 5);
    layeredPane.setLayer(sleepButton, 5);
    layeredPane.setLayer(shopButton, 5);
    layeredPane.setLayer(inventoryButton, 5);
    layeredPane.setLayer(stepButton, 5);
    layeredPane.setLayer(upButton, 5);
    layeredPane.setLayer(downButton, 5);
    layeredPane.setLayer(confirmButton, 5);
    layeredPane.setLayer(cancelButton, 5);
    //endregion

    add(layeredPane);
    pack();
    setVisible(true);
  }

  /**
   * Adds action listeners to the buttons in the PetView.
   * @param controller the PetController to handle button actions
   */
  public void addFeatures(PetController controller) {
    feedButton.addActionListener(e -> controller.interact(Action.FEED));
    playButton.addActionListener(e -> controller.interact(Action.PLAY));
    cleanButton.addActionListener(e -> controller.interact(Action.CLEAN));
    sleepButton.addActionListener(e -> controller.interact(Action.SLEEP));
    shopButton.addActionListener(e -> controller.initShop());
    inventoryButton.addActionListener(e -> controller.initInventory());
    stepButton.addActionListener(e -> controller.step());
    upButton.addActionListener(e -> controller.changeSelect(true));
    downButton.addActionListener(e -> controller.changeSelect(false));
    confirmButton.addActionListener(e -> controller.confirmSelect());
    cancelButton.addActionListener(e -> controller.cancelSelect());
  }

  /**
   * Updates the pet's image based on the equipped head and body items.
   * @param head the equipped head item
   * @param body the equipped body item
   */
  public void updatePetImage(HeadEquip head, BodyEquip body) {
    this.petHeadLabel.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/petHead_" + head + ".png"))));
    this.petBodyLabel.setIcon(new ImageIcon(
        Objects.requireNonNull(getClass().getResource("/images/petBody_" + body + ".png"))));
  }

  /**
   * Updates the coin label with the current coin amount.
   * @param coin the current coin amount
   */
  public void updateCoin(int coin) {
    this.coinLabel.setText("<html>Coin<br>" + coin);
  }

  /**
   * Updates the mood label with the current mood of the pet.
   * @param mood the current mood of the pet
   */
  public void updateMood(MoodEnum mood) {
    this.moodLabel.setText("<html>Mood<br>" + mood);
  }

  /**
   * Updates the health status labels with the current health values.
   * @param health the current health status of the pet
   */
  public void updateHealth(HealthStatus health) {
    this.hungerLabel.setText("<html>Hunger<br>" + health.getHunger() + "/100");
    this.socialLabel.setText("<html>Social<br>" + health.getSocial() + "/100");
    this.hygieneLabel.setText("<html>Hygiene<br>" + health.getHygiene() + "/100");
    this.sleepLabel.setText("<html>Sleep<br>" + health.getSleep() + "/100");
  }

  /**
   * Displays a message in the ItemList panel.
   * @param message the message to display
   */
  public void displayMessage(String message) {
    // Clear previous messages
    itemList.removeAll();
    // Add new message
    JLabel messageLabel = new JLabel(message);
    messageLabel.setFont(smallFont);
    messageLabel.setForeground(java.awt.Color.white);
    itemList.add(messageLabel);
    // Refresh the ItemList panel
    itemList.revalidate();
    itemList.repaint();
  }

  /**
   * Displays the shop items in the ItemList panel.
   * @param headInventory the inventory of head items
   * @param bodyInventory the inventory of body items
   */
  public void displayShop(Map<HeadEquip, Integer> headInventory,
                          Map<BodyEquip, Integer> bodyInventory) {
    itemList.removeAll();
    itemLabels = new JLabel[HeadEquip.values().length + BodyEquip.values().length];
    itemLabels[0] = createItemLabel("<html><div style='width:190px;'>"
        + "------Head Equipments------"
        + "</div></html>");
    itemList.add(itemLabels[0]);
    int i = 1;
    for (HeadEquip head : HeadEquip.values()) {
      if (head == HeadEquip.NONE) {
        continue;
      }
      itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
          + head.getName()
          + (headInventory.get(head) == 0 ? ("<br>Cost: " + head.getCost()) : ("<br>Sold Out"))
          + "</div></html>");
      itemList.add(itemLabels[i]);
      i++;
    }
    itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
        + "------Body Equipments------"
        + "</div></html>");
    itemList.add(itemLabels[i]);
    i++;
    for (BodyEquip body : BodyEquip.values()) {
      if (body == BodyEquip.NONE) {
        continue;
      }
      itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
          + body.getName()
          + (bodyInventory.get(body) == 0 ? ("<br>Cost: " + body.getCost()) : ("<br>Sold Out"))
          + "</div></html>");
      itemList.add(itemLabels[i]);
      i++;
    }
    itemLabels[1].setBackground(java.awt.Color.white);
    itemLabels[1].setOpaque(true);
    itemLabels[1].setForeground(Color.darkGray);
    itemList.revalidate();
    itemList.repaint();
  }

  /**
   * Displays the inventory items in the ItemList panel.
   * @param headInventory the inventory of head items
   * @param bodyInventory the inventory of body items
   */
  public void displayInventory(Map<HeadEquip, Integer> headInventory,
                               Map<BodyEquip, Integer> bodyInventory) {
    itemList.removeAll();
    itemLabels = new JLabel[HeadEquip.values().length + BodyEquip.values().length + 2];
    itemLabels[0] = createItemLabel("<html><div style='width:190px;'>"
        + "------Head Equipments------"
        + "</div></html>");
    itemList.add(itemLabels[0]);
    int i = 1;
    for (HeadEquip head : HeadEquip.values()) {
      if (head == HeadEquip.NONE) {
        itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
            + head.getName()
            + "</div></html>");
        itemList.add(itemLabels[i]);
        i++;
        continue;
      }
      itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
          + (headInventory.get(head) != 0
          ? (head.getName() + " Durability: " + headInventory.get(head)) : ("No Item"))
          + "</div></html>");
      itemList.add(itemLabels[i]);
      i++;
    }
    itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
        + "------Body Equipments------"
        + "</div></html>");
    itemList.add(itemLabels[i]);
    i++;
    for (BodyEquip body : BodyEquip.values()) {
      if (body == BodyEquip.NONE) {
        itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
            + body.getName()
            + "</div></html>");
        itemList.add(itemLabels[i]);
        i++;
        continue;
      }
      itemLabels[i] = createItemLabel("<html><div style='width:190px;'>"
          + (bodyInventory.get(body) != 0
          ? (body.getName() + " Durability: " + bodyInventory.get(body)) : ("No Item"))
          + "</div></html>");
      itemList.add(itemLabels[i]);
      i++;
    }
    itemLabels[1].setBackground(java.awt.Color.white);
    itemLabels[1].setOpaque(true);
    itemLabels[1].setForeground(Color.darkGray);
    itemList.revalidate();
    itemList.repaint();
  }

  /**
   * Updates the selected item in the shop menu.
   * @param index the index of the selected item
   */
  public void updateShopSelect(int index) {
    int i = 0;
    for (HeadEquip head : HeadEquip.values()) {
      if (head == HeadEquip.NONE) {
        continue;
      }
      if (i == index && index < HeadEquip.values().length - 1) {
        itemLabels[i + 1].setBackground(java.awt.Color.white);
        itemLabels[i + 1].setOpaque(true);
        itemLabels[i + 1].setForeground(Color.darkGray);
      } else {
        itemLabels[i + 1].setBackground(null);
        itemLabels[i + 1].setOpaque(false);
        itemLabels[i + 1].setForeground(java.awt.Color.white);
      }
      i++;
    }
    i = HeadEquip.values().length - 1;
    for (BodyEquip body : BodyEquip.values()) {
      if (body == BodyEquip.NONE) {
        continue;
      }
      if (i == index && index >= HeadEquip.values().length - 1) {
        itemLabels[i + 2].setBackground(java.awt.Color.white);
        itemLabels[i + 2].setOpaque(true);
        itemLabels[i + 2].setForeground(Color.darkGray);
      } else {
        itemLabels[i + 2].setBackground(null);
        itemLabels[i + 2].setOpaque(false);
        itemLabels[i + 2].setForeground(java.awt.Color.white);
      }
      i++;
    }
    itemList.revalidate();
    itemList.repaint();
  }

  /**
   * Updates the selected item in the inventory menu.
   * @param index the index of the selected item
   */
  public void updateInventorySelect(int index) {
    int i = 0;
    for (HeadEquip head : HeadEquip.values()) {
      if (i == index && index < HeadEquip.values().length) {
        itemLabels[i + 1].setBackground(java.awt.Color.white);
        itemLabels[i + 1].setOpaque(true);
        itemLabels[i + 1].setForeground(Color.darkGray);
      } else {
        itemLabels[i + 1].setBackground(null);
        itemLabels[i + 1].setOpaque(false);
        itemLabels[i + 1].setForeground(java.awt.Color.white);
      }
      i++;
    }
    i = HeadEquip.values().length;
    for (BodyEquip body : BodyEquip.values()) {
      if (i == index && index >= HeadEquip.values().length) {
        itemLabels[i + 2].setBackground(java.awt.Color.white);
        itemLabels[i + 2].setOpaque(true);
        itemLabels[i + 2].setForeground(Color.darkGray);
      } else {
        itemLabels[i + 2].setBackground(null);
        itemLabels[i + 2].setOpaque(false);
        itemLabels[i + 2].setForeground(java.awt.Color.white);
      }
      i++;
    }
    itemList.revalidate();
    itemList.repaint();
  }

  /**
   * Creates a JLabel with the specified text and small font.
   * @param text the text to display in the label
   * @return the created JLabel
   */
  private JLabel createItemLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(smallFont);
    label.setForeground(java.awt.Color.white);
    return label;
  }

  /**
   * Sets the scene of the PetView based on the specified scene number.
   * @param scene the scene number to set (0: normal, -1: game over, 1: shop, 2: inventory)
   */
  public void setScene(int scene) {
    switch (scene) {
      case 0:
        feedButton.setEnabled(true);
        playButton.setEnabled(true);
        cleanButton.setEnabled(true);
        sleepButton.setEnabled(true);
        stepButton.setEnabled(true);
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        confirmButton.setEnabled(false);
        cancelButton.setEnabled(false);
        backgroundLabel.setIcon(new ImageIcon(
            Objects.requireNonNull(getClass().getResource("/images/Background_normal.png"))));
        break;
      case -1:
        feedButton.setEnabled(false);
        playButton.setEnabled(false);
        cleanButton.setEnabled(false);
        sleepButton.setEnabled(false);
        shopButton.setEnabled(false);
        inventoryButton.setEnabled(false);
        stepButton.setEnabled(false);
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        confirmButton.setEnabled(false);
        cancelButton.setEnabled(false);
        break;
      case 1:
        feedButton.setEnabled(false);
        playButton.setEnabled(false);
        cleanButton.setEnabled(false);
        sleepButton.setEnabled(false);
        stepButton.setEnabled(false);
        upButton.setEnabled(true);
        downButton.setEnabled(true);
        confirmButton.setEnabled(true);
        cancelButton.setEnabled(true);
        petBodyLabel.setIcon(null);
        petHeadLabel.setIcon(null);
        backgroundLabel.setIcon(new ImageIcon(
            Objects.requireNonNull(getClass().getResource("/images/Background_shop.png"))));
        break;
      case 2:
        feedButton.setEnabled(false);
        playButton.setEnabled(false);
        cleanButton.setEnabled(false);
        sleepButton.setEnabled(false);
        stepButton.setEnabled(false);
        upButton.setEnabled(true);
        downButton.setEnabled(true);
        confirmButton.setEnabled(true);
        cancelButton.setEnabled(true);
        petBodyLabel.setIcon(null);
        petHeadLabel.setIcon(null);
        backgroundLabel.setIcon(new ImageIcon(
            Objects.requireNonNull(getClass().getResource("/images/Background_inventory.png"))));
        break;
      default:
        break;
    }
  }
}
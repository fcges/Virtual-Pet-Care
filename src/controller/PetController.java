package controller;

import pet.Action;
import pet.BodyEquip;
import pet.HeadEquip;
import pet.PetInterface;
import view.PetView;

/**
 * Controller class for the pet game.
 * It handles the interaction between the view and the model.
 */
public class PetController {

  PetView view;
  PetInterface model;
  int selectedIndex = 0;
  int scene = 0;

  /**
   * Constructor for PetController, initializes the game.
   * @param view the view of the game
   * @param model the model of the game
   */
  public void playGame(PetView view, PetInterface model) {
    this.view = view;
    this.model = model;
    view.addFeatures(this);
    view.setScene(0);
    this.view.updateCoin(model.getCoin());
    this.view.updateHealth(model.getHealth());
    this.view.updateMood(model.getMood());
    this.view.updatePetImage(model.getEquipHead(), model.getEquipBody());
  }

  /**
   * Interact with the pet using the specified action.
   * @param action the action to perform
   */
  public void interact(Action action) {
    try {
      model.interactWith(action);
      view.updateCoin(model.getCoin());
      view.updateHealth(model.getHealth());
      view.updateMood(model.getMood());
      view.updatePetImage(model.getEquipHead(), model.getEquipBody());
      view.displayMessage("<html><div style='width:190px;'>"
          + "You chose " + action + "!"
          + "<br>Cost: " + action.getCost()
          + "<br><br>------Wearing------"
          + "<br>Head: " + model.getEquipHead().getName()
          + "<br>" + model.getEquipHead().getDescribe()
          + "<br><br>Body: " + model.getEquipBody().getName()
          + "<br>" + model.getEquipBody().getDescribe()
          + "</div></html>");
    } catch (IllegalArgumentException e) {
      view.displayMessage("<html><div style='width:190px;'>"
          + e.getMessage()
          + "<br><br>------Wearing------"
          + "<br>Head: " + model.getEquipHead().getName()
          + "<br>" + model.getEquipHead().getDescribe()
          + "<br><br>Body: " + model.getEquipBody().getName()
          + "<br>" + model.getEquipBody().getDescribe()
          + "</div></html>");
    }
  }

  /**
   * Initialize the shop scene.
   */
  public void initShop() {
    scene = 1;
    view.setScene(scene);
    view.displayShop(model.getHeadInventory(), model.getBodyInventory());
    selectedIndex = 0;
  }

  /**
   * Initialize the inventory scene.
   */
  public void initInventory() {
    scene = 2;
    view.setScene(scene);
    view.displayInventory(model.getHeadInventory(), model.getBodyInventory());
    selectedIndex = 0;
  }

  /**
   * Advance the game by one step.
   */
  public void step() {
    final int previousCoin = model.getCoin();
    model.step();
    view.updateCoin(model.getCoin());
    view.updateHealth(model.getHealth());
    view.updateMood(model.getMood());
    view.updatePetImage(model.getEquipHead(), model.getEquipBody());
    if (model.isGameOver()) {
      view.displayMessage("<html><body style='width:190px;'>"
          + "Game Over!"
          + "</body></html>");
      view.setScene(-1);
    } else {
      view.displayMessage("<html><body style='width:190px;'>"
          + "You earned " + (model.getCoin() - previousCoin) + " coins."
          + "<br><br>------Wearing------"
          + "<br>Head: " + model.getEquipHead().getName()
          + "<br>" + model.getEquipHead().getDescribe()
          + "<br><br>Body: " + model.getEquipBody().getName()
          + "<br>" + model.getEquipBody().getDescribe()
          + "</body></html>");
    }
  }

  /**
   * Change the selected item in the shop or inventory.
   * @param isUp true if moving up, false if moving down
   */
  public void changeSelect(boolean isUp) {
    if (scene == 1) {
      if (isUp) {
        selectedIndex = (selectedIndex - 1
            + HeadEquip.values().length + BodyEquip.values().length - 2)
            % (HeadEquip.values().length + BodyEquip.values().length - 2);
      } else {
        selectedIndex = (selectedIndex + 1)
            % (HeadEquip.values().length + BodyEquip.values().length - 2);
      }
      view.updateShopSelect(selectedIndex);
    } else if (scene == 2) {
      if (isUp) {
        selectedIndex = (selectedIndex - 1
            + HeadEquip.values().length + BodyEquip.values().length)
            % (HeadEquip.values().length + BodyEquip.values().length);
      } else {
        selectedIndex = (selectedIndex + 1)
            % (HeadEquip.values().length + BodyEquip.values().length);
      }
      view.updateInventorySelect(selectedIndex);
    }
  }

  /**
   * Confirm the selected item in the shop or inventory.
   */
  public void confirmSelect() {
    if (scene == 1) {
      try {
        if (selectedIndex < HeadEquip.values().length - 1) {
          model.buyEquip(HeadEquip.values()[selectedIndex + 1]);
        } else {
          model.buyEquip(BodyEquip.values()[selectedIndex - HeadEquip.values().length + 2]);
        }
        view.updateCoin(model.getCoin());
        view.updatePetImage(model.getEquipHead(), model.getEquipBody());
        view.displayMessage("<html><body style='width:190px;'>"
            + "You bought "
            + (selectedIndex < HeadEquip.values().length - 1
            ? HeadEquip.values()[selectedIndex + 1].getName() :
            BodyEquip.values()[selectedIndex - HeadEquip.values().length + 2].getName())
            + ".<br><br>------Wearing------"
            + "<br>Head: " + model.getEquipHead().getName()
            + "<br>" + model.getEquipHead().getDescribe()
            + "<br><br>Body: " + model.getEquipBody().getName()
            + "<br>" + model.getEquipBody().getDescribe()
            + "</body></html>");
      } catch (IllegalArgumentException e) {
        view.displayMessage("<html><body style='width:190px;'>"
            + e.getMessage()
            + "<br><br>------Wearing------"
            + "<br>Head: " + model.getEquipHead().getName()
            + "<br>" + model.getEquipHead().getDescribe()
            + "<br><br>Body: " + model.getEquipBody().getName()
            + "<br>" + model.getEquipBody().getDescribe()
            + "</body></html>");
      }
    } else {
      try {
        if (selectedIndex < HeadEquip.values().length) {
          model.setEquipHead(HeadEquip.values()[selectedIndex]);
        } else {
          model.setEquipBody(BodyEquip.values()[selectedIndex - HeadEquip.values().length]);
        }
        view.updateMood(model.getMood());
        view.updatePetImage(model.getEquipHead(), model.getEquipBody());
        view.displayMessage("<html><body style='width:190px;'>"
            + "You equipped "
            + (selectedIndex < model.getHeadInventory().size()
            ? HeadEquip.values()[selectedIndex].getName() + " on head." :
            BodyEquip.values()[selectedIndex - HeadEquip.values().length].getName()
                + " on body.")
            + "<br><br>------Wearing------"
            + "<br>Head: " + model.getEquipHead().getName()
            + "<br>" + model.getEquipHead().getDescribe()
            + "<br><br>Body: " + model.getEquipBody().getName()
            + "<br>" + model.getEquipBody().getDescribe()
            + "</body></html>");
      } catch (IllegalArgumentException e) {
        view.displayMessage("<html><body style='width:190px;'>"
            + e.getMessage()
            + "<br><br>------Wearing------"
            + "<br>Head: " + model.getEquipHead().getName()
            + "<br>" + model.getEquipHead().getDescribe()
            + "<br><br>Body: " + model.getEquipBody().getName()
            + "<br>" + model.getEquipBody().getDescribe()
            + "</body></html>");
      }
    }
    scene = 0;
    view.setScene(scene);
    view.updatePetImage(model.getEquipHead(), model.getEquipBody());
  }

  /**
   * Cancel the selection and return to the main scene.
   */
  public void cancelSelect() {
    scene = 0;
    view.setScene(scene);
    view.displayMessage("<html><div style='width:190px;'>"
        + "------Wearing------"
        + "<br>Head: " + model.getEquipHead().getName()
        + "<br>" + model.getEquipHead().getDescribe()
        + "<br><br>Body: " + model.getEquipBody().getName()
        + "<br>" + model.getEquipBody().getDescribe()
        + "</div></html>");
    view.updatePetImage(model.getEquipHead(), model.getEquipBody());
  }
}

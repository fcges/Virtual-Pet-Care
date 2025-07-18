package pet;

import java.util.Map;

/**
 * Interface for the pet model.
 */
public interface PetInterface {

  /**
   * Advance the time of the pet by one step.
   */
  void step();

  /**
   * Interact with the pet by performing an action.
   * @param action the action to perform
   */
  void interactWith(Action action);

  /**
   * Get the health status of the pet.
   * @return the health status of the pet
   */
  HealthStatus getHealth();

  /**
   * Set the mood of the pet.
   * @param mood the mood to set
   */
  void setMood(MoodEnum mood);

  /**
   * Get the mood of the pet.
   * @return the mood of the pet
   */
  MoodEnum getMood();

  /**
   * Get the head equipment of the pet.
   * @return the head equipment of the pet
   */
  HeadEquip getEquipHead();

  /**
   * Get the body equipment of the pet.
   * @return the body equipment of the pet
   */
  BodyEquip getEquipBody();

  /**
   * Set the head equipment of the pet.
   * @param head the head equipment to set
   * @throws IllegalArgumentException if the head equipment is not in the inventory
   */
  void setEquipHead(HeadEquip head) throws IllegalArgumentException;

  /**
   * Set the body equipment of the pet.
   * @param body the body equipment to set
   * @throws IllegalArgumentException if the body equipment is not in the inventory
   */
  void setEquipBody(BodyEquip body) throws IllegalArgumentException;

  /**
   * Buy head equipment for the pet.
   * @param head the head equipment to buy
   * @throws IllegalArgumentException if the pet does not have enough coins
   */
  void buyEquip(HeadEquip head) throws IllegalArgumentException;

  /**
   * Buy body equipment for the pet.
   * @param body the body equipment to buy
   * @throws IllegalArgumentException if the pet does not have enough coins
   */
  void buyEquip(BodyEquip body) throws IllegalArgumentException;

  /**
   * Check if the game is over by the coin and mood of the pet.
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Get the durability of the head equipment.
   * @param head the head equipment to get the durability of
   * @return the durability of the head equipment
   */
  int getDurability(HeadEquip head);

  /**
   * Get the durability of the body equipment.
   * @param body the body equipment to get the durability of
   * @return the durability of the body equipment
   */
  int getDurability(BodyEquip body);

  /**
   * Set the coin of the pet.
   * @param coin the coin to set
   */
  void setCoin(int coin);

  /**
   * Get the coin of the pet.
   * @return the coin of the pet
   */
  int getCoin();

  /**
   * Get the name of the pet.
   * @return the name of the pet
   */
  String getName();

  /**
   * Get the inventory of head equipment.
   * @return the inventory of head equipment
   */
  Map<HeadEquip, Integer> getHeadInventory();

  /**
   * Get the inventory of body equipment.
   * @return the inventory of body equipment
   */
  Map<BodyEquip, Integer> getBodyInventory();

}

package pet;

/**
 * Enum class for head equipments that can be equipped on the pet.
 */
public enum HeadEquip {
  NONE("No Equip",
      "Nothing on head",
      new HealthStatus(0, 0, 0, 0), -1, 0),
  BURGER_HAT("Burger Hat",
      "You can has cheezburger! (Slow the reduction of hunger)",
      new HealthStatus(3, 0, 0, 0), 5, 80),
  SOAP_HAT("Soap Hat",
      "A hat with a soap on it. (Slow the reduction of hygiene)",
      new HealthStatus(0, 3, 0, 0), 5, 80),
  EARPHONE("Earphone",
      "Use this earphone to chat with anyone at any time! (Slow the reduction of social)",
      new HealthStatus(0, 0, 3, 0), 5, 80),
  COFFEE_MASK("Coffee face mask",
      "Smell like real coffee! (Slow the reduction of sleep)",
      new HealthStatus(0, 0, 0, 3), 5, 80);

  private final String name;
  private final String describe;
  private final HealthStatus effect;
  private final int maxDurability;
  private final int cost;

  /**
   * Constructor for HeadEquip.
   * @param name the name of the head equipment
   * @param describe the description of the head equipment
   * @param effect the effect of the head equipment
   * @param maxDurability the maximum durability of the head equipment
   * @param cost the cost of the head equipment
   */
  HeadEquip(String name, String describe, HealthStatus effect, int maxDurability, int cost) {
    this.name = name;
    this.describe = describe;
    this.effect = effect;
    this.maxDurability = maxDurability;
    this.cost = cost;
  }

  /**
   * Get the name of the head equipment.
   * @return the name of the head equipment
   */
  public String getName() {
    return name;
  }

  /**
   * Get the description of the head equipment.
   * @return the description of the head equipment
   */
  public String getDescribe() {
    return describe;
  }

  /**
   * Get the effect of the head equipment.
   * @return the effect of the head equipment
   */
  public HealthStatus getEffect() {
    return effect;
  }

  /**
   * Get the maximum durability of the head equipment.
   * @return the maximum durability of the head equipment
   */
  public int getMaxDurability() {
    return maxDurability;
  }

  /**
   * Get the cost of the head equipment.
   * @return the cost of the head equipment
   */
  public int getCost() {
    return cost;
  }
}

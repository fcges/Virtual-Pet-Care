package pet;

/**
 * Enum class for body equipment that can be equipped on the pet.
 */
public enum BodyEquip {
  NONE("The Emperor's New Clothes",
      "The most beautiful clothes you never have seen.",
      0, 0, -1, 0),
  TALISMAN("Talisman",
      "Protect you from feeling bad. (Lower the threshold for feeling bad)",
      0, 10, 3, 100),
  GOOD_DRESS("Gorgeous dress",
      "Fancy dress makes pet happy! (Lower the threshold for feeling good)",
      10, 0, 10, 500);

  private final String name;
  private final String describe;
  private final int effectGood;
  private final int effectBad;
  private final int maxDurability;
  private final int cost;

  /**
   * Constructor for BodyEquip.
   * @param name the name of the body equipment
   * @param describe the description of the body equipment
   * @param effectGood the effect on good status
   * @param effectBad the effect on bad status
   * @param maxDurability the maximum durability of the body equipment
   * @param cost the cost of the body equipment
   */
  BodyEquip(String name, String describe,
            int effectGood, int effectBad, int maxDurability, int cost) {
    this.name = name;
    this.describe = describe;
    this.effectGood = effectGood;
    this.effectBad = effectBad;
    this.maxDurability = maxDurability;
    this.cost = cost;
  }

  /**
   * Get the name of the body equipment.
   * @return the name of the body equipment
   */
  public String getName() {
    return name;
  }

  /**
   * Get the description of the body equipment.
   * @return the description of the body equipment
   */
  public String getDescribe() {
    return describe;
  }

  /**
   * Get the effect on good status threshold.
   * @return the effect on good status threshold
   */
  public int getEffectGood() {
    return effectGood;
  }

  /**
   * Get the effect on bad status threshold.
   * @return the effect on bad status threshold
   */
  public int getEffectBad() {
    return effectBad;
  }

  /**
   * Get the maximum durability of the body equipment.
   * @return the maximum durability of the body equipment
   */
  public int getMaxDurability() {
    return maxDurability;
  }

  /**
   * Get the cost of the body equipment.
   * @return the cost of the body equipment
   */
  public int getCost() {
    return cost;
  }
}

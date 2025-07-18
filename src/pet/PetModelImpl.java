package pet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class for the pet model.
 */
public class PetModelImpl implements PetInterface {

  private HealthStatus health;
  private final HealthStatus healthChangePerStep = new HealthStatus(5, 5, 5, 5);
  private String name;
  private MoodEnum mood;
  private HeadEquip equipHead;
  private BodyEquip equipBody;
  private int coin;
  private Map<HeadEquip, Integer> headInventory = new HashMap<>();
  private Map<BodyEquip, Integer> bodyInventory = new HashMap<>();
  private final int healthBadThreshold = 25;
  private final int healthGoodThreshold = 80;

  /**
   * Constructor for PetModelImpl.
   * @param name the name of the pet
   * @param health the health status of the pet
   */
  public PetModelImpl(String name, HealthStatus health) {
    this.health = health;
    this.name = name;
    equipHead = HeadEquip.NONE;
    equipBody = BodyEquip.NONE;
    for (HeadEquip head : HeadEquip.values()) {
      headInventory.put(head, 0);
    }
    headInventory.put(HeadEquip.NONE, 1);
    for (BodyEquip body : BodyEquip.values()) {
      bodyInventory.put(body, 0);
    }
    bodyInventory.put(BodyEquip.NONE, 1);
    coin = 20;
    updateMood();
  }

  /**
   * Constructor for PetModelImpl.
   * @param health the health status of the pet
   */
  public PetModelImpl(HealthStatus health) {
    this.health = health;
    equipHead = HeadEquip.NONE;
    equipBody = BodyEquip.NONE;
    for (HeadEquip head : HeadEquip.values()) {
      headInventory.put(head, 0);
    }
    headInventory.put(HeadEquip.NONE, 1);
    for (BodyEquip body : BodyEquip.values()) {
      bodyInventory.put(body, 0);
    }
    bodyInventory.put(BodyEquip.NONE, 1);
    coin = 20;
    updateMood();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public HealthStatus getHealth() {
    return health;
  }

  @Override
  public void setMood(MoodEnum mood) {
    this.mood = mood;
  }

  @Override
  public MoodEnum getMood() {
    return mood;
  }

  @Override
  public void step() {
    // Change Coin by current Health and Mood
    int coinChange = getCoinChange();
    coin += coinChange;
    coin = Math.max(coin, 0);
    // Change health status
    HealthStatus changeHealth = new HealthStatus(
        healthChangePerStep.getHunger() - equipHead.getEffect().getHunger(),
        healthChangePerStep.getHygiene() - equipHead.getEffect().getHygiene(),
        healthChangePerStep.getSocial() - equipHead.getEffect().getSocial(),
        healthChangePerStep.getSleep() - equipHead.getEffect().getSleep());
    double changeMultiplier = getMood() == MoodEnum.HAPPY ? 1.5 : 1;
    health = new HealthStatus(
        Math.max((int) (health.getHunger() - changeHealth.getHunger() * changeMultiplier), 0),
        Math.max((int) (health.getHygiene() - changeHealth.getHygiene() * changeMultiplier), 0),
        Math.max((int) (health.getSocial() - changeHealth.getSocial() * changeMultiplier), 0),
        Math.max((int) (health.getSleep() - changeHealth.getSleep() * changeMultiplier), 0));
    // Reduce durability of equipped items
    reduceDurability();
    // Change Mood
    updateMood();
  }

  /**
   * Calculate the coin change by the current health and mood each step.
   * @return the coin change
   */
  private int getCoinChange() {
    Random rand = new Random();
    int coinChange = 0;
    if (getMood() == MoodEnum.HAPPY || getMood() == MoodEnum.NORMAL) {
      coinChange = (int) ((health.getHunger() + health.getHygiene()
          + health.getSocial() + health.getSleep())
          * (1 + (rand.nextInt(10) - 5) * 0.05)
          / (getMood() == MoodEnum.HAPPY ? 10 : 20));
    } else if (getMood() == MoodEnum.ANGRY) {
      coinChange = (int) ((400 - (health.getHunger() + health.getHygiene() + health.getSocial()
          + health.getSleep())) * (1 + (rand.nextInt(10) - 5) * 0.05) / 15 * -1);
    }
    return coinChange;
  }

  /**
   * Reduce the durability of equipped items.
   */
  private void reduceDurability() {
    if (equipHead.getMaxDurability() != -1) {
      headInventory.put(equipHead, getDurability(equipHead) - 1);
      if (headInventory.get(equipHead) == 0) {
        equipHead = HeadEquip.NONE;
      }
    }
    if (equipBody.getMaxDurability() != -1) {
      bodyInventory.put(equipBody, getDurability(equipBody) - 1);
      if (bodyInventory.get(equipBody) == 0) {
        equipBody = BodyEquip.NONE;
      }
    }
  }

  /**
   * Update the mood of the pet based on the current health status and equipped items.
   */
  private void updateMood() {
    int badThreshold = healthBadThreshold - equipBody.getEffectBad();
    boolean[] isBad = new boolean[4];
    isBad[0] = health.getHunger() < badThreshold;
    isBad[1] = health.getHygiene() < badThreshold;
    isBad[2] = health.getSocial() < badThreshold;
    isBad[3] = health.getSleep() < badThreshold;
    int goodThreshold = healthGoodThreshold - equipBody.getEffectGood();
    boolean[] isGood = new boolean[4];
    isGood[0] = health.getHunger() >= goodThreshold;
    isGood[1] = health.getHygiene() >= goodThreshold;
    isGood[2] = health.getSocial() >= goodThreshold;
    isGood[3] = health.getSleep() >= goodThreshold;
    if (isBad[0] && isBad[1] && isBad[2] && isBad[3]) {
      setMood(MoodEnum.ANGRY);
    } else if (isBad[0]) {
      setMood(MoodEnum.HUNGRY);
    } else if (isBad[3]) {
      setMood(MoodEnum.SLEEPY);
    } else if (isBad[1]) {
      setMood(MoodEnum.UNCOMFORTABLE);
    } else if (isBad[2]) {
      setMood(MoodEnum.BORED);
    } else if (isGood[0] && isGood[1] && isGood[2] && isGood[3]) {
      setMood(MoodEnum.HAPPY);
    } else {
      setMood(MoodEnum.NORMAL);
    }
  }

  @Override
  public void interactWith(Action action) throws IllegalArgumentException {
    if (coin < action.getCost()) {
      throw new IllegalArgumentException("Not enough coin to interact!");
    }
    if (getMood() == MoodEnum.HUNGRY && action != Action.FEED) {
      throw new IllegalArgumentException("Too hungry to do this action!");
    }
    if (getMood() == MoodEnum.SLEEPY && action != Action.SLEEP) {
      throw new IllegalArgumentException("Too sleepy to do this action!");
    }
    if (getMood() == MoodEnum.UNCOMFORTABLE && action == Action.PLAY) {
      throw new IllegalArgumentException("Too dirty to Play!");
    }
    coin -= action.getCost();
    health = new HealthStatus(
        Math.min(health.getHunger() + action.getEffect().getHunger(), 100),
        Math.min(health.getHygiene() + action.getEffect().getHygiene(), 100),
        Math.min(health.getSocial() + action.getEffect().getSocial(), 100),
        Math.min(health.getSleep() + action.getEffect().getSleep(), 100));
    updateMood();
  }

  @Override
  public HeadEquip getEquipHead() {
    return equipHead;
  }

  @Override
  public BodyEquip getEquipBody() {
    return equipBody;
  }

  @Override
  public void setEquipHead(HeadEquip head) throws IllegalArgumentException {
    if (headInventory.get(head) == 0 && head != HeadEquip.NONE) {
      throw new IllegalArgumentException("Don't have this equip in inventory!");
    }
    equipHead = head;
    updateMood();
  }

  @Override
  public void setEquipBody(BodyEquip body) throws IllegalArgumentException {
    if (bodyInventory.get(body) == 0 && body != BodyEquip.NONE) {
      throw new IllegalArgumentException("Don't have this equip in inventory!");
    }
    equipBody = body;
    updateMood();
  }

  @Override
  public void buyEquip(HeadEquip head) throws IllegalArgumentException {
    if (coin < head.getCost()) {
      throw new IllegalArgumentException("Not enough coin to buy the item!");
    }
    if (headInventory.get(head) != 0) {
      throw new IllegalArgumentException("Already have this equip in inventory!");
    }
    coin -= head.getCost();
    headInventory.put(head, head.getMaxDurability());
  }

  @Override
  public void buyEquip(BodyEquip body) throws IllegalArgumentException {
    if (coin < body.getCost()) {
      throw new IllegalArgumentException("Not enough coin to buy the item!");
    }
    if (bodyInventory.get(body) != 0) {
      throw new IllegalArgumentException("Already have this equip in inventory!");
    }
    coin -= body.getCost();
    bodyInventory.put(body, body.getMaxDurability());
  }

  @Override
  public int getDurability(HeadEquip head) {
    return headInventory.get(head);
  }

  @Override
  public int getDurability(BodyEquip body) {
    return bodyInventory.get(body);
  }

  @Override
  public boolean isGameOver() {
    return coin < 5 && getMood() == MoodEnum.ANGRY;
  }

  @Override
  public void setCoin(int coin) {
    this.coin = coin;
  }

  @Override
  public int getCoin() {
    return coin;
  }

  @Override
  public Map<HeadEquip, Integer> getHeadInventory() {
    return headInventory;
  }

  @Override
  public Map<BodyEquip, Integer> getBodyInventory() {
    return bodyInventory;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    PetModelImpl that = (PetModelImpl) obj;
    return coin == that.coin && health.equals(that.health)
        && mood == that.mood && equipHead == that.equipHead && equipBody == that.equipBody
        && headInventory.equals(that.headInventory) && bodyInventory.equals(that.bodyInventory);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(health, mood, equipHead, equipBody, coin, headInventory,
        bodyInventory);
  }
}

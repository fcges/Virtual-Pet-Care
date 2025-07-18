package pet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for PetModelImpl.
 */
public class PetModelImplTest {

  PetInterface pet;

  /**
   * We create a pet for testing.
   */
  @Before
  public void setUp() {
    pet = new PetModelImpl("Test", new HealthStatus(50, 50, 50, 50));
  }

  /**
   * We test the getName method work as expected.
   */
  @Test
  public void getName() {
    assertEquals("Test", pet.getName());
  }

  /**
   * We test the getHealth method work as expected.
   */
  @Test
  public void getHealth() {
    assertEquals(new HealthStatus(50, 50, 50, 50).toString(), pet.getHealth().toString());
  }

  /**
   * We test the setMood method work as expected.
   */
  @Test
  public void setMood() {
    pet.setMood(MoodEnum.HAPPY);
    assertEquals(MoodEnum.HAPPY, pet.getMood());
    pet.setMood(MoodEnum.NORMAL);
    assertEquals(MoodEnum.NORMAL, pet.getMood());
  }

  /**
   * We test the setMood method work as expected.
   */
  @Test
  public void getMood() {
    pet.setMood(MoodEnum.HUNGRY);
    assertEquals(MoodEnum.HUNGRY, pet.getMood());
    pet.setMood(MoodEnum.ANGRY);
    assertEquals(MoodEnum.ANGRY, pet.getMood());
  }

  /**
   * We test the step method work as expected.
   */
  @Test
  public void step() {
    pet.step();
    assertEquals(45, pet.getHealth().getHunger());
    assertEquals(45, pet.getHealth().getHygiene());
    assertEquals(45, pet.getHealth().getSocial());
    assertEquals(45, pet.getHealth().getSleep());
    for (int i = 0; i < 9; i++) {
      pet.step();
    }
    assertEquals(0, pet.getHealth().getHunger());
    assertEquals(0, pet.getHealth().getHygiene());
    assertEquals(0, pet.getHealth().getSocial());
    assertEquals(0, pet.getHealth().getSleep());
    assertTrue(pet.isGameOver());
  }

  /**
   * We test the pet will get more coin when it is happy.
   */
  @Test
  public void stepCoinGet() {
    pet = new PetModelImpl("Test", new HealthStatus(80, 80, 80, 80));
    pet.setCoin(0);
    pet.step();
    int coinHappy = pet.getCoin();
    pet.step();
    int coinNormal = pet.getCoin() - coinHappy;
    assertTrue(coinHappy > coinNormal);
  }

  /**
   * We test the step method work as expected.
   */
  @Test
  public void interactWith() {
    pet.setCoin(40);
    pet.interactWith(Action.FEED);
    assertEquals(70, pet.getHealth().getHunger());
    pet.interactWith(Action.SLEEP);
    assertEquals(70, pet.getHealth().getSleep());
    pet.interactWith(Action.PLAY);
    assertEquals(70, pet.getHealth().getSocial());
    pet.interactWith(Action.CLEAN);
    assertEquals(70, pet.getHealth().getHygiene());
    pet.interactWith(Action.FEED);
    pet.interactWith(Action.SLEEP);
    pet.interactWith(Action.PLAY);
    pet.interactWith(Action.CLEAN);
    assertEquals(MoodEnum.HAPPY, pet.getMood());
  }

  /**
   * We test the step method throw exception when don't have enough coin to interact.
   */
  @Test(expected = IllegalArgumentException.class)
  public void interactNotEnoughCoin() {
    pet.setCoin(0);
    pet.interactWith(Action.FEED);
  }

  /**
   * We test the step method throw exception when user want to play but the pet is too hungry.
   */
  @Test(expected = IllegalArgumentException.class)
  public void interactPlayTooHungry() {
    pet.setMood(MoodEnum.HUNGRY);
    pet.interactWith(Action.PLAY);
  }

  /**
   * We test the step method throw exception when user want to play but the pet is too sleepy.
   */
  @Test(expected = IllegalArgumentException.class)
  public void interactPlayTooSleepy() {
    pet.setMood(MoodEnum.SLEEPY);
    pet.interactWith(Action.PLAY);
  }

  /**
   * We test the step method throw exception when user want to play but the pet is too dirty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void interactPlayTooDirty() {
    pet.setMood(MoodEnum.UNCOMFORTABLE);
    pet.interactWith(Action.PLAY);
  }

  /**
   * We test the getEquipHead method work as expected.
   */
  @Test
  public void getEquipHead() {
    assertEquals(HeadEquip.NONE, pet.getEquipHead());
    pet.setCoin(500);
    pet.buyEquip(HeadEquip.BURGER_HAT);
    pet.setEquipHead(HeadEquip.BURGER_HAT);
    assertEquals(HeadEquip.BURGER_HAT, pet.getEquipHead());
  }

  /**
   * We test the getEquipBody method work as expected.
   */
  @Test
  public void getEquipBody() {
    assertEquals(BodyEquip.NONE, pet.getEquipBody());
    pet.setCoin(500);
    pet.buyEquip(BodyEquip.GOOD_DRESS);
    pet.setEquipBody(BodyEquip.GOOD_DRESS);
    assertEquals(BodyEquip.GOOD_DRESS, pet.getEquipBody());
  }

  /**
   * We test the setEquipHead method work as expected.
   */
  @Test
  public void setEquipHead() {
    pet.setCoin(500);
    pet.buyEquip(HeadEquip.COFFEE_MASK);
    pet.setEquipHead(HeadEquip.COFFEE_MASK);
    assertEquals(HeadEquip.COFFEE_MASK, pet.getEquipHead());
  }

  /**
   * We test the setEquipHead method throw exception when the equipment is not in the inventory.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setEquipHeadNotInInventory() {
    pet.setEquipHead(HeadEquip.BURGER_HAT);
  }

  /**
   * We test the setEquipBody method work as expected.
   */
  @Test
  public void setEquipBody() {
    pet.setCoin(500);
    pet.buyEquip(BodyEquip.TALISMAN);
    pet.setEquipBody(BodyEquip.TALISMAN);
    assertEquals(BodyEquip.TALISMAN, pet.getEquipBody());
  }

  /**
   * We test the setEquipBody method throw exception when the equipment is not in the inventory.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setEquipBodyNotInInventory() {
    pet.setEquipBody(BodyEquip.GOOD_DRESS);
  }

  /**
   * We test the buyEquip method work as expected.
   */
  @Test
  public void testBuyEquip() {
    pet.setCoin(500);
    pet.buyEquip(BodyEquip.GOOD_DRESS);
    assertEquals(BodyEquip.GOOD_DRESS.getMaxDurability(), pet.getDurability(BodyEquip.GOOD_DRESS));
    pet.setCoin(80);
    pet.buyEquip(HeadEquip.COFFEE_MASK);
    assertEquals(HeadEquip.COFFEE_MASK.getMaxDurability(),
        pet.getDurability(HeadEquip.COFFEE_MASK));
  }

  /**
   * We test the buyEquip method throw exception when the pet does not have enough coins.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBuyEquipNotEnoughCoin() {
    pet.setCoin(0);
    pet.buyEquip(BodyEquip.GOOD_DRESS);
  }

  /**
   * We test the getDurability method work as expected.
   */
  @Test
  public void getDurability() {
    pet.setCoin(580);
    assertEquals(0, pet.getDurability(HeadEquip.BURGER_HAT));
    pet.buyEquip(HeadEquip.BURGER_HAT);
    assertEquals(HeadEquip.BURGER_HAT.getMaxDurability(), pet.getDurability(HeadEquip.BURGER_HAT));
    assertEquals(0, pet.getDurability(BodyEquip.GOOD_DRESS));
    pet.buyEquip(BodyEquip.GOOD_DRESS);
    assertEquals(BodyEquip.GOOD_DRESS.getMaxDurability(), pet.getDurability(BodyEquip.GOOD_DRESS));
  }

  /**
   * We test the isGameOver method work as expected.
   */
  @Test
  public void isGameOver() {
    pet.setCoin(4);
    pet.setMood(MoodEnum.ANGRY);
    assertTrue(pet.isGameOver());
    pet.setCoin(5);
    assertFalse(pet.isGameOver());
    pet.setCoin(4);
    pet.setMood(MoodEnum.HAPPY);
    assertFalse(pet.isGameOver());
  }

  /**
   * We test the getCoin method work as expected.
   */
  @Test
  public void getCoin() {
    pet.setCoin(100);
    assertEquals(100, pet.getCoin());
  }
}
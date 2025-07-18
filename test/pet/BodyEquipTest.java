package pet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for BodyEquip Enum.
 */
public class BodyEquipTest {

  /**
   * We test the getName method work as expected.
   */
  @Test
  public void getName() {
    assertEquals("Talisman", BodyEquip.TALISMAN.getName());
  }

  /**
   * We test the getDescribe method work as expected.
   */
  @Test
  public void getDescribe() {
    assertEquals("Protect you from feeling bad. (Lower the threshold for feeling bad)",
        BodyEquip.TALISMAN.getDescribe());
  }

  /**
   * We test the getEffectGood method work as expected.
   */
  @Test
  public void getEffectGood() {
    assertEquals(0, BodyEquip.TALISMAN.getEffectGood());
  }

  /**
   * We test the getEffectBad method work as expected.
   */
  @Test
  public void getEffectBad() {
    assertEquals(10, BodyEquip.TALISMAN.getEffectBad());
  }

  /**
   * We test the getMaxDurability method work as expected.
   */
  @Test
  public void getMaxDurability() {
    assertEquals(3, BodyEquip.TALISMAN.getMaxDurability());
  }

  /**
   * We test the getCost method work as expected.
   */
  @Test
  public void getCost() {
    assertEquals(100, BodyEquip.TALISMAN.getCost());
  }
}
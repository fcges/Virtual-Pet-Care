package pet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for HeadEquip Enum.
 */
public class HeadEquipTest {

  /**
   * We test the getName method work as expected.
   */
  @Test
  public void getName() {
    assertEquals("Soap Hat", HeadEquip.SOAP_HAT.getName());
  }

  /**
   * We test the getDescribe method work as expected.
   */
  @Test
  public void getDescribe() {
    assertEquals("A hat with a soap on it. (Slow the reduction of hygiene)",
        HeadEquip.SOAP_HAT.getDescribe());
  }

  /**
   * We test the getEffect method work as expected.
   */
  @Test
  public void getEffect() {
    assertEquals(new HealthStatus(0, 3, 0, 0).toString(),
        HeadEquip.SOAP_HAT.getEffect().toString());
  }

  /**
   * We test the getMaxDurability method work as expected.
   */
  @Test
  public void getMaxDurability() {
    assertEquals(5, HeadEquip.SOAP_HAT.getMaxDurability());
  }

  /**
   * We test the getCost method work as expected.
   */
  @Test
  public void getCost() {
    assertEquals(80, HeadEquip.SOAP_HAT.getCost());
  }
}
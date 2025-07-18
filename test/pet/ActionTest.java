package pet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for Action Enum.
 */
public class ActionTest {

  /**
   * We test the getName method work as expected.
   */
  @Test
  public void getEffect() {
    assertEquals(new HealthStatus(20, 0, 0, 0).toString(), Action.FEED.getEffect().toString());
  }

  /**
   * We test the getCost method work as expected.
   */
  @Test
  public void getCost() {
    assertEquals(5, Action.FEED.getCost());
  }
}
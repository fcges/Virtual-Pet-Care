package pet;

/**
 * Class for the health status of the pet.
 */
public class HealthStatus {

  private final int hunger;
  private final int hygiene;
  private final int social;
  private final int sleep;

  /**
   * Constructor for HealthStatus.
   * @param hunger the hunger level of the pet
   * @param hygiene the hygiene level of the pet
   * @param social the social level of the pet
   * @param sleep the sleep level of the pet
   */
  public HealthStatus(int hunger, int hygiene, int social, int sleep) {
    this.hunger = hunger;
    this.hygiene = hygiene;
    this.social = social;
    this.sleep = sleep;
  }

  /**
   * Get the hunger level of the pet.
   * @return the hunger level of the pet
   */
  public int getHunger() {
    return hunger;
  }

  /**
   * Get the hygiene level of the pet.
   * @return the hygiene level of the pet
   */
  public int getHygiene() {
    return hygiene;
  }

  /**
   * Get the social level of the pet.
   * @return the social level of the pet
   */
  public int getSocial() {
    return social;
  }

  /**
   * Get the sleep level of the pet.
   * @return the sleep level of the pet
   */
  public int getSleep() {
    return sleep;
  }

  @Override
  public String toString() {
    return "HealthStatus{"
        + "hunger=" + hunger
        + ", hygiene=" + hygiene
        + ", social=" + social
        + ", sleep=" + sleep
        + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    HealthStatus that = (HealthStatus) obj;
    return hunger == that.hunger
        && hygiene == that.hygiene
        && social == that.social
        && sleep == that.sleep;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(hunger, hygiene, social, sleep);
  }
}

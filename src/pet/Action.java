package pet;

/**
 * Enum class for actions that can be performed on the pet.
 */
public enum Action {
  FEED(new HealthStatus(20, 0, 0, 0), 5),
  PLAY(new HealthStatus(0, 0, 20, 0), 5),
  CLEAN(new HealthStatus(0, 20, 0, 0), 5),
  SLEEP(new HealthStatus(0, 0, 0, 20), 5);

  private final HealthStatus effect;
  private final int cost;

  /**
   * Constructor for Action.
   * @param effect the effect of the action
   * @param cost the cost of the action
   */
  Action(HealthStatus effect, int cost) {
    this.effect = effect;
    this.cost = cost;
  }

  /**
   * Get the effect of the action.
   * @return the effect of the action
   */
  public HealthStatus getEffect() {
    return effect;
  }

  /**
   * Get the cost of the action.
   * @return the cost of the action
   */
  public int getCost() {
    return cost;
  }
}

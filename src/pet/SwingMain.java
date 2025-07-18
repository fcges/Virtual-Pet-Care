package pet;

import controller.PetController;
import view.PetView;

/**
 * Main class for the pet game.
 * It initializes the model, view, and controller.
 */
public class SwingMain {

  /**
   * Main method to start the game.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PetView view = new PetView();
    PetInterface model = new PetModelImpl(new HealthStatus(50, 50, 50, 50));
    PetController controller = new PetController();
    controller.playGame(view, model);
  }
}

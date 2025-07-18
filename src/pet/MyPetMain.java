package pet;

import java.util.Scanner;

/**
 * The MyPetMain class is the entry point for the Virtual Pet Care application.
 * It allows users to interact with a virtual pet by issuing commands to
 * play, feed, clean, put to sleep, or advance the pet's state.
 */
public class MyPetMain {

  /**
   * The main method is the entry point of the Virtual Pet Care application.
   * It initializes the pet and scanner, prints the menu, and processes user commands in a loop.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to Virtual Pet Care! What is your pet's name?");
    String name = scanner.next();
    PetInterface pet = new PetModelImpl(name, new HealthStatus(50, 50, 50, 50));


    printMenu();

    while (scanner.hasNext() && !pet.isGameOver()) {
      try {
        String command = scanner.next();
        int times;

        switch (command) {
          case "p":
            times = scanner.nextInt();
            for (int i = 0; i < times; i++) {
              pet.interactWith(Action.PLAY);
            }
            break;
          case "f":
            times = scanner.nextInt();
            for (int i = 0; i < times; i++) {
              pet.interactWith(Action.FEED);
            }
            break;
          case "c":
            times = scanner.nextInt();
            for (int i = 0; i < times; i++) {
              pet.interactWith(Action.CLEAN);
            }
            break;
          case "s":
            times = scanner.nextInt();
            for (int i = 0; i < times; i++) {
              pet.interactWith(Action.SLEEP);
            }
            break;
          case "a":
            times = scanner.nextInt();
            for (int i = 0; i < times; i++) {
              pet.step();
            }
            break;
          case "x":
            times = scanner.nextInt();
            runSimulation(pet, times);
            break;
          case "d":
            int equip = printEquipMenu(pet);
            int item = scanner.nextInt();
            if (item == 999) {
              printMenu();
              continue;
            }
            if (item < 0 || item > equip) {
              System.out.println("Invalid item number.");
              continue;
            }
            if (item < HeadEquip.values().length) {
              if (pet.getDurability(HeadEquip.values()[item]) != 0) {
                pet.setEquipHead(HeadEquip.values()[item]);
              } else {
                System.out.println("Don't have this equip in inventory.");
              }
            } else {
              if (pet.getDurability(BodyEquip.values()[item - HeadEquip.values().length]) != 0) {
                pet.setEquipBody(BodyEquip.values()[item - HeadEquip.values().length]);
              } else {
                System.out.println("Don't have this equip in inventory.");
              }
            }
            break;
          case "b":
            int shop = printShopMenu(pet);
            int buy = scanner.nextInt();
            if (buy == 999) {
              printMenu();
              continue;
            }
            if (buy < 0 || buy > shop) {
              System.out.println("Invalid item number.");
              continue;
            }
            if (buy < HeadEquip.values().length - 1) {
              if (pet.getDurability(HeadEquip.values()[buy + 1]) == 0) {
                pet.buyEquip(HeadEquip.values()[buy + 1]);
              } else {
                System.out.println("Sold out.");
              }
            } else {
              if (pet.getDurability(BodyEquip.values()[buy - HeadEquip.values().length + 2]) == 0) {
                pet.buyEquip(BodyEquip.values()[buy - HeadEquip.values().length + 2]);
              } else {
                System.out.println("Sold out.");
              }
            }
            break;
          default:
            System.out.println("Unknown command.");
            continue;
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
      System.out.println("-------" + pet.getName() + "-------");
      System.out.println("Pet Status: " + pet.getHealth());
      System.out.println("Current Coin: " + pet.getCoin());
      System.out.println("Head Equip: " + pet.getEquipHead().getName());
      System.out.println("Body Equip: " + pet.getEquipBody().getName());
      System.out.println("Current Mood: " + pet.getMood());
      printMenu();
    }
  }

  /**
   * Prints the menu of commands for the Virtual Pet Care application.
   */
  private static void printMenu() {
    System.out.println("Type a command:");
    System.out.println("p n  - Play n times");
    System.out.println("f n  - Feed n items of food");
    System.out.println("c n  - Clean n times");
    System.out.println("s n  - Put pet to sleep n times");
    System.out.println("a n  - Advance pet state n steps");
    System.out.println("x n  - Run simulation for n steps");
    System.out.println("d  - Dress up your pet");
    System.out.println("b  - Open shop to buy items");
  }

  private static int printShopMenu(PetInterface pet) {
    System.out.println("Welcome to the shop! Type a number to buy items:");
    System.out.println("You have " + pet.getCoin() + " coins.");
    System.out.println("Head Equip:");
    int i = 0;
    for (HeadEquip head : HeadEquip.values()) {
      if (head == HeadEquip.NONE) {
        continue;
      }
      System.out.println(i + ". " + head.getName() + " - " + head.getDescribe()
          + (pet.getDurability(head) == 0 ? (" for " + head.getCost() + " coins") : " Sold out"));
      i++;
    }
    System.out.println("Body Equip:");
    for (BodyEquip body : BodyEquip.values()) {
      if (body == BodyEquip.NONE) {
        continue;
      }
      System.out.println(i + ". " + body.getName() + " - " + body.getDescribe()
          + (pet.getDurability(body) == 0 ? (" for " + body.getCost() + " coins") : " Sold out"));
      i++;
    }
    System.out.println("999. Exit");
    return i - 1;
  }

  private static int printEquipMenu(PetInterface pet) {
    System.out.println("Dress up " + pet.getName() + "! Type a number to equip items:");
    System.out.println("Head Equip: " + pet.getEquipHead().getName());
    System.out.println("Body Equip: " + pet.getEquipBody().getName());
    System.out.println();
    System.out.println("Inventory:");
    int i = 0;
    for (HeadEquip head : HeadEquip.values()) {
      System.out.println(i + ". "
          + (pet.getDurability(head) != 0 ? (head.getName() + " - " + head.getDescribe()
              + (head != HeadEquip.NONE ? (" Durability: " + pet.getDurability(head)) : "")) :
              "No Item"));
      i++;
    }
    for (BodyEquip body : BodyEquip.values()) {
      System.out.println(i + ". "
          + (pet.getDurability(body) != 0 ? (body.getName() + " - " + body.getDescribe()
              + (body != BodyEquip.NONE ? (" Durability: " + pet.getDurability(body)) : "")) :
              "No Item"));
      i++;
    }
    System.out.println("999. Cancel");
    return i - 1;
  }

  /**
   * Runs a simulation that interacts with the pet for a significant number of steps.
   *
   * @param pet   the pet to interact with
   * @param steps the number of steps to simulate
   */
  private static void runSimulation(PetInterface pet, int steps) {
    for (int i = 0; i < steps; i++) {
      pet.step();
      if (i % 5 == 0) {
        pet.interactWith(Action.FEED);
      }
      if (i % 10 == 0) {
        pet.interactWith(Action.PLAY);
      }
      if (i % 15 == 0) {
        pet.interactWith(Action.CLEAN);
      }
      if (i % 20 == 0) {
        pet.interactWith(Action.SLEEP);
      }
    }
  }
}
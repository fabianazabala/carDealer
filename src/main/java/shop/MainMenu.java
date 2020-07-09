package shop;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import shop.cars.Car;
import shop.cars.Workshop;
import shop.database.CarService;
import shop.database.ClientService;
import shop.persons.Client;
import shop.persons.Player;
import shop.transactions.Transaction;

public class MainMenu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    CarService carService = new CarService();
    ClientService clientService = new ClientService();

    Player player = new Player();
    System.out.println("Hi :) Welcome to the car dealer game! Please enter your name: \n");
    player.name = in.nextLine();

    System.out.println("Please enter the amount of cash you'd like to play with\n");
    player.cash = in.nextDouble();
    double initialCash = player.cash;
    System.out.println("Welcome " + player.name + " you've been granted an initial of " + player.cash + " in cash\n");
    System.out.println("You'll win the game when your total cash is " + player.cash * 2 + " or more!");
    int nextOption = 0;
    while (nextOption != 99) {
      if (player.cash >= (initialCash * 2)) {
        System.out.println("Congratulations! You've doubled your cash, would you like to continue playing? \n");
        System.out.println("Press 1 for Yes, Press 2 for No");
        int continueGame = in.nextInt();
        if (continueGame == 2) {
          break;
        }
      }
      printMainMenuOptions();
      nextOption = in.nextInt();
      switch (nextOption) {
        case 1 -> listAvailableCars(carService);
        case 2 -> buyCar(in, player, carService);
        case 3 -> listMyCars(player);
        case 4 -> repairCar(in, carService, player);
        case 5 -> viewPotentialClients(clientService, player);
        case 6 -> sellCarToClient(in, player, carService, clientService);
        case 7 -> buyAdvertisement(clientService, player, in);
        case 8 -> checkAccountBalance(player);
        case 9 -> checkTransactionHistory(player);
        case 10 -> checkCarRepairHistory(in, carService);
        case 11 -> checkCarExpenses(in, carService);
        default -> System.out.println("That's an invalid option! Try again");
      }
    }
    System.out.println("Thanks for playing :) See you!");
  }

  private static void checkCarExpenses(Scanner in, CarService carService) {
    double total = 0;
    System.out.println("Enter car's id:\n");
    long carId = in.nextLong();

    for (Transaction transaction : carService.repairHistory(carId)) {
      total += transaction.value;
    }

    System.out.println("You've spent a total of " + total + " repairing this car");
  }

  private static void checkCarRepairHistory(Scanner in, CarService carService) {
    System.out.println("Enter car's id:\n");
    long carId = in.nextLong();

    System.out.println("Repair history is: ");
    for (Transaction transaction : carService.repairHistory(carId)) {
      System.out.println("\n");
      System.out.println(transaction);
    }
  }

  private static void checkTransactionHistory(Player player) {
    System.out.println("Your transactions are: ");
    for (Transaction transaction : player.transactions) {
      System.out.println("\n");
      System.out.println(transaction);
    }
  }

  private static void checkAccountBalance(Player player) {
    System.out.println("Your current balance is: " + player.cash);
  }

  private static void buyAdvertisement(ClientService clientService, Player player, Scanner in) {
    System.out
        .println("How much you'd like to spend in advertisement? The more you invest the more clients you'll get");
    double amount = in.nextDouble();
    clientService.buyAdvertisement(player, amount);
  }

  private static void sellCarToClient(Scanner in,
                                      Player player,
                                      CarService carService,
                                      ClientService clientService) {
    System.out.println("Enter buyer's id:\n");
    long buyerId = in.nextLong();
    Client buyer = clientService.getClient(buyerId);

    List<Car> potentialCars = player.cars.stream().filter(buyer::isInterested).collect(Collectors.toList());
    if (potentialCars.isEmpty()) {
      System.out.println("This client is not interested in any of your cars");
      return;
    }

    System.out.println("This buyer is interested in buy cars: ");
    for (Car car : potentialCars) {
      System.out.println("\n");
      System.out.println(car);
    }

    System.out.println("\nEnter id of car to sell:\n");
    long carId = in.nextLong();
    Car toSell = carService.getCar(carId);
    carService.sellCar(toSell, buyer, player);
  }

  private static void viewPotentialClients(ClientService clientService, Player player) {
    System.out.println("List of potential clients: ");
    for (Client client : clientService.potentialClients()) {
      boolean isInterested = player.cars.stream().anyMatch(client::isInterested);
      if (isInterested) {
        System.out.println("\n");
        System.out.println(client);
      }
    }
  }

  private static void repairCar(Scanner in, CarService carService, Player player) {
    System.out.println("Please select which workshop you'd like to use:\n");
    for (Workshop workshop : carService.listAvailableWorkshops()) {
      System.out.println("\n");
      System.out.println(workshop);
    }

    long workshopId = in.nextLong();

    System.out.println("Please enter id of the car to repair: ");
    long carId = in.nextLong();

    System.out.println("---------------------------------");
    System.out.println("Please select what you'd like to repair: ");
    System.out.println("1. Brakes");
    System.out.println("2. Car Body");
    System.out.println("3. Dampers");
    System.out.println("4. Engine");
    System.out.println("5. Gearbox");
    System.out.println("6. All damaged parts");
    System.out.println("---------------------------------");
    int repairOption = in.nextInt();
    switch (repairOption) {
      case 1 -> carService.repairBrakes(player, workshopId, carId);
      case 2 -> carService.repairCarBody(player, workshopId, carId);
      case 3 -> carService.repairDampers(player, workshopId, carId);
      case 4 -> carService.repairEngine(player, workshopId, carId);
      case 5 -> carService.repairGearbox(player, workshopId, carId);
      case 6 -> carService.repairDamagedParts(player, workshopId, carId);
    }
  }

  private static void listMyCars(Player player) {
    System.out.println("You have the following cars: \n");
    for (Car car : player.cars) {
      System.out.println("\n");
      System.out.println(car);
    }
  }

  private static void buyCar(Scanner in, Player player, CarService carService) {
    System.out.println("Please enter car id: ");
    long carId = in.nextLong();
    carService.buyCar(player, carId);
  }

  private static void listAvailableCars(CarService carService) {
    System.out.println("The following cars are available: \n");
    for (Car car : carService.listAvailableCars()) {
      System.out.println("\n");
      System.out.println(car);
    }
  }

  public static void printMainMenuOptions() {
    System.out.println("---------------------------------");
    System.out.println("Choose your next move: \n");
    System.out.println("1. List the cars you can buy");
    System.out.println("2. Buy a car");
    System.out.println("3. List my cars");
    System.out.println("4. Repair a car");
    System.out.println("5. View the potential clients");
    System.out.println("6. Sell a car to a potential client");
    System.out.println("7. Buy an advertisement");
    System.out.println("8. Check your account balance");
    System.out.println("9. Check transactions history");
    System.out.println("10. Check car repair history");
    System.out.println("11. Check how much you've spent fixing and cleaning your cars");
    System.out.println("99. Quit the game");
    System.out.println("---------------------------------");
  }
}

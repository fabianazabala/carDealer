package shop;

import java.util.Scanner;
import shop.cars.Car;
import shop.clients.Client;
import shop.database.CarService;
import shop.database.ClientService;
import shop.players.Player;
import shop.transactions.Transaction;

public class MainMenu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    CarService carService = new CarService();
    ClientService clientService = new ClientService();

    Player player = new Player();

    int nextOption = 0;
    while (nextOption != 99) {
      printMainMenuOptions();
      nextOption = in.nextInt();
      switch (nextOption) {
        case 1:
          listAvailableCars(carService);
          break;
        case 2:
          buyCar(in, carService);
          break;
        case 3:
          listMyCars(player);
          break;
        case 4:
          repairCar(in, carService);
          break;
        case 5:
          viewPotentialClients(clientService);
          break;
        case 6:
          sellCarToClient(in, carService, clientService);
          break;
        case 7:
          buyAdvertisement(clientService);
          break;
        case 8:
          checkAccountBalance(player);
          break;
        case 9:
          checkTransactionHistory(player);
          break;
        case 10:
          checkCarRepairHistory(in, carService);
          break;
        case 11:
          checkCarExpenses(in, carService);
          break;
        default:
          System.out.println("That's an invalid option! Try again");
          break;
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

  private static void buyAdvertisement(ClientService clientService) {
    clientService.buyAdvertisement();
  }

  private static void sellCarToClient(Scanner in,
                                      CarService carService,
                                      ClientService clientService) {
    System.out.println("Enter buyer's id:\n");
    long buyerId = in.nextLong();
    Client buyer = clientService.getClient(buyerId);
    System.out.println("Enter car's id:\n");
    long carId = in.nextLong();
    Car toSell = carService.getCar(carId);
    carService.sellCar(toSell, buyer);
  }

  private static void viewPotentialClients(ClientService clientService) {
    System.out.println("List of potential clients: ");
    for (Client client : clientService.potentialClients()) {
      System.out.println("\n");
      System.out.println(client);
    }
  }

  private static void repairCar(Scanner in, CarService carService) {
    System.out.println("Please enter car id: ");
    long carId = in.nextLong();
    carService.repairCar(carId);
  }

  private static void listMyCars(Player player) {
    System.out.println("You have the following cars: \n");
    for (Car car : player.cars) {
      System.out.println("\n");
      System.out.println(car);
    }
  }

  private static void buyCar(Scanner in, CarService carService) {
    System.out.println("Please enter car id: ");
    long carId = in.nextLong();
    carService.buyCar(carId);
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

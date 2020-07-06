package shop.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shop.cars.Car;
import shop.cars.PassangerCar;
import shop.cars.Workshop;
import shop.persons.Client;
import shop.persons.Player;
import shop.stores.JanuszCars;
import shop.stores.MarianAuto;
import shop.stores.PPHUAdrian;
import shop.transactions.Transaction;

public class CarService {

  private Map<Long, List<Transaction>> carRepairHistory = new HashMap<>();
  private Map<Long, Car> cars = new HashMap<>();
  private Map<Long, Workshop> availableWorkshops = Map.of(
      1L, new JanuszCars(1L),
      2L, new MarianAuto(2L),
      3L, new PPHUAdrian(3L)
  );

  public CarService() {
    cars.put(1L, new PassangerCar(1L, null, 100, "Ford", 10000, "white", "standard"));
    cars.put(2L, new PassangerCar(2L, null, 200, "Toyota", 5000, "black", "standard"));
    cars.put(3L, new PassangerCar(3L, null, 150, "Mazda", 7500, "black", "standard"));
    cars.put(4L, new PassangerCar(4L, null, 250, "Chevrolet", 7500, "red", "standard"));
    cars.put(5L, new PassangerCar(5L, null, 2500, "Audi", 1500, "yellow", "premium"));
    cars.put(6L, new PassangerCar(6L, null, 5000, "Mercedes", 7500, "white", "premium"));
    cars.put(7L, new PassangerCar(7L, null, 25000, "Ferrari", 7500, "red", "premium"));
  }

  public List<Car> listAvailableCars() {
    //from database as well
    return new ArrayList<>(cars.values());
  }

  public Car getCar(long carId) {
    return cars.get(carId);
  }

  public void sellCar(Car toSell, Client buyer, Player player) {
    if (!toSell.owner.equals(player)) {
      System.out.println("You cannot sell a car that's not yours! Don't steal :(");
    } else if (buyer.cash >= toSell.value) {
      player.cash += toSell.value;
      player.cars.remove(toSell);
      buyer.cash -= toSell.value;
      toSell.owner = buyer;
      buyer.cars.add(toSell);

      Transaction transaction = new Transaction(toSell.value, "Sold car: " + toSell);
      player.transactions.add(transaction);
      System.out.println("Congratulations! You've sold the following car: \n" + toSell);
    } else {
      System.out.println("This client doesn't have enough money to buy your car :(");
    }
  }

  public void buyCar(Player player, long carId) {
    Car toBuy = getCar(carId);
    if (player.cash >= toBuy.value) {
      player.cash -= toBuy.value;
      player.cars.add(toBuy);
      toBuy.owner = player;
      Transaction transaction = new Transaction(-toBuy.value, "Bought car: " + toBuy);
      player.transactions.add(transaction);
      System.out.println("Congratulations! You've bought the following car: \n" + toBuy);
    } else {
      System.out.println("You don't have money to buy this car! >:( Try with another one");
    }
  }

  public void repairBrakes(Player player, long workshopId, long carId) {
    double initialCash = player.cash;

    Workshop workshop = availableWorkshops.get(workshopId);
    workshop.repairBrakes(player, getCar(carId));
    double afterRepairCash = player.cash;

    Transaction transaction = new Transaction(-(initialCash - afterRepairCash), "Repair brakes");
    player.transactions.add(transaction);
    carRepairHistory.putIfAbsent(carId, new ArrayList<>());
    carRepairHistory.get(carId).add(transaction);
  }

  public void repairCarBody(Player player, long workshopId, long carId) {
    double initialCash = player.cash;
    Workshop workshop = availableWorkshops.get(workshopId);
    workshop.repairCarBody(player, getCar(carId));
    double afterRepairCash = player.cash;

    Transaction transaction = new Transaction(-(initialCash - afterRepairCash), "Repair car body");
    player.transactions.add(transaction);
    carRepairHistory.putIfAbsent(carId, new ArrayList<>());
    carRepairHistory.get(carId).add(transaction);
  }

  public void repairDampers(Player player, long workshopId, long carId) {
    double initialCash = player.cash;
    Workshop workshop = availableWorkshops.get(workshopId);
    workshop.repairDampers(player, getCar(carId));
    double afterRepairCash = player.cash;

    Transaction transaction = new Transaction(-(initialCash - afterRepairCash), "Repair dampers");
    player.transactions.add(transaction);
    carRepairHistory.putIfAbsent(carId, new ArrayList<>());
    carRepairHistory.get(carId).add(transaction);
  }

  public void repairEngine(Player player, long workshopId, long carId) {
    double initialCash = player.cash;
    Workshop workshop = availableWorkshops.get(workshopId);
    workshop.repairEngine(player, getCar(carId));
    double afterRepairCash = player.cash;

    Transaction transaction = new Transaction(-(initialCash - afterRepairCash), "Repair engine");
    player.transactions.add(transaction);
    carRepairHistory.putIfAbsent(carId, new ArrayList<>());
    carRepairHistory.get(carId).add(transaction);
  }

  public void repairGearbox(Player player, long workshopId, long carId) {
    double initialCash = player.cash;
    Workshop workshop = availableWorkshops.get(workshopId);
    workshop.repairGearbox(player, getCar(carId));
    double afterRepairCash = player.cash;

    Transaction transaction = new Transaction(-(initialCash - afterRepairCash), "Repair gearbox");
    player.transactions.add(transaction);
    carRepairHistory.putIfAbsent(carId, new ArrayList<>());
    carRepairHistory.get(carId).add(transaction);
  }

  public Collection<Workshop> listAvailableWorkshops() {
    return availableWorkshops.values();
  }

  public List<Transaction> repairHistory(long carId) {
    return carRepairHistory.get(carId);
  }
}

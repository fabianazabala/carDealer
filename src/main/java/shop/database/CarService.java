package shop.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import shop.cars.Car;
import shop.cars.CarPartStatus;
import shop.cars.PassangerCar;
import shop.cars.Truck;
import shop.persons.Client;
import shop.persons.Player;
import shop.stores.JanuszCars;
import shop.stores.MarianAuto;
import shop.stores.PPHUAdrian;
import shop.stores.Workshop;
import shop.transactions.Transaction;

public class CarService {

  private Map<Long, List<Transaction>> carRepairHistory = new HashMap<>();
  private Map<Long, Car> cars;
  private Map<Long, Workshop> availableWorkshops = Map.of(
      1L, new JanuszCars(1L),
      2L, new MarianAuto(2L),
      3L, new PPHUAdrian(3L)
  );

  public CarService() {
    cars = getAllCars().stream().collect(Collectors.toMap(car -> car.carId, car -> car));
  }

  public List<Car> listAvailableCars() {
    return new ArrayList<>(cars.values());
  }

  public Car getCar(long carId) {
    return cars.get(carId);
  }

  public void sellCar(Car toSell, Client buyer, Player player) {
    if (!toSell.owner.equals(player)) {
      System.out.println("You cannot sell a car that's not yours! Don't steal :(");
      return;
    }
    if (!buyer.isInterested(toSell)) {
      System.out.println("This client is not interested in buy your car :(");
      return;
    }
    if (buyer.cash >= toSell.value) {
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


  public void repairDamagedParts(Player player, long workshopId, long carId) {
    Car car = getCar(carId);
    if (car.brakes == CarPartStatus.NOT_OK) {
      repairBrakes(player, workshopId, carId);
    }
    if (car.carBody == CarPartStatus.NOT_OK) {
      repairCarBody(player, workshopId, carId);
    }
    if (car.dampers == CarPartStatus.NOT_OK) {
      repairDampers(player, workshopId, carId);
    }
    if (car.engine == CarPartStatus.NOT_OK) {
      repairEngine(player, workshopId, carId);
    }
    if (car.gearbox == CarPartStatus.NOT_OK) {
      repairGearbox(player, workshopId, carId);
    }
  }

  public Collection<Workshop> listAvailableWorkshops() {
    return availableWorkshops.values();
  }

  public List<Transaction> repairHistory(long carId) {
    return carRepairHistory.get(carId);
  }

  private List<Car> getAllCars() {
    List<Car> allCars = new ArrayList<>();
    try (Connection connection = Connector.connect()) {
      ResultSet result = connection.createStatement()
          .executeQuery("SELECT id, car_type, value, brand, mileage, color, segment, load_capacity " +
              "FROM public.car");
      while (result.next()) {
        String carType = result.getString("car_type");
        switch (carType) {
          case "passenger" -> allCars.add(
              new PassangerCar(result.getLong("id"),
                  null,
                  result.getDouble("value"),
                  result.getString("brand"),
                  result.getDouble("mileage"),
                  result.getString("color"),
                  result.getString("segment"))
          );
          case "truck" -> allCars.add(
              new Truck(result.getLong("id"),
                  null,
                  result.getDouble("value"),
                  result.getString("brand"),
                  result.getDouble("mileage"),
                  result.getString("color"),
                  result.getString("segment"),
                  result.getInt("load_capacity"))
          );
        }
      }
    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }
    return allCars;
  }
}

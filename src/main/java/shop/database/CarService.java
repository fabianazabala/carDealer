package shop.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shop.cars.Car;
import shop.clients.Client;
import shop.transactions.Transaction;

public class CarService {

  private Map<Long, List<Transaction>> carRepairHistory = new HashMap<>();
  private Map<Long, Car> cars = new HashMap<>();

  public List<Car> listAvailableCars() {
    return Collections.emptyList();
  }

  public Car getCar(long carId) {
    return cars.get(carId);
  }

  public void sellCar(Car toSell, Client buyer) {

  }

  public Car buyCar(long carId) {
    return null;
  }

  public Car repairCar(long carId) {
    return null;
  }

  public List<Transaction> repairHistory(long carId) {
    return carRepairHistory.get(carId);
  }
}

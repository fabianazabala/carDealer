package shop.stores;

import shop.cars.Car;
import shop.cars.RepairParts;
import shop.players.Player;

public class MarianAuto implements RepairParts {

  public double price = 60;

  @Override
  public Car repairBrakes(Player player, Car car) {
    return null;
  }

  @Override
  public Car repairDampers(Player player, Car car) {
    return null;
  }

  @Override
  public Car repairEngine(Player player, Car car) {
    return null;
  }

  @Override
  public Car repairCarBody(Player player, Car car) {
    return null;
  }

  @Override
  public Car repairGearbox(Player player, Car car) {
    return null;
  }
}

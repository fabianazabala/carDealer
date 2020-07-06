package shop.cars;

import shop.players.Player;

public interface RepairParts {

  public Car repairBrakes(Player player, Car car);

  public Car repairDampers(Player player, Car car);

  public Car repairEngine(Player player, Car car);

  public Car repairCarBody(Player player, Car car);

  public Car repairGearbox(Player player, Car car);
}

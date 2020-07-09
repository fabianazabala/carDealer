package shop.stores;

import shop.cars.Car;
import shop.persons.Player;

public interface Workshop {

  public void repairBrakes(Player player, Car car);

  public void repairDampers(Player player, Car car);

  public void repairEngine(Player player, Car car);

  public void repairCarBody(Player player, Car car);

  public void repairGearbox(Player player, Car car);

  public boolean isRepairSuccessful();

  public long workshopId();

  public String workshopDescription();
}

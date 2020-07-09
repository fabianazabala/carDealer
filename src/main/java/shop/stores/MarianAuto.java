package shop.stores;

import java.util.concurrent.ThreadLocalRandom;
import shop.cars.Car;
import shop.cars.CarPartStatus;
import shop.cars.Workshop;
import shop.persons.Player;

public class MarianAuto implements Workshop {

  public static final int BRAKES_PRICE = 15;
  public static final int DAMPERS_PRICE = 20;
  public static final int ENGINE_PRICE = 50;
  public static final int CAR_BODY_PRICE = 40;
  public static final int CAR_GEARBOX = 40;
  public static final int STORE_PREMIUM_PRICE = 80;
  public static final int STORE_STANDARD_PRICE = 40;
  public static final int STORE_BUDGET_PRICE = 15;

  public long workshopId;

  public MarianAuto(long workshopId) {
    this.workshopId = workshopId;
  }

  @Override
  public void repairBrakes(Player player, Car car) {
    player.cash = player.cash - BRAKES_PRICE;


    if (car.segment.equals("premium")) {
      player.cash = player.cash - STORE_PREMIUM_PRICE;

    } else if (car.segment.equals("standard")) {
      player.cash = player.cash - STORE_STANDARD_PRICE;


    } else if (car.segment.equals("budget")) {
      player.cash = player.cash - STORE_BUDGET_PRICE;
    }

    if (isRepairSuccessful()) {
      car.value = car.value + car.value * 0.10;
      car.brakes = CarPartStatus.OK;
      System.out.println("We've successfully repaired your brakes :)");
    } else {
      throw new CarRepairFailException("We couldn't repair your brakes :( sorry!");
    }
  }

  @Override
  public void repairDampers(Player player, Car car) {
    player.cash = player.cash - DAMPERS_PRICE;

    if (car.segment.equals("premium")) {
      player.cash = player.cash - STORE_PREMIUM_PRICE;

    } else if (car.segment.equals("standard")) {
      player.cash = player.cash - STORE_STANDARD_PRICE;


    } else if (car.segment.equals("budget")) {
      player.cash = player.cash - STORE_BUDGET_PRICE;
    }

    if (isRepairSuccessful()) {
      car.value = car.value + car.value * 0.20;
      car.dampers = CarPartStatus.OK;
      System.out.println("We've successfully repaired your dampers :)");
    } else {
      throw new CarRepairFailException("We couldn't repair your dampers :( sorry!");
    }
  }

  @Override
  public void repairEngine(Player player, Car car) {
    player.cash = player.cash - ENGINE_PRICE;

    if (car.segment.equals("premium")) {
      player.cash = player.cash - STORE_PREMIUM_PRICE;

    } else if (car.segment.equals("standard")) {
      player.cash = player.cash - STORE_STANDARD_PRICE;


    } else if (car.segment.equals("budget")) {
      player.cash = player.cash - STORE_BUDGET_PRICE;
    }

    if (isRepairSuccessful()) {
      car.value = car.value * 2;
      car.engine = CarPartStatus.OK;
      System.out.println("We've successfully repaired your engine :)");
    } else {
      throw new CarRepairFailException("We couldn't repair your engine :( sorry!");
    }

  }

  @Override
  public void repairCarBody(Player player, Car car) {
    player.cash = player.cash - CAR_BODY_PRICE;

    if (car.segment.equals("premium")) {
      player.cash = player.cash - STORE_PREMIUM_PRICE;

    } else if (car.segment.equals("standard")) {
      player.cash = player.cash - STORE_STANDARD_PRICE;


    } else if (car.segment.equals("budget")) {
      player.cash = player.cash - STORE_BUDGET_PRICE;
    }

    if (isRepairSuccessful()) {
      car.value = car.value + car.value * 0.50;
      car.carBody = CarPartStatus.OK;
      System.out.println("We've successfully repaired your car body :)");
    } else {
      throw new CarRepairFailException("We couldn't repair your car body :( sorry!");
    }

  }

  @Override
  public void repairGearbox(Player player, Car car) {
    player.cash = player.cash - CAR_GEARBOX;

    if (car.segment.equals("premium")) {
      player.cash = player.cash - STORE_PREMIUM_PRICE;

    } else if (car.segment.equals("standard")) {
      player.cash = player.cash - STORE_STANDARD_PRICE;


    } else if (car.segment.equals("budget")) {
      player.cash = player.cash - STORE_BUDGET_PRICE;
    }

    if (isRepairSuccessful()) {
      car.value = car.value + car.value * 0.50;
      car.gearbox = CarPartStatus.OK;
      System.out.println("We've successfully repaired your gearbox :)");
    } else {
      throw new CarRepairFailException("We couldn't repair your gearbox :( sorry!");
    }

  }

  @Override
  public boolean isRepairSuccessful() {
    return ThreadLocalRandom.current().nextInt(0, 100 + 1) > 10;
  }

  @Override
  public long workshopId() {
    return workshopId;
  }

  @Override
  public String workshopDescription() {
    return "Very good price per quality value! We guarantee 90% of your money, there's 10% chance of failure in the repair";
  }

  @Override
  public String toString() {
    return "Id: " + workshopId + "\n Description:" + workshopDescription();
  }
}

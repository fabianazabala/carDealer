package shop.stores;

import java.util.concurrent.ThreadLocalRandom;
import shop.cars.Car;
import shop.cars.CarPartStatus;
import shop.cars.Workshop;
import shop.persons.Player;

public class PPHUAdrian implements Workshop {

  public static final int BRAKES_PRICE = 5;
  public static final int DAMPERS_PRICE = 10;
  public static final int ENGINE_PRICE = 25;
  public static final int CAR_BODY_PRICE = 20;
  public static final int CAR_GEARBOX = 20;
  public static final int STORE_PREMIUM_PRICE = 40;
  public static final int STORE_STANDARD_PRICE = 20;
  public static final int STORE_BUDGET_PRICE = 5;

  public long workshopId;

  public PPHUAdrian(long workshopId) {
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
      System.out.println("We've successfully repaired your car :)");
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your dampers :(");
        car.dampers = CarPartStatus.NOT_OK;
        car.value = car.value - car.value * 0.20;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
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
      System.out.println("We've successfully repaired your car :)");
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your brakes :(");
        car.brakes = CarPartStatus.NOT_OK;
        car.value = car.value - car.value * 0.10;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
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
      System.out.println("We've successfully repaired your car :)");
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your car body :(");
        car.carBody = CarPartStatus.NOT_OK;
        car.value = car.value - car.value * 0.50;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
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
      System.out.println("We've successfully repaired your car :)");
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your gearbox :(");
        car.gearbox = CarPartStatus.NOT_OK;
        car.value = car.value - car.value * 0.50;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
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
      System.out.println("We've successfully repaired your car :)");
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your engine :(");
        car.engine = CarPartStatus.NOT_OK;
        car.value = car.value / 2;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
    }

  }

  @Override
  public boolean isRepairSuccessful() {
    return ThreadLocalRandom.current().nextInt(0, 100 + 1) > 20;
  }

  public boolean areOtherParsFine() {
    return ThreadLocalRandom.current().nextInt(0, 100 + 1) > 2;
  }

  @Override
  public long workshopId() {
    return workshopId;
  }

  @Override
  public String workshopDescription() {
    return "Lowest prices available! We guarantee 80% of your money, " +
        "there's 20% chance of failure in your repair and a very low chance we break another piece";
  }

  @Override
  public String toString() {
    return "Id: " + workshopId + "\n Description:" + workshopDescription();
  }
}

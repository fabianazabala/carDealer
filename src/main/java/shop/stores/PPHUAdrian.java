package shop.stores;

import java.util.concurrent.ThreadLocalRandom;
import shop.cars.Car;
import shop.cars.RepairParts;
import shop.players.Player;

public class PPHUAdrian implements RepairParts {

  public static final int BRAKES_PRICE = 5;
  public static final int DAMPERS_PRICE = 10;
  public static final int ENGINE_PRICE = 25;
  public static final int CAR_BODY_PRICE = 20;
  public static final int CAR_GEARBOX = 20;
  public static final int STORE_PREMIUM_PRICE = 40;
  public static final int STORE_STANDARD_PRICE = 20;
  public static final int STORE_BUDGET_PRICE = 5;

  @Override
  public Car repairBrakes(Player player, Car car) {
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
      car.brakes = "ok";
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your dampers :(");
        car.dampers = "not ok";
        car.value = car.value - car.value * 0.20;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
    }
    return car;
  }

  @Override
  public Car repairDampers(Player player, Car car) {
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
      car.dampers = "ok";
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your brakes :(");
        car.brakes = "not ok";
        car.value = car.value - car.value * 0.10;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
    }
    return car;
  }

  @Override
  public Car repairEngine(Player player, Car car) {
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
      car.engine = "ok";
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your car body :(");
        car.carBody = "not ok";
        car.value = car.value - car.value * 0.50;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
    }

    return car;
  }

  @Override
  public Car repairCarBody(Player player, Car car) {
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
      car.carBody = "ok";
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your gearbox :(");
        car.gearbox = "not ok";
        car.value = car.value - car.value * 0.50;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
    }

    return car;
  }

  @Override
  public Car repairGearbox(Player player, Car car) {
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
      car.gearbox = "ok";
    } else {
      if (!areOtherParsFine()) {
        System.out.println("Sorry! We also broke your engine :(");
        car.engine = "not ok";
        car.value = car.value / 2;
      }
      throw new CarRepairFailException("We couldn't repair your car :( sorry!");
    }

    return car;
  }

  @Override
  public boolean isRepairSuccessful() {
    return ThreadLocalRandom.current().nextInt(0, 100 + 1) > 20;
  }

  public boolean areOtherParsFine() {
    return ThreadLocalRandom.current().nextInt(0, 100 + 1) > 2;
  }
}

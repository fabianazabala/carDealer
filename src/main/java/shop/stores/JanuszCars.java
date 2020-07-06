package shop.stores;

import shop.cars.Car;
import shop.cars.RepairParts;
import shop.players.Player;

public class JanuszCars implements RepairParts {

  public static final int BRAKES_PRICE = 20;
  public static final int DAMPERS_PRICE = 30;
  public static final int ENGINE_PRICE = 60;
  public static final int CAR_BODY_PRICE = 50;
  public static final int CAR_GEARBOX = 50;
  public static final int STORE_PREMIUM_PRICE = 100;
  public static final int STORE_STANDARD_PRICE = 50;
  public static final int STORE_BUDGET_PRICE = 20;

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

    car.value = car.value + car.value * 0.10;
    car.brakes = "ok";
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

    car.value = car.value + car.value * 0.20;
    car.dampers = "ok";
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

    car.value = car.value * 2;
    car.engine = "ok";
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

    car.value = car.value + car.value * 0.50;
    car.carBody = "ok";
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

    car.value = car.value + car.value * 0.50;
    car.gearbox = "ok";
    return car;
  }

  @Override
  public boolean isRepairSuccessful() {
    return true;
  }
}

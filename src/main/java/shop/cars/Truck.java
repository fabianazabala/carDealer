package shop.cars;

public class Truck extends Car {

  public static String CAR_TYPE = "truck";

  public int loadCapacity;

  @Override
  public Car repairBrakes() {
    return null;
  }

  @Override
  public Car repairDampers() {
    return null;
  }

  @Override
  public Car repairEngine() {
    return null;
  }

  @Override
  public Car repairCarBody() {
    return null;
  }

  @Override
  public Car repairGearbox() {
    return null;
  }
}

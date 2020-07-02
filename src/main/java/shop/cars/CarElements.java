package shop.cars;

public abstract class CarElements {
  public CarPart brakes;
  public CarPart dampers;
  public CarPart engine;
  public CarPart carBody;
  public CarPart gearbox;

  public abstract Car repairBrakes();

  public abstract Car repairDampers();

  public abstract Car repairEngine();

  public abstract Car repairCarBody();

  public abstract Car repairGearbox();
}

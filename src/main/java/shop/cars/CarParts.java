package shop.cars;

import java.util.concurrent.ThreadLocalRandom;

public abstract class CarParts {
  public CarPartStatus brakes;
  public CarPartStatus dampers;
  public CarPartStatus engine;
  public CarPartStatus carBody;
  public CarPartStatus gearbox;

  public CarParts() {
    brakes = isPartWorking() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    dampers = isPartWorking() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    engine = isPartWorking() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    carBody = isPartWorking() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    gearbox = isPartWorking() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
  }

  private boolean isPartWorking() {
    return ThreadLocalRandom.current().nextInt(0, 100) <= 20;
  }

  @Override
  public String toString() {
    return " Brakes: " + brakes
        + ", Dampers: " + dampers
        + ", Engine: " + engine
        + ", Car Body: " + carBody
        + ", Gearbox: " + gearbox;
  }
}

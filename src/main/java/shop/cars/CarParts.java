package shop.cars;

import java.util.concurrent.ThreadLocalRandom;

public abstract class CarParts {
  public CarPartStatus brakes;
  public CarPartStatus dampers;
  public CarPartStatus engine;
  public CarPartStatus carBody;
  public CarPartStatus gearbox;

  public CarParts() {
    brakes = randomBrokenPart() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    dampers = randomBrokenPart() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    engine = randomBrokenPart() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    carBody = randomBrokenPart() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
    gearbox = randomBrokenPart() ? CarPartStatus.OK : CarPartStatus.NOT_OK;
  }

  private boolean randomBrokenPart() {
    return ThreadLocalRandom.current().nextInt(0, 100) <= 20;
  }

  private boolean isPartBroken(CarPartStatus status) {
    return status == CarPartStatus.NOT_OK;
  }

  public boolean isDamaged() {
    return isPartBroken(brakes)
        || isPartBroken(dampers)
        || isPartBroken(engine)
        || isPartBroken(carBody)
        || isPartBroken(gearbox);
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

package shop.cars;

import shop.persons.Person;

public class PassangerCar extends Car {

  public PassangerCar(long carId, Person owner, double value, String brand, double mileage, String color,
                      String segment) {
    super(carId, owner, value, brand, mileage, color, segment);
  }

  @Override
  public String carType() {
    return "passenger";
  }
}

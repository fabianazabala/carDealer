package shop.cars;

import shop.persons.Person;

public class Truck extends Car {

  public int loadCapacity;

  public Truck(long carId, Person owner, double value, String brand, double mileage, String color,
               String segment, int loadCapacity) {
    super(carId, owner, value, brand, mileage, color, segment);
    this.loadCapacity = loadCapacity;
  }

  @Override
  public String carType() {
    return "truck";
  }
}

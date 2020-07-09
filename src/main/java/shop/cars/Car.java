package shop.cars;

import shop.persons.Person;

public abstract class Car extends CarParts {

  public long carId;
  public Person owner;
  public double value;
  public String brand;
  public double mileage;
  public String color;
  public String segment; // premium - standard - budget

  public Car(long carId, Person owner, double value, String brand, double mileage, String color, String segment) {
    super();
    this.carId = carId;
    this.owner = owner;
    this.value = value;
    this.brand = brand;
    this.mileage = mileage;
    this.color = color;
    this.segment = segment;
  }

  @Override
  public String toString() {
    return "Id: " + carId
        + " \nCar Type: " + carType()
        + " \nBrand: " + brand
        + " \nColor: " + color
        + " \nMileage: " + mileage
        + " \nSegment: " + segment
        + " \nValue: " + value
        + " \nCar parts: " + super.toString();
  }

  public abstract String carType();
}

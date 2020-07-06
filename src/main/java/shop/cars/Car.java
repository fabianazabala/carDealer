package shop.cars;

public abstract class Car extends CarParts {
  public long carId;
  public double value;
  public String brand;
  public double mileage;
  public String color;
  public String segment; // premium - standard - budget


  @Override
  public String toString() {
    return "Id: " + carId
        + " \nBrand: " + brand
        + " \nColor: " + color
        + " \nMileage: " + mileage
        + " \nSegment: " + segment
        + " \nValue: " + value;
  }
}

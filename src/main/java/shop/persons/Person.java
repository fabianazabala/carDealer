package shop.persons;

import java.util.ArrayList;
import java.util.List;
import shop.cars.Car;

public abstract class Person {
  public long id;
  public String name;
  public double cash;
  public List<Car> cars = new ArrayList<>();

  public Person(long id, String name, double cash) {
    this.id = id;
    this.name = name;
    this.cash = cash;
  }

  @Override
  public String toString() {
    return "Id: " + id
        + ", Name: " + name
        + ", Cash: " + cash
        + ", Cars: " + cars;
  }
}

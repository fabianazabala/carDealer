package shop.persons;

import java.util.ArrayList;
import java.util.List;
import shop.cars.Car;

public abstract class Person {
  public long id;
  public String name;
  public double cash;
  public List<Car> cars = new ArrayList<>();
}

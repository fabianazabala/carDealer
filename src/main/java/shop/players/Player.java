package shop.players;

import java.util.ArrayList;
import java.util.List;
import shop.cars.Car;
import shop.transactions.Transaction;

public class Player {

  public long id;
  public String name;
  public double cash = 10000;
  public List<Car> cars = new ArrayList<>();
  public List<Transaction> transactions = new ArrayList<>();
}

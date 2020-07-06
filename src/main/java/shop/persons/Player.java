package shop.persons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import shop.transactions.Transaction;

public class Player extends Person {

  public List<Transaction> transactions = new ArrayList<>();

  public Player() {
    cash = 10000;
    id = ThreadLocalRandom.current().nextLong(1, 100);
  }
}

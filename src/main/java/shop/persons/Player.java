package shop.persons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import shop.transactions.Transaction;

public class Player extends Person {

  public List<Transaction> transactions = new ArrayList<>();

  public Player() {
    super(ThreadLocalRandom.current().nextLong(1, 100), null, 10000);
  }
}

package shop.database;

import java.util.Collections;
import java.util.List;
import shop.persons.Client;
import shop.persons.Player;
import shop.transactions.Transaction;

public class ClientService {

  public List<Client> potentialClients() {
    //from db?
    return Collections.emptyList();
  }

  public Client getClient(long clientId) {
    //from db?
    return null;
  }

  public void buyAdvertisement(Player player, double amount) {
    if (player.cash >= amount) {
      //adds more clients to db?
      Transaction transaction = new Transaction(-amount, "Bought more advertisement");
      player.transactions.add(transaction);
      System.out.println("You've successfully invest " + amount + " in advertisement!");
    } else {
      System.out.println("You don't have enough money to buy more advertisement :(");
    }
  }
}

package shop.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shop.persons.Client;
import shop.persons.Player;
import shop.transactions.Transaction;

public class ClientService {

  private Map<Long, Client> clients = new HashMap<>();

  public ClientService() {
    clients.put(1L, new Client(1L, "Name1", 5000, "passenger", "Mercedes", "premium", false));
    clients.put(2L, new Client(2L, "Name2", 500, "passenger", "Ford", "standard", false));
    clients.put(3L, new Client(3L, "Name3", 300, "passenger", "Chevrolet", "standard", false));
    clients.put(4L, new Client(4L, "Name4", 50000, "passenger", "Ferrari", "premium", false));
    clients.put(5L, new Client(5L, "Name5", 3000, "passenger", "Audi", "premium", false));
    clients.put(6L, new Client(6L, "Name6", 500, "passenger", "Toyota", "standard", false));
    clients.put(7L, new Client(7L, "Name7", 500, "passenger", "Mazda", "standard", false));
  }

  public List<Client> potentialClients() {
    //from db?
    return new ArrayList<>(clients.values());
  }

  public Client getClient(long clientId) {
    //from db?
    return clients.get(clientId);
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

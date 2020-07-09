package shop.database;

import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import shop.persons.Client;
import shop.persons.Player;
import shop.transactions.Transaction;

public class ClientService {

  private Map<Long, Client> allClients;
  private Faker faker = new Faker();

  public ClientService() {
    allClients = getInitialClients().stream().collect(Collectors.toMap(client -> client.id, client -> client));
  }

  public List<Client> potentialClients() {
    return new ArrayList<>(allClients.values());
  }

  public Client getClient(long clientId) {
    return allClients.get(clientId);
  }

  public void buyAdvertisement(Player player, double amount) {
    if (player.cash >= amount) {
      double toAdd = amount / 100;
      if (toAdd < 1) {
        System.out.println("You need at least 100 to buy more advertisement");
        return;
      }
      int addedClients = ThreadLocalRandom.current().nextInt(0, (int) toAdd + 1);
      addRandomClients(addedClients);

      Transaction transaction = new Transaction(-amount, "Bought more advertisement");
      player.transactions.add(transaction);
      System.out.println("You've successfully invest " + amount + " in advertisement!");
    } else {
      System.out.println("You don't have enough money to buy more advertisement :(");
    }
  }

  public List<Client> getInitialClients() {
    List<Client> initialClients = new ArrayList<>();
    try (Connection connection = Connector.connect()) {
      ResultSet result = connection.createStatement()
          .executeQuery(
              "SELECT id, name, cash, brand_preference, car_type_preference, segment_preference, accepts_damaged " +
                  "FROM public.client");
      while (result.next()) {
        initialClients.add(new Client(result.getLong("id"),
            result.getString("name"),
            result.getDouble("cash"),
            result.getString("brand_preference"),
            result.getString("car_type_preference"),
            result.getString("segment_preference"),
            result.getBoolean("accepts_damaged")));
      }
    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }
    return initialClients;
  }

  public void addRandomClients(int clientsNumber) {
    long currentMaxId = allClients.keySet().stream().max(Long::compareTo).orElse(0L);

    for (int i = 0; i < clientsNumber; i++) {
      allClients.put(++currentMaxId,
          new Client(
              currentMaxId,
              faker.name().fullName(),
              faker.number().randomDouble(2, 100, 100000),
              randomTypePreference(),
              randomBrandPreference(),
              randomSegmentPreference(),
              ThreadLocalRandom.current().nextBoolean()
          ));
    }
  }

  public String randomTypePreference() {
    List<String> types = new ArrayList<>(List.of("passenger", "truck"));
    Collections.shuffle(types);
    return types.get(0);
  }

  public String randomBrandPreference() {
    List<String> brands = new ArrayList<>(List.of(
        "Mazda",
        "Ford",
        "Mercedes",
        "Wolkswagen",
        "Toyota",
        "Audi",
        "Skoda",
        "Chevrolet",
        "Ferrari"));
    Collections.shuffle(brands);
    return brands.get(0);
  }

  public String randomSegmentPreference() {
    List<String> segments = new ArrayList<>(List.of(
        "budget", "standard", "premium"
    ));
    Collections.shuffle(segments);
    return segments.get(0);
  }
}

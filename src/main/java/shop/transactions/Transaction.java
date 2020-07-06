package shop.transactions;

public class Transaction {
  public double value;
  public String purpose;


  @Override
  public String toString() {
    return "Purpose: " + purpose + "\nValue: " + value;
  }
}

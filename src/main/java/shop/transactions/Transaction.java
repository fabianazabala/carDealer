package shop.transactions;

public class Transaction {
  public double value;
  public String purpose;

  public Transaction(double value, String purpose) {
    this.value = value;
    this.purpose = purpose;
  }

  @Override
  public String toString() {
    return "Purpose: " + purpose + "\nValue: " + value;
  }
}

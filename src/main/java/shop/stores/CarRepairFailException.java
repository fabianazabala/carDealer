package shop.stores;

public class CarRepairFailException extends RuntimeException {
  public CarRepairFailException(String message) {
    super(message);
  }
}

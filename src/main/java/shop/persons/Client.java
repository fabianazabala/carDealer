package shop.persons;

import shop.cars.Car;

public class Client extends Person {

  public String carTypePreference;
  public String brandPreference;
  public String segmentPreference;
  public boolean acceptDamaged;

  public Client(long id, String name, double cash, String carTypePreference, String brandPreference,
                String segmentPreference, boolean acceptDamaged) {
    super(id, name, cash);
    this.carTypePreference = carTypePreference;
    this.brandPreference = brandPreference;
    this.segmentPreference = segmentPreference;
    this.acceptDamaged = acceptDamaged;
  }

  public boolean isInterested(Car car) {
    return (!car.isDamaged() || (car.isDamaged() && acceptDamaged))
        && car.value <= cash
        && car.brand.equals(brandPreference)
        && car.carType().equals(carTypePreference)
        && car.segment.equals(segmentPreference);
  }

  @Override
  public String toString() {
    return super.toString()
        + ", Car Type preference: " + carTypePreference
        + ", Brand preference: " + brandPreference
        + ", Segment preference: " + segmentPreference
        + ", Accepts damaged car: " + (acceptDamaged ? "yes" : "no");
  }
}

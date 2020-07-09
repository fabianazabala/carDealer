package shop.persons;

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

  @Override
  public String toString() {
    return super.toString()
        + ", Car Type preference: " + carTypePreference
        + ", Brand preference: " + brandPreference
        + ", Segment preference: " + segmentPreference
        + ", Accepts damaged car: " + (acceptDamaged ? "yes" : "no");
  }
}

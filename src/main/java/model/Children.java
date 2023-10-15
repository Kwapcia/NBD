package model;

public class Children implements PassengerType {
    public double applyDiscount(double price) {
        return 0.75 * price;
    }

    public String getTypeInfo() {
        return "Children";
    }
}

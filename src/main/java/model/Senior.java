package model;

public class Senior implements PassengerType {
    public double applyDiscount(double price) {
        return 0.8 * price;
    }

    public String getTypeInfo() {
        return "Senior";
    }
}

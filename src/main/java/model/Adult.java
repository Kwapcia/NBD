package model;

public class Adult implements PassengerType {
    public double applyDiscount(double price) {
        return price;
    }

    public String getTypeInfo() {
        return "Adult";
    }
}

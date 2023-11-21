package model;

public class Children extends PassengerType{

    public String discount = "25%";

    @Override
    public double applyDiscount(double price) {
        return 0.75 * price;
    }

    @Override
    public String getTypeInfo() {
        return "Children";
    }
}

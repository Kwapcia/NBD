package model;

public class Adult extends PassengerType{

    public String discount = "0%";

    @Override
    public double applyDiscount(double price) {
        return price;
    }

    @Override
    public String getTypeInfo() {
        return "Adult";
    }
}

package model;

//@Entity
//@DiscriminatorValue(value = "Senior")

public class Senior extends PassengerType {

    //@Column(name = "Discount")
    public String discount = "20%";

    @Override
    public double applyDiscount(double price) {
        return 0.8 * price;
    }

    @Override
    public String getTypeInfo() {
        return "Senior";
    }
}

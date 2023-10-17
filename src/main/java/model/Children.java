package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Child")

public class Children implements PassengerType {

    @Column(name = "Discount")
    public String discount = "25%";

    public double applyDiscount(double price) {
        return 0.75 * price;
    }

    public String getTypeInfo() {
        return "Children";
    }
}

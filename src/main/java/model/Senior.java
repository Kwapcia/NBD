package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Senior")

public class Senior implements PassengerType {

    @Column(name = "Discount")
    public String discount = "20%";

    public double applyDiscount(double price) {
        return 0.8 * price;
    }

    public String getTypeInfo() {
        return "Senior";
    }
}

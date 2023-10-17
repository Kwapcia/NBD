package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Adult")

public class Adult implements PassengerType {

    @Column(name = "Discount")
    public String discount = "0%";

    public double applyDiscount(double price) {
        return price;
    }

    public String getTypeInfo() {
        return "Adult";
    }
}

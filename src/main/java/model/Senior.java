package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "Senior")

public class Senior extends PassengerType {

    @Column(name = "Discount")
    public String discount = "20%";

    public double applyDiscount(double price) {
        return 0.8 * price;
    }

    public String getTypeInfo() {
        return "Senior";
    }
}

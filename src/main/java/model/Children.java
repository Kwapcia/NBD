package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "Child")

public class Children extends PassengerType{

    @Column(name = "Discount")
    public String discount = "25%";

    public double applyDiscount(double price) {
        return 0.75 * price;
    }

    public String getTypeInfo() {
        return "Children";
    }
}

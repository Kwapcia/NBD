package model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "Adult")

public class Adult extends PassengerType{

    @Column(name = "Discount")
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

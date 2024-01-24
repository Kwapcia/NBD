package model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import ids.CassandraIds;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
@Entity(defaultKeyspace = CassandraIds.KEYSPACE)
@CqlName(CassandraIds.PASSENGER_TABLE)
@Getter
@Setter

public class Senior extends Passenger {

    @CqlName("discount")
    private double discount;

    public Senior(){}
    public Senior(String discriminator,String firstName, String lastName, UUID id, int age, boolean isArchive, double discount){
        super(discriminator,firstName, lastName, id, age, isArchive);
        this.discount = discount;

    }

    @Override
    public double getDiscount() {
        return 0.8;
    }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public static double applyDiscount(double price) {
        return 0.8 * price;
    }
}

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
public class Children extends Passenger{
    @CqlName("discount")
    private double discount=0.75;



    public Children(){}

    public Children(String firstName, String lastName, UUID id, int age, boolean isArchive, double discount) {
        super(firstName, lastName, id, age,isArchive);
        this.discount=discount;


    }






    @Override
    public double getDiscount() {
        return 0.75;
    }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    //    public Children(String discount,String nrLegitymacji){
//        this.discount=discount;
//        this.nrLegitymacji=nrLegitymacji;
//    }

//    public Children() {
//
//    }
    //    public String discount = "25%";
//

    //public static double applyDiscount(double price) {
       // return 0.75 * price;
   // }
//
//    @Override
//    public String getTypeInfo() {
//        return "Children";
//    }
}

package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Senior extends Passenger {

    private String discount;
    private String nrKartySeniora;

    public Senior(String firstName, String lastName, ObjectId id, int age, boolean isArchive, String discount, String nrKartySeniora) {
        super(firstName, lastName, id, age, isArchive);
        this.discount = discount;
        this.nrKartySeniora = nrKartySeniora;
    }
    public Senior(String discount,String nrKartySeniora){
        this.discount=discount;
        this.nrKartySeniora =nrKartySeniora;
    }


    public static double applyDiscount(double price) {
        return 0.8 * price;
    }

//    @Override
//    public String getTypeInfo() {
//        return "Senior";
//    }
}

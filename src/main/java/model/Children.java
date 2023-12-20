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
public class Children extends Passenger{
    private String discount;
    private String nrLegitymacji;

    public Children(String firstName, String lastName, ObjectId id, int age, String discount, String nrLegitymacji) {
        super(firstName, lastName, id, age);
        this.discount = discount;
        this.nrLegitymacji = nrLegitymacji;
    }
    public Children(String discount,String nrLegitymacji){
        this.discount=discount;
        this.nrLegitymacji=nrLegitymacji;
    }

//    public Children() {
//
//    }
    //    public String discount = "25%";
//

    public static double applyDiscount(double price) {
        return 0.75 * price;
    }
//
//    @Override
//    public String getTypeInfo() {
//        return "Children";
//    }
}

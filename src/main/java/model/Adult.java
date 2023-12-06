package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Adult extends Passenger{

    private String discount;
    private String nrDowodu;

    public Adult(String discount,String nrDowodu){
        this.discount=discount;
        this.nrDowodu=nrDowodu;
    }
    public Adult(String firstName, String lastName, UUID id, int age, boolean isArchive, String discount, String nrDowodu) {
        super(firstName, lastName, id, age, isArchive);
        this.discount = discount;
        this.nrDowodu = nrDowodu;
    }

    //    @Override
    public static double applyDiscount(double price) {
        return price;
    }

//    @Override
//    public String getTypeInfo() {
//        return "ADULT";
//    }
}

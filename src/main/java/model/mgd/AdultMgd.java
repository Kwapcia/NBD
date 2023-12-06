package model.mgd;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.Adult;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;
@Getter
@SuperBuilder
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@BsonDiscriminator(key="_tClass",value="adult")
public class AdultMgd extends PassengerMgd{
    @BsonProperty("discount")
    private String discount;

    @BsonProperty("nrDowodu")
    private String nrDowodu;

    public AdultMgd(@BsonProperty("First_Name") String firstName,
                    @BsonProperty("Last_Name")String lastName,
                    @BsonProperty("_id") UUID id,
                    @BsonProperty("Age")int age,
//                        @BsonProperty("Passenger_Type")PassengerMgd.Type passengerType,
                    @BsonProperty("Is_Archived") boolean isArchive,
                    @BsonProperty("discount")String discount,
                    @BsonProperty("nrDowodu")String nrDowodu) {
        super(firstName, lastName, id, age, isArchive);
        this.discount = discount;
        this.nrDowodu = nrDowodu;
    }
}

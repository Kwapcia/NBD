package model.mgd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class ChildrenMgd extends PassengerMgd{

    @BsonProperty("discount")
    private String discount;

    @BsonProperty("nrLegitymacji")
    private String nrLegitymacji;

    public ChildrenMgd(@BsonProperty("First_Name") String firstName,
                       @BsonProperty("Last_Name")String lastName,
                       @BsonProperty("_id") UUID id,
                       @BsonProperty("Age")int age,
//                        @BsonProperty("Passenger_Type")PassengerMgd.Type passengerType,
                       @BsonProperty("Is_Archived") boolean isArchive,
                       @BsonProperty("discount")String discount,
                       @BsonProperty("nrLegitymacji")String nrLegitymacji){
        super(firstName,lastName,id,age,isArchive);
        this.discount=discount;
        this.nrLegitymacji=nrLegitymacji;
    }
}

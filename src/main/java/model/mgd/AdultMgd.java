package model.mgd;

import lombok.*;
import lombok.experimental.SuperBuilder;
import com.google.gson.Gson;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.types.ObjectId;

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
@BsonCreator
    public AdultMgd(@BsonProperty("First_Name") String firstName,
                    @BsonProperty("Last_Name")String lastName,
                    @BsonProperty("_id") ObjectId id,
                    @BsonProperty("Age")int age,
//                        @BsonProperty("Passenger_Type")PassengerMgd.Type passengerType,
                    @BsonProperty("Is_Archived") boolean isArchive,
                    @BsonProperty("discount")String discount,
                    @BsonProperty("nrDowodu")String nrDowodu) {
        super(firstName, lastName, id, age, isArchive);
        this.discount = discount;
        this.nrDowodu = nrDowodu;
    }

    public static AdultMgd fromJson(String json){
    Gson gson = new Gson();
    return gson.fromJson(json,AdultMgd.class);
    }
}

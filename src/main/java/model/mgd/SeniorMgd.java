package model.mgd;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
@BsonDiscriminator(key="_tClass",value="senior")
public class SeniorMgd extends PassengerMgd{
    @BsonProperty("discount")
    private String discount;
    @BsonProperty("nrKartySeniora")
    private String nrKartySeniora;
    @BsonCreator
    public SeniorMgd(@BsonProperty("First_Name") String firstName,
                     @BsonProperty("Last_Name")String lastName,
                     @BsonProperty("_id") ObjectId id,
                     @BsonProperty("Age")int age,
//                        @BsonProperty("Passenger_Type")PassengerMgd.Type passengerType,
                     @BsonProperty("Is_Archived") boolean isArchive,
                     @BsonProperty("discount")String discount,
                     @BsonProperty("nrKartySeniora")String nrKartySeniora){
        super(firstName,lastName,id,age,isArchive);
        this.discount=discount;
        this.nrKartySeniora=nrKartySeniora;
    }
    public static SeniorMgd fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,SeniorMgd.class);
    }
}

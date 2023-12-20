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
import org.hibernate.tool.schema.internal.SchemaCreatorImpl;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
@BsonDiscriminator(key="_tClass",value="children")
public class ChildrenMgd extends PassengerMgd{

    @BsonProperty("discount")
    private String discount;

    @BsonProperty("nrLegitymacji")
    private String nrLegitymacji;
@BsonCreator
    public ChildrenMgd(@BsonProperty("First_Name") String firstName,
                       @BsonProperty("Last_Name")String lastName,
                       @BsonProperty("_id") ObjectId id,
                       @BsonProperty("Age")int age,
//                        @BsonProperty("Passenger_Type")PassengerMgd.Type passengerType,
                       @BsonProperty("Is_Archived") boolean isArchive,
                       @BsonProperty("discount")String discount,
                       @BsonProperty("nrLegitymacji")String nrLegitymacji){
        super(firstName,lastName,id,age,isArchive);
        this.discount=discount;
        this.nrLegitymacji=nrLegitymacji;
    }
    public static ChildrenMgd fromJson(String json){
    Gson gson = new Gson();
    return gson.fromJson(json,ChildrenMgd.class);
    }
}

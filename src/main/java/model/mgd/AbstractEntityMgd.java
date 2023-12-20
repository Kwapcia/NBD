package model.mgd;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AbstractEntityMgd implements Serializable {
    @BsonProperty("_id")
    @SerializedName("_id")
    protected ObjectId id ;

    @BsonCreator
    public AbstractEntityMgd(@BsonProperty("_id")ObjectId id){
        this.id = id;
    }


}

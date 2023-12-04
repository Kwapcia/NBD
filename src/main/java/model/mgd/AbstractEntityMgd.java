package model.mgd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@SuperBuilder
public class AbstractEntityMgd implements Serializable {
    //@BsonProperty("id")
    private UUID id ;

    @BsonCreator
    public AbstractEntityMgd(@BsonProperty("_id")UUID id){
        this.id = id;
    }


}

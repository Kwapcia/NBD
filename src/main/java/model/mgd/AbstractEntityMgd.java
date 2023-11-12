package model.mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.io.Serializable;
import java.util.UUID;

public class AbstractEntityMgd implements Serializable {
    @BsonProperty("id")
    private UUID id ;

    @BsonCreator
    public AbstractEntityMgd(@BsonProperty("id")UUID id){
        this.id = id;
    }
}

package model.mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class PassengerEmbeddedMgd {

    @BsonCreator
    public PassengerEmbeddedMgd (
            @BsonProperty("_id") UUID id,
            @BsonProperty("passenger") PassengerMgd passenger
    ){
        this.id = id;
        this.passenger = passenger;
    }

    @BsonId
    private UUID id;

    @BsonProperty
    private PassengerMgd passenger;

    public PassengerMgd getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerMgd passenger) {
        this.passenger = passenger;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

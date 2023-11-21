package model.mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class TrainMgd extends AbstractEntityMgd {
    @BsonProperty("_id")
    private UUID id;

    @BsonProperty("Base_Price")
    private int basePrice;

    @BsonProperty("Seat")
    private String seat;

    @BsonProperty("Starting_Location")
    private String startingLocation;

    @BsonProperty("Destination")
    private String destination;

    @BsonProperty("IsArchive")
    private boolean isArchive;

    @BsonCreator
    public TrainMgd(@BsonProperty("_id") UUID id,
                     @BsonProperty("Base_Price") int basePrice,
                     @BsonProperty("Seat") String seat,
                     @BsonProperty("Starting_Location") String startingLocation,
                     @BsonProperty("Destination") String destination,
                     @BsonProperty("IsArchive") boolean isArchive){
        super(id);
        this.basePrice = basePrice;
        this.seat = seat;
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.isArchive = isArchive;
    }
    public UUID getId(){
        return id;
    }
    public int getBasePrice() {
        return basePrice;
    }

    public String getSeat() {
        return seat;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }
}

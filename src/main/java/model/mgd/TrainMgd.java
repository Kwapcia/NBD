package model.mgd;

import java.util.UUID;

public class TrainMgd extends AbstractEntityMgd {
    @BsonProperty("id")
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

//    public TicketMgd(@BsonProperty("id") UUID id,
//                     @BsonProperty("Base_Price") int basePrice,
//                     @BsonProperty("Seat") String seat,
//                     @BsonProperty("Starting_Location") String startingLocation,
//                     @BsonProperty("Destination") String destination,
//                     @BsonProperty("IsArchive") boolean isArchive){
//        this.id = id;
//        this.basePrice = basePrice;
//        this.seat = seat;
//        this.startingLocation = startingLocation;
//        this.destination = destination;
//        this.isArchive = isArchive;
//    }
    @BsonCreator
    public TicketMgd(@BsonProperty("id") UUID id,
                     @BsonProperty("Base_Price") int basePrice,
                     @BsonProperty("Seat") String seat,
                     @BsonProperty("Starting_Location") String startingLocation,
                     @BsonProperty("Destination") String destination,
                     @BsonProperty("IsArchive") boolean isArchive){
        this.id = id;
        this.basePrice = basePrice;
        this.seat = seat;
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.isArchive = isArchive;
    }
}

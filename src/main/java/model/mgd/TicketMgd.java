package model.mgd;

import model.mapper.PassengerMapper;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

public class TicketMgd extends AbstractEntityMgd{
    @BsonProperty("id")
    private UUID id;
    @BsonProperty("Begin_Time")
    private DateTime beginTime;
    @BsonProperty("End_Time")
    private DateTime endtime;
    @BsonProperty("Ticket_Price")
    private float ticketCost;
    @BsonProperty("Passenger")
    private PassengerMgd passenger;
    @BsonProperty("Train")
    private TrainMgd train;
    @BsonCreator
    public TicketMgd(@BsonProperty("id")UUID id,
                     @BsonProperty("Passenger")PassengerMgd passenger,
                     @BsonProperty("Train")TrainMgd train,
                     @BsonProperty("Begin_Time") DateTime beginTime) {
        super(id);
        this.passenger = passenger;
        this.train = train;
        this.beginTime = beginTime;
    }
    public UUID getId(){
        return id;
    }
}

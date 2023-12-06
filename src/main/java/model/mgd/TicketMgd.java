package model.mgd;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.UUID;
@SuperBuilder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TicketMgd extends AbstractEntityMgd{
//    @BsonProperty("_id")
//    private UUID id;
    @BsonProperty("Begin_Time")
    private DateTime beginTime;

    @BsonProperty("End_Time")
    private DateTime endTime;

    @BsonProperty("Ticket_Price")
    private float ticketCost;
//    @BsonProperty("Passenger")
//    private PassengerMgd passenger;
    @BsonProperty("ticket_passengers")
    private Map<String, PassengerMgd> ticketPassengers;
    @BsonProperty("Train")
    private TrainMgd train;

    @BsonCreator
    public TicketMgd(//@BsonProperty("id")UUID id,
                     @BsonProperty("ticket_passengers") Map<String,PassengerMgd>ticketPassengers,
                     @BsonProperty("Train")TrainMgd train,
                     @BsonProperty("Begin_Time") DateTime beginTime,
                     @BsonProperty("End_Time") DateTime endTime,
                     @BsonProperty("Ticket_Price")float ticketCost
                     )
    {
        //super(id);
        this.ticketPassengers=ticketPassengers;
        this.train = train;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.ticketCost = ticketCost;
    }

    public TicketMgd(UUID id, DateTime beginTime, DateTime endTime, float ticketCost, Map<String, PassengerMgd> ticketPassengers, TrainMgd train) {
        super(id);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.ticketCost = ticketCost;
        this.ticketPassengers = ticketPassengers;
        this.train = train;
    }
//    public UUID getId(){
//        return id;
//    }
//
//    public PassengerMgd getPassengerMgd() {
//        return passengerMgd;
//    }
//
//    public TrainMgd getTrainMgd() {
//        return trainMgd;
//    }
//
//
//    public DateTime getBeginTime() {
//        return beginTime;
//    }
//
//    public DateTime getEndTime() {
//        return endTime;
//    }
//
//    public float getTicketCost() {
//        return ticketCost;
//    }
//    public void setBeginTime(DateTime beginTime) {
//        this.beginTime = beginTime;
//    }
//
//    public void setEndTime(DateTime endTime) {
//        this.endTime = endTime;
//    }
//
//    public void setTicketCost(float ticketCost) {
//        this.ticketCost = ticketCost;
//    }
//
//    public void setPassengerMgd(PassengerMgd passengerMgd) {
//        this.passengerMgd = passengerMgd;
//    }
//
//    public void setTrainMgd(TrainMgd trainMgd) {
//        this.trainMgd = trainMgd;
//    }
}

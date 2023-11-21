package model.mgd;

import model.mapper.PassengerMapper;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

public class TicketMgd extends AbstractEntityMgd{
    @BsonProperty("_id")
    private UUID id;
    @BsonProperty("Begin_Time")
    private DateTime beginTime;
    @BsonProperty("End_Time")
    private DateTime endTime;
    @BsonProperty("Ticket_Price")
    private float ticketCost;
    @BsonProperty("Passenger")
    private PassengerMgd passengerMgd;
    @BsonProperty("Train")
    private TrainMgd trainMgd;

    @BsonCreator
    public TicketMgd(@BsonProperty("id")UUID id,
                     @BsonProperty("Passenger")PassengerMgd passenger,
                     @BsonProperty("Train")TrainMgd train,
                     @BsonProperty("Begin_Time") DateTime beginTime,
                    @BsonProperty("End_Time") DateTime endTime,
                     @BsonProperty("Ticket_Price")float ticketCost
                     )
    {
        super(id);
        this.passengerMgd = passenger;
        this.trainMgd = train;
        this.beginTime = beginTime;
    }
    public UUID getId(){
        return id;
    }

    public PassengerMgd getPassengerMgd() {
        return passengerMgd;
    }

    public TrainMgd getTrainMgd() {
        return trainMgd;
    }

    public DateTime getBeginTime() {
        return beginTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public float getTicketCost() {
        return ticketCost;
    }
    public void setBeginTime(DateTime beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public void setTicketCost(float ticketCost) {
        this.ticketCost = ticketCost;
    }

    public void setPassengerMgd(PassengerMgd passengerMgd) {
        this.passengerMgd = passengerMgd;
    }

    public void setTrainMgd(TrainMgd trainMgd) {
        this.trainMgd = trainMgd;
    }
}

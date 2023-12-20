package model.mgd;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import model.Passenger;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
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
    @BsonProperty("Passenger")
    private PassengerMgd passenger;
    @BsonProperty("Train")
    private TrainMgd train;

    @BsonCreator
    public TicketMgd(//@BsonProperty("id")UUID id,
                     @BsonProperty("Passenger")PassengerMgd passenger,
                     @BsonProperty("Train")TrainMgd train,
                     @BsonProperty("Begin_Time") DateTime beginTime,
                     @BsonProperty("End_Time") DateTime endTime,
                     @BsonProperty("Ticket_Price")float ticketCost
                     )
    {
        //super(id);
        this.passenger = passenger;
        this.train = train;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.ticketCost = ticketCost;
    }

    public TicketMgd(ObjectId id, DateTime beginTime, DateTime endTime, float ticketCost, PassengerMgd passenger, TrainMgd train) {
        super(id);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.ticketCost = ticketCost;
        this.passenger=passenger;
        this.train = train;
    }
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static TicketMgd fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,TicketMgd.class);
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

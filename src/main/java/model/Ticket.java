package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket extends AbstractEntity {

    //private UUID id;

    //private Passenger passenger;
    private Map<String,Passenger>ticketPassengers;

    private Train train;

    private DateTime beginTime;

    private DateTime endTime;

    private float ticketCost;

    public Ticket(UUID id, Map<String , Passenger>ticketPassengers, Train train, DateTime beginTime) {
       super(id);
       // this.passenger = passenger;
        this.ticketPassengers=ticketPassengers;
        this.train = train;
        if (beginTime == null) {
            this.beginTime = DateTime.now();
        } else {
            this.beginTime = beginTime;
        }
        this.endTime = null;
        this.ticketCost = 0.0f;

        if (ticketPassengers == null) {
            throw new IllegalArgumentException("Need passenger!");
        }
        if (train == null) {
            throw new IllegalArgumentException("Need train!");
        }
    }

    public Ticket(Map<String ,Passenger>ticketPassengers, Train train, DateTime beginTime, DateTime endTime, float ticketCost) {
        //super(id);
        this.ticketPassengers = ticketPassengers;
        this.train = train;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.ticketCost = ticketCost;
    }

    // Getters
    public String getInfo() {
        String beginTimeString = beginTime != null ? beginTime.toString() : "Not set";
        String endTimeString = endTime != null ? endTime.toString() : "Not set";
        return "Ticket id: " +
                train.getInfo() + "; begin time: " + beginTimeString + "; end time: " + endTimeString + "; paid: " + ticketCost;
    }

    public UUID getId() {
        return id;
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

//    public Passenger getPassenger() {
//        return passenger;
//    }

    public Train getTrain() {
        return train;
    }

    // Other
    public void endTicket(DateTime _endTime) {
        if (endTime == null) {
            if (_endTime == null) {
                endTime = DateTime.now();
            } else {
                endTime = _endTime.isAfter(beginTime) ? _endTime : beginTime;
            }
            ticketCost = calculateTicketCost();
        }
    }

    public int getTicketDays() {
        Period period = new Period(beginTime, endTime, PeriodType.days());
        return period.getDays();
    }

    public float calculateTicketCost() {
        float cost = Math.round(100 * Senior.applyDiscount(getTicketDays() * train.getBasePrice())) / 100.0f;
        if (cost < 0) {
            throw new IllegalArgumentException("Invalid ticket cost!");
        }
        return cost;
    }
}

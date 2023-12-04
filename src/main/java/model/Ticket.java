package model;

import lombok.experimental.SuperBuilder;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.io.Serializable;
import java.util.UUID;

@SuperBuilder
public class Ticket extends AbstractEntity {

    private UUID id;

    private Passenger passenger;

    private Train train;

    private DateTime beginTime;

    private DateTime endTime;

    private float ticketCost;

    public Ticket(UUID id, Passenger passenger, Train train, DateTime beginTime) {
        this.id = id;
        this.passenger = passenger;
        this.train = train;
        if (beginTime == null) {
            this.beginTime = DateTime.now();
        } else {
            this.beginTime = beginTime;
        }
        this.endTime = null;
        this.ticketCost = 0.0f;

        if (passenger == null) {
            throw new IllegalArgumentException("Need passenger!");
        }
        if (train == null) {
            throw new IllegalArgumentException("Need train!");
        }
    }

    // Getters
    public String getInfo() {
        String beginTimeString = beginTime != null ? beginTime.toString() : "Not set";
        String endTimeString = endTime != null ? endTime.toString() : "Not set";
        return "Ticket id: " + id + ", passenger: " + passenger.getInfo() + "; " +
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

    public Passenger getPassenger() {
        return passenger;
    }

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
        float cost = Math.round(100 * passenger.applyDiscount(getTicketDays() * train.getBasePrice())) / 100.0f;
        if (cost < 0) {
            throw new IllegalArgumentException("Invalid ticket cost!");
        }
        return cost;
    }
}

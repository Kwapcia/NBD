package model;
import jakarta.persistence.*;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name = "Tickets")

public class Ticket implements Serializable {
    @Column(name = "Id")
    private final UUID id;

    @Column(name = "Passenger_Info") //?
    private final Passenger passenger;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private final Train train;

    @Column(name = "Begin_Time")
    private DateTime beginTime;

    @Column(name = "End_Time")
    private DateTime endTime;

    @Column(name = "Ticket_Price")
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
    @OneToMany(mappedBy = "Passenger") //ticket może mieć tylko jednego passenger a passenger może mieć wiele ticketsów, mappedby-> dwukierunkowa
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

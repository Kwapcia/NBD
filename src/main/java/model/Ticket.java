package model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import ids.CassandraIds;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import java.util.UUID;

@Entity(defaultKeyspace = CassandraIds.KEYSPACE)
@CqlName(CassandraIds.TICKET_TABLE)
@Getter
@Setter
public class Ticket extends AbstractEntity {
    @CqlName("passenger_id")
    private UUID passengerId;

    @CqlName("train_id")
    private UUID trainId;

    @CqlName("begin_time")
    private DateTime beginTime;

    @CqlName("end_time")
    private DateTime endTime;

    @CqlName("ticket_cost")
    private float ticketCost;

    public Train train;
    public Ticket(){}

    public Ticket(String discriminator,UUID id, UUID passengerId,UUID trainId, DateTime beginTime, DateTime endTime, float ticketCost) {
       super(id,discriminator);
        this.passengerId=passengerId;
        this.trainId=trainId;
        if (beginTime == null) {
            this.beginTime = DateTime.now();
        } else {
            this.beginTime = beginTime;
        }
        this.endTime = null;
        this.ticketCost = 0.0f;

        if (passengerId == null) {
            throw new IllegalArgumentException("Need passenger!");
        }
        if (trainId == null) {
            throw new IllegalArgumentException("Need train!");
        }
        this.endTime = endTime;
        this.ticketCost = ticketCost;
    }

    public UUID getPassengerId() {
        return passengerId;
    }

    public void setPassegerId(UUID passengerId) {
        this.passengerId = passengerId;
    }

    public void setTrainId(UUID trainId) {
        this.trainId = trainId;
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

    public DateTime getBeginTime() {
        return beginTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public float getTicketCost() {
        return ticketCost;
    }

    public UUID getTrainId() {
        return trainId;
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

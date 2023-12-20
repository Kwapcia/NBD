package model.mapper;

import model.Passenger;
import model.Ticket;
import model.AbstractEntity;
import model.mgd.PassengerMgd;
import model.mgd.TicketMgd;

import java.util.HashMap;
import java.util.Map;

public class TicketMapper {
    public static TicketMgd toMongoDocument(Ticket ticket) {
//        Map<String, PassengerMgd> ticketMgdPassengers = new HashMap<>();
//        ticket.getTicketPassengers().keySet().forEach(key -> {
//            ticketMgdPassengers.put(key, PassengerMapper.toMongoDocument(ticket.getTicketPassengers().get(key)));
//        });
        return TicketMgd.builder()
                .id(ticket.getId())
                .passenger(PassengerMapper.toMongoDocument(ticket.getPassenger()))
                .train(TrainMapper.toMongoDocument(ticket.getTrain()))
                .beginTime(ticket.getBeginTime())
                .endTime(ticket.getEndTime())
                .ticketCost(ticket.getTicketCost())
                .build();
    }
}

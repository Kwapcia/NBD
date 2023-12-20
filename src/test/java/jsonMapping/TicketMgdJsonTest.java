package jsonMapping;

import model.Passenger;
import model.mgd.PassengerMgd;
import model.mgd.TicketMgd;
import model.mgd.TrainMgd;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketMgdJsonTest {
//    @Test
//    public void testTicketGsonSerializationDeserialization(){
//        Map<String,PassengerMgd> ticketPassengers = new HashMap<>();
//        PassengerMgd passengerMgd = new PassengerMgd("a","b", UUID.randomUUID(),23,false);
//        PassengerMgd passengerMgd1 = new PassengerMgd("g","c",UUID.randomUUID(),26,false);
//        TrainMgd trainMgd = new TrainMgd(UUID.randomUUID(),25,"2A","lodz","Wroclaw",false);
//        DateTime beginTime = DateTime.now();
//        DateTime endTime = beginTime.plusHours(2);
//        TicketMgd ticketMgd = new TicketMgd(passengerMgd,trainMgd,beginTime,endTime,25);
//        String json = ticketMgd.toJson();
//        TicketMgd newTicketMgd = TicketMgd.fromJson(json);
//        assertEquals(ticketMgd.getPassenger(),newTicketMgd.getPassenger());
//    }
}

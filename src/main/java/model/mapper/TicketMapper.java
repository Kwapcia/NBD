package model.mapper;

import org.bson.Document;
import model.mgd.TicketMgd;

public class TicketMapper {

    public static Document toMongoTicket(TicketMgd ticketMgd) {
        Document ticketDocument = new Document ()
                .append("_id",ticketMgd.getId())
                .append("First_Name",ticketMgd.getPassengerMgd().getFirstName())
                .append("Last_Name",ticketMgd.getPassengerMgd().getLastName())
                .append("_id",ticketMgd.getPassengerMgd().getId())
                .append("Age",ticketMgd.getPassengerMgd().getAge())
                .append("Passenger_Type",ticketMgd.getPassengerMgd().getPassengerType())
                .append("Is_Archived",ticketMgd.getPassengerMgd().isArchive())
                .append("_id",ticketMgd.getTrainMgd().getId())
                .append("Base_Price",ticketMgd.getTrainMgd().getBasePrice())
                .append("Seat",ticketMgd.getTrainMgd().getSeat())
                .append("Starting_Location",ticketMgd.getTrainMgd().getStartingLocation())
                .append("Destination",ticketMgd.getTrainMgd().getDestination())
                .append("IsArchive",ticketMgd.getTrainMgd().isArchive())
                .append("Begin_Time",ticketMgd.getBeginTime())
                .append("End_Time",ticketMgd.getEndTime())
                .append("Ticket_Price",ticketMgd.getTicketCost());
        return ticketDocument;
    }
}

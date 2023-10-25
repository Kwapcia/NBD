package managers;
import java.io.Serializable;
import java.util.UUID;

import model.Passenger;
import model.Ticket;
import repositories.TicketRepository;
import model.Train;
import org.joda.time.DateTime;
public class TicketManager implements Serializable {
    private TicketRepository currentTickets;
    private TicketRepository archiveTickets;

    public TicketManager(TicketRepository currentTickets, TicketRepository archiveTickets) {
        if (currentTickets == null || archiveTickets == null) {
            throw new IllegalArgumentException("Cannot create TicketManager without repositories!");
        }
        this.currentTickets = currentTickets;
        this.archiveTickets = archiveTickets;
    }

    public Ticket registerTrain(Passenger passenger, Train train, DateTime beginTime) {
        if (getTicket(train) == null) {
            UUID uuid = UUID.randomUUID();
            Ticket newTicket = new Ticket(uuid, passenger, train, beginTime);
            currentTickets.add(newTicket);
            return newTicket;
        } else {
            return null;
        }
    }

    public void returnTrain(Train train) {
        Ticket ticket = getTicket(train);
        if (ticket != null) {
            DateTime currentTime = DateTime.now();
            ticket.endTicket(currentTime);
            archiveTickets.add(ticket);
            currentTickets.remove(ticket);
        }
    }

    public Ticket getTicket(Train train) {
        for (Ticket ticket : currentTickets.getTickets()) {
            if (ticket.getTrain().equals(train)) {
                return ticket;
            }
        }
        return null;
    }
}
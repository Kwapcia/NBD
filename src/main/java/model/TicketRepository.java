package model;
import java.util.ArrayList;
import java.util.List;
public class TicketRepository {
    private List<Ticket> tickets = new ArrayList<>();
    public void add(Ticket ticket) {
        tickets.add(ticket);
    }
    public void remove(Ticket ticket) {
        tickets.remove(ticket);
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public String report() {
        StringBuilder report = new StringBuilder();
        for (Ticket ticket : tickets) {
            report.append(ticket.getInfo()).append("\n");
        }
        return report.toString();
    }
}
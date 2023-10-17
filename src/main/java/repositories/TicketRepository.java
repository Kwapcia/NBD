package repositories;

import model.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TicketRepository implements Repository<Ticket> {
    private List<Ticket> tickets = new ArrayList<>();

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public Ticket find(Predicate<Ticket> predicate) {
        return null;
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

    @Override
    public Ticket get(int id) {
        return null;
    }


    @Override
    public List<Ticket> findAll(Predicate<Ticket> predicate) {
        return new ArrayList<>();
    }

    @Override
    public int size() {
        return tickets.size();
    }
}
package managers;

import model.Passenger;
import model.Train;
import org.joda.time.DateTime;
import repositories.TicketRepository;
import model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        ticketRepository = new TicketRepository();
    }

    @Test
    void addTicket() {
        Passenger passenger = new Passenger("John", "Doe", "john.doe@example.com", 30);
        Train train = new Train(123, 5, "Station A", "Station B", "12:00");
        DateTime dateTime = new DateTime();
        Ticket ticket = new Ticket(UUID.randomUUID(), passenger, train, dateTime);
        ticketRepository.add(ticket);
        assertEquals(1, ticketRepository.size());
    }

    @Test
    void removeTicket() {
        Passenger passenger = new Passenger("John", "Doe", "john.doe@example.com", 30);
        Train train = new Train(123, 5, "Station A", "Station B", "12:00");
        DateTime dateTime = new DateTime();
        Ticket ticket = new Ticket(UUID.randomUUID(), passenger, train, dateTime);
        ticketRepository.add(ticket);
        ticketRepository.remove(ticket);
        assertEquals(0, ticketRepository.size());
    }

    @Test
    void getTickets() {
        Passenger passenger1 = new Passenger("John", "Doe", "john.doe@example.com", 30);
        Train train1 = new Train(123, 5, "Station A", "Station B", "12:00");
        DateTime dateTime1 = new DateTime();
        Ticket ticket1 = new Ticket(UUID.randomUUID(), passenger1, train1, dateTime1);
        Passenger passenger2 = new Passenger("Bob", "Smith", "bob.smith@example.com", 20);
        Train train2 = new Train(100, 50, "Station C", "Station D", "16:00");
        DateTime dateTime2 = new DateTime();
        Ticket ticket2 = new Ticket(UUID.randomUUID(), passenger2, train2, dateTime2);
        ticketRepository.add(ticket1);
        ticketRepository.add(ticket2);
        List<Ticket> tickets = ticketRepository.getTickets();
        assertEquals(2, tickets.size());
        assertTrue(tickets.contains(ticket1));
        assertTrue(tickets.contains(ticket2));
    }

    @Test
    void generateReport() {
        Passenger passenger1 = new Passenger("John", "Doe", "john.doe@example.com", 30);
        Train train1 = new Train(123, 5, "Station A", "Station B", "12:00");
        DateTime dateTime1 = new DateTime();
        Ticket ticket1 = new Ticket(UUID.randomUUID(), passenger1, train1, dateTime1);
        Passenger passenger2 = new Passenger("Bob", "Smith", "bob.smith@example.com", 20);
        Train train2 = new Train(100, 50, "Station C", "Station D", "16:00");
        DateTime dateTime2 = new DateTime();
        Ticket ticket2 = new Ticket(UUID.randomUUID(), passenger2, train2, dateTime2);
        ticketRepository.add(ticket1);
        ticketRepository.add(ticket2);
        String expectedReport = ticket1.getInfo() + "\n" + ticket2.getInfo() + "\n";
        assertEquals(expectedReport, ticketRepository.report());
    }
}
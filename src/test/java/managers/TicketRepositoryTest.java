package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.TicketRepository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TicketRepositoryTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("default");
        ticketRepository = new TicketRepository();
    }

    @AfterEach
    void afterAll(){
        if(emf!=null)
            emf.close();
        if(em!=null)
            em.close();
    }
    @Test
    void testAddTicket() {
//        Ticket ticket = new Ticket();
//        ticketRepository.add(ticket);
//        assertNotNull(ticket.getId());
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(ticket);
//        transaction.commit();
        Ticket ticket = new Ticket();
        // Set ticket properties
        ticketRepository.add(ticket);
        assertNotNull(ticket.getId()); // Ensure that the ticket has been assigned an ID
    }

    @Test
    void testRemoveTicket() {
        // Create a new ticket or obtain an existing one
        Ticket ticket = new Ticket();
        // Set ticket properties
        ticketRepository.add(ticket);
        UUID ticketId = ticket.getId();

        ticketRepository.remove(ticket);

        // Try to get the ticket again using the ID and assert that it should be null
        Ticket removedTicket = ticketRepository.getByUUID(ticketId);
        assertNull(removedTicket);
    }

    @Test
    void testGetTickets() {
        // Create and add multiple tickets
        Ticket ticket1 = new Ticket();
        // Set ticket properties
        ticketRepository.add(ticket1);

        Ticket ticket2 = new Ticket();
        // Set ticket properties
        ticketRepository.add(ticket2);

        List<Ticket> tickets = ticketRepository.getTickets();
        assertEquals(2, tickets.size()); // Ensure that all tickets have been retrieved
    }
}
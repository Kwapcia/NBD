package model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import managers.PassengerManager;
import managers.TicketManager;
import managers.TrainManager;
import repositories.EntityManagerGetter;
import repositories.PassengerRepository;
import repositories.TicketRepository;
import repositories.TrainRepository;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager() ;
        em.getTransaction().begin();
        em.getTransaction().commit();
//        PassengerRepository passengerRepo = new PassengerRepository();
//        PassengerManager passengerManager = new PassengerManager(passengerRepo);
//
//        TrainRepository trainRepo = new TrainRepository();
//        TrainManager trainManager = new TrainManager(trainRepo);
//
//        TicketRepository currentTickets = new TicketRepository();
//        TicketRepository archiveTickets = new TicketRepository();
//        TicketManager ticketManager = new TicketManager(currentTickets, archiveTickets);
    }
}

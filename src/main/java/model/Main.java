package model;
import managers.PassengerManager;
import managers.TicketManager;
import managers.TrainManager;
import menu.TerminalMenu;
public class Main {
    public static void main(String[] args) {
        PassengerRepository passengerRepo = new PassengerRepository();
        PassengerManager passengerManager = new PassengerManager(passengerRepo);

        TrainRepository trainRepo = new TrainRepository();
        TrainManager trainManager = new TrainManager(trainRepo);

        TicketRepository currentTickets = new TicketRepository();
        TicketRepository archiveTickets = new TicketRepository();
        TicketManager ticketManager = new TicketManager(currentTickets, archiveTickets);

        TerminalMenu menu = new TerminalMenu(passengerManager, trainManager, ticketManager);

        menu.start();
    }
}

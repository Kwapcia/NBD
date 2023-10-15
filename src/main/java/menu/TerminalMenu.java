package menu;
import exceptions.LogicException;
import exceptions.ParameterException;
import managers.*;
import model.*;
import org.joda.time.DateTime;
import java.util.Scanner;
public class TerminalMenu {
    private PassengerManager passengerManager;
    private TrainManager trainManager;
    private TicketManager ticketManager;

    public TerminalMenu(PassengerManager passengerManager, TrainManager trainManager, TicketManager ticketManager) {
        this.passengerManager = passengerManager;
        this.trainManager = trainManager;
        this.ticketManager = ticketManager;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w systemie Lodz Koleje!");

        boolean exit = false;
        while (!exit) {
            System.out.println(
                    "==================================\n" +
                            "1. Zarządzaj pasażerami\n" +
                            "2. Zarządzaj pociągami\n" +
                            "3. Zarządzaj biletami\n" +
                            "0. Zakończ program\n" +
                            "==================================");

            char input = scanner.next().charAt(0);

            switch (input) {
                case '1':
                    passenger();
                    break;
                case '2':
                    train();
                    break;
                case '3':
                    ticket();
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("Błędny wybór!");
                    break;
            }
        }
    }

    public void passenger() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println(
                    "==================================\n" +
                            "1. Zarejestruj nowego pasażera\n" +
                            "2. Wyświetl informacje o pasażerze\n" +
                            "3. Wyświetl informacje o wszystkich pasażerach\n" +
                            "4. Usuń pasażera\n" +
                            "0. Powrót do menu\n" +
                            "==================================");

            char input = scanner.next().charAt(0);

            switch (input) {
                case '1':
                    String firstName, lastName, personalID;
                    int age;
                    System.out.print("Podaj imię: ");
                    scanner.nextLine();
                    firstName = scanner.nextLine();
                    System.out.print("Podaj nazwisko: ");
                    lastName = scanner.nextLine();
                    System.out.print("Podaj numer pesel: ");
                    personalID = scanner.next();
                    System.out.print("Podaj wiek: ");
                    age = scanner.nextInt();

                    try {
                        passengerManager.registerPassenger(firstName, lastName, personalID, age);
                    } catch (ParameterException e) {
                        System.err.println("Parameter error: " + e.getMessage());
                    } catch (LogicException e) {
                        System.err.println("Logic error: " + e.getMessage());
                    }
                    break;
                case '2':
                    System.out.print("Podaj numer pesel pasażera: ");
                    personalID = scanner.next();

                    Passenger passenger = passengerManager.getPassenger(personalID);
                    if (passenger == null) {
                        System.out.println("Podany pasażer nie istnieje!");
                        break;
                    }

                    System.out.println(passenger.getInfo());
                    break;
                case '3':
                    System.out.println(passengerManager.getAllPassengersReport());
                    break;
                case '4':
                    System.out.print("Podaj numer pesel pasażera: ");
                    personalID = scanner.next();

                    try {
                        passenger = passengerManager.getPassenger(personalID);
                        if (passenger == null) {
                            System.out.println("Pasażer o podanym peselu nie istnieje!");
                            break;
                        }
                        passengerManager.unregisterPassenger(passenger);
                    } catch (ParameterException e) {
                        System.err.println("Parameter error: " + e.getMessage());
                    } catch (LogicException e) {
                        System.err.println("Logic error: " + e.getMessage());
                    }
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("Błędny wybór!");
                    break;
            }
        }
    }

    public void train() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println(
                    "==================================\n" +
                            "1. Zarejestruj nowy pociąg\n" +
                            "2. Wyświetl informacje o pociągu\n" +
                            "3. Wyświetl informacje o wszystkich pociągach\n" +
                            "4. Usuń pociąg\n" +
                            "0. Powrót do menu\n" +
                            "==================================");

            char input = scanner.next().charAt(0);

            switch (input) {
                case '1':
                    int basePrice, id;
                    String seat, startingLocation, destination;
                    System.out.print("Podaj cenę: ");
                    basePrice = scanner.nextInt();
                    System.out.print("Podaj numer pociągu: ");
                    id = scanner.nextInt();
                    System.out.print("Podaj nr siedzenia: ");
                    seat = scanner.next();
                    System.out.print("Podaj miejsce odjazdu: ");
                    startingLocation = scanner.next();
                    System.out.print("Podaj miejsce docelowe: ");
                    destination = scanner.next();

                    try {
                        trainManager.registerTrain(basePrice, id, seat, startingLocation, destination);
                    } catch (ParameterException e) {
                        System.err.println("Parameter error: " + e.getMessage());
                    } catch (LogicException e) {
                        System.err.println("Logic error: " + e.getMessage());
                    }
                    break;
                case '2':
                    System.out.print("Podaj numer pociągu: ");
                    id = scanner.nextInt();

                    Train train = trainManager.getTrain(id);
                    if (train == null) {
                        System.out.println("Pociąg o podanym numerze nie istnieje!");
                        break;
                    }

                    System.out.println(train.getInfo());
                    break;
                case '3':
                    System.out.println(trainManager.getAllTrainsReport());
                    break;
                case '4':
                    System.out.print("Podaj numer pociągu: ");
                    id = scanner.nextInt();

                    try {
                        trainManager.unregisterTrain(trainManager.getTrain(id));
                    } catch (ParameterException e) {
                        System.err.println("Parameter error: " + e.getMessage());
                    } catch (LogicException e) {
                        System.err.println("Logic error: " + e.getMessage());
                    }
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("Błędny wybór!");
                    break;
            }
        }
    }

    public void ticket() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        DateTime beginTime = new DateTime();
        while (!exit) {
            System.out.println(
                    "==================================\n" +
                            "1. Zarezerwuj pociąg\n" +
                            "2. Wyświetl informacje o bilecie\n" +
                            "3. Wyświetl informacje o wszystkich obecnych biletach\n" +
                            "4. Wyświetl informacje o wszystkich archiwalnych biletach\n" +
                            "5. Zakończ rezerwację\n" +
                            "0. Powrót do menu\n" +
                            "==================================");

            char input = scanner.next().charAt(0);

            switch (input) {
                case '1':
                    String personalID, seat, startingLocation, destination;
                    int basePrice, trainNumber;
                    System.out.print("Podaj numer pesel pasażera: ");
                    personalID = scanner.next();

                    Passenger passenger = passengerManager.getPassenger(personalID);
                    if (passenger == null) {
                        System.out.println("Pasażer nie istnieje!");
                        break;
                    }

                    System.out.print("Podaj cenę: ");
                    basePrice = scanner.nextInt();
                    System.out.print("Podaj nr siedzenia: ");
                    seat = scanner.next();
                    System.out.print("Podaj miejsce odjazdu: ");
                    startingLocation = scanner.next();
                    System.out.print("Podaj miejsce docelowe: ");
                    destination = scanner.next();

                    Train train = trainManager.getTrain(basePrice, seat, startingLocation, destination);
                    if (train == null) {
                        System.out.println("Nie znaleziono odpowiedniego pociągu!");
                        break;
                    }

                    try {
                        ticketManager.registerTrain(passenger, train, beginTime);
                    } catch (ParameterException e) {
                        System.err.println("Parameter error: " + e.getMessage());
                    } catch (LogicException e) {
                        System.err.println("Logic error: " + e.getMessage());
                    }
                    break;
                case '2':
                    System.out.print("Podaj numer pesel pasażera: ");
                    trainNumber = scanner.nextInt();

                    Ticket ticket = ticketManager.getTicket(trainManager.getTrain(trainNumber));
                    if (ticket == null) {
                        System.out.println("Nie znaleziono biletu!");
                        break;
                    }

                    System.out.println(ticket.getInfo());
                    break;
                case '3':
                    System.out.println(ticketManager.getAllCurrentTicketsReport());
                    break;
                case '4':
                    System.out.println(ticketManager.getAllArchiveTicketsReport());
                    break;
                case '5':
                    System.out.print("Podaj numer pociągu: ");
                    trainNumber = scanner.nextInt();

                    try {
                        ticketManager.returnTrain(trainManager.getTrain(trainNumber));
                    } catch (ParameterException e) {
                        System.err.println("Parameter error: " + e.getMessage());
                    } catch (LogicException e) {
                        System.err.println("Logic error: " + e.getMessage());
                    }
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("Błędny wybór!");
                    break;
            }
        }
    }
}


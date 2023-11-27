//package repository;
//    import com.mongodb.client.MongoDatabase;
//    import model.mgd.PassengerMgd;
//import model.mgd.TrainMgd;
//import org.joda.time.DateTime;
//import org.junit.jupiter.api.*;
//import repositories.TicketRepository;
//import model.mgd.TicketMgd;
//import java.util.List;
//import java.util.UUID;
//
//    class TicketRepositoryTest {
//
//        private TicketRepository ticketRepository;
//
//        @BeforeEach
//        void setUp() {
//            // Set up your MongoDB connection or mock objects as needed
//            MongoDatabase mockMongoDB = ticketRepository.trainStationDB;
//                    ticketRepository = new TicketRepository(ticketRepository.trainStationDB);
//
//        }
//        //Cannot invoke "repositories.TicketRepository.close()" because "this.ticketRepository" is null
//
//        @AfterEach
//        void tearDown() {
//            // Clean up resources or close MongoDB connection after each test
//            try {
//                ticketRepository.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Test
//        void addTicket() throws Exception {
//            // Create a TicketMgd object for testing
//            PassengerMgd passengerMgd = new PassengerMgd( "John", "Doe",UUID.randomUUID(), 25, PassengerMgd.Type.ADULT, false);
//            TrainMgd trainMgd = new TrainMgd(UUID.randomUUID(), 50, "A1", "StationA", "StationB", false);
//            DateTime beginTime = DateTime.now();
//            TicketMgd ticketMgd = new TicketMgd(UUID.randomUUID(), passengerMgd, trainMgd, beginTime, null, 0.0f);
//
//            // Add the ticket to the repository
//            Assertions.assertDoesNotThrow(() -> ticketRepository.add(ticketMgd));
//
//            // Verify that the ticket is added successfully
//            TicketMgd retrievedTicket = ticketRepository.get(ticketMgd.getId());
//            Assertions.assertNotNull(retrievedTicket);
//            Assertions.assertEquals(ticketMgd.getId(), retrievedTicket.getId());
//        }
//
//        @Test
//        void removeTicket()throws Exception {
//            // Create a TicketMgd object for testing
//            PassengerMgd passengerMgd = new PassengerMgd( "Jane", "Doe",UUID.randomUUID(), 30, PassengerMgd.Type.ADULT, false);
//            TrainMgd trainMgd = new TrainMgd(UUID.randomUUID(), 60, "B1", "StationX", "StationY", false);
//            DateTime beginTime = DateTime.now();
//            TicketMgd ticketMgd = new TicketMgd(UUID.randomUUID(), passengerMgd, trainMgd, beginTime, null, 0.0f);
//
//            // Add the ticket to the repository
//            ticketRepository.add(ticketMgd);
//
//            // Remove the ticket from the repository
//            Assertions.assertDoesNotThrow(() -> ticketRepository.remove(ticketMgd));
//
//            // Verify that the ticket is removed successfully
//            TicketMgd retrievedTicket = ticketRepository.get(ticketMgd.getId());
//            Assertions.assertNull(retrievedTicket);
//        }
//
//        @Test
//        void updateTicket() throws Exception{
//            // Create a TicketMgd object for testing
//            PassengerMgd passengerMgd = new PassengerMgd( "Bob", "Smith", UUID.randomUUID(),22, PassengerMgd.Type.CHILDREN, false);
//            TrainMgd trainMgd = new TrainMgd(UUID.randomUUID(), 40, "C1", "StationP", "StationQ", false);
//            DateTime beginTime = DateTime.now();
//            TicketMgd ticketMgd = new TicketMgd(UUID.randomUUID(), passengerMgd, trainMgd, beginTime, null, 0.0f);
//
//            // Add the ticket to the repository
//            ticketRepository.add(ticketMgd);
//
//            // Modify the ticket and update it in the repository
//            ticketMgd.setTicketCost(50.0f);
//            Assertions.assertDoesNotThrow(() -> ticketRepository.update(ticketMgd));
//
//            // Verify that the ticket is updated successfully
//            TicketMgd updatedTicket = ticketRepository.get(ticketMgd.getId());
//            Assertions.assertNotNull(updatedTicket);
//            Assertions.assertEquals(ticketMgd.getTicketCost(), updatedTicket.getTicketCost());
//        }
//
//        @Test
//        void getTickets()throws Exception {
//            // Create and add multiple TicketMgd objects to the repository
//            TicketMgd ticket1 = createTestTicket();
//            TicketMgd ticket2 = createTestTicket();
//            TicketMgd ticket3 = createTestTicket();
//
//            ticketRepository.add(ticket1);
//            ticketRepository.add(ticket2);
//            ticketRepository.add(ticket3);
//
//            // Retrieve all tickets from the repository
//            List<TicketMgd> tickets = ticketRepository.getTickets();
//
//            // Verify that the correct number of tickets is retrieved
//            Assertions.assertEquals(3, tickets.size());
//        }
//
//        private TicketMgd createTestTicket() {
//            PassengerMgd passengerMgd = new PassengerMgd( "Test", "Passenger", UUID.randomUUID(),25, PassengerMgd.Type.ADULT, false);
//            TrainMgd trainMgd = new TrainMgd(UUID.randomUUID(), 50, "TestTrain", "StationX", "StationY", false);
//            DateTime beginTime = DateTime.now();
//            return new TicketMgd(UUID.randomUUID(), passengerMgd, trainMgd, beginTime, null, 0.0f);
//        }
//    }
//

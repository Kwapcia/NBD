//package managers;
//
//import model.Passenger;
//import model.Senior;
//import model.mgd.PassengerMgd;
//import repositories.AbstractMongoRepository;
//import repositories.PassengerRepository;
//import repositories.Repository;
//
//import java.io.Serializable;
//import java.util.UUID;
//
//public class PassengerManager {
//        private PassengerRepository passengerRepository;
//
//        public PassengerManager(PassengerRepository passengerRepository) {
//            if (passengerRepository == null) {
//                throw new IllegalArgumentException("Cannot create PassengerManager without repository!");
//            }
//            this.passengerRepository = passengerRepository;
//        }
//
//        public Passenger registerPassenger(String firstName, String lastName, UUID id, int age) {
//            Passenger passengerCheck = getPassenger(id);
//            if (passengerCheck == null) {
//                Passenger newPassenger = new Passenger(firstName, lastName, id, age);
//                passengerRepository.add(newPassenger);
//                return newPassenger;
//            } else {
//                passengerCheck.setArchiveStatus(false);
//                passengerCheck.setFirstName(firstName);
//                passengerCheck.setLastName(lastName);
//                passengerCheck.setAge(age);
//                return passengerCheck;
//            }
//        }
//
//        public void unregisterPassenger(Passenger passenger) {
//            if (passenger == null) {
//                throw new IllegalArgumentException("Invalid Passenger!");
//            }
//            passenger.setArchiveStatus(true);
//        }
//
//        public Passenger getPassenger(UUID id) {
//            for (Passenger passenger : passengerRepository.getPassengers()) {
//                if (passenger.getID().equals(id)) {
//                    return passenger;
//                }
//            }
//            return null;
//        }
//}

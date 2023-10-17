package managers;

import model.Passenger;
import repositories.PassengerRepository;

public class PassengerManager {
        private PassengerRepository passengerRepository;

        public PassengerManager(PassengerRepository passengerRepository) {
            if (passengerRepository == null) {
                throw new IllegalArgumentException("Cannot create PassengerManager without repository!");
            }
            this.passengerRepository = passengerRepository;
        }

        public Passenger registerPassenger(String firstName, String lastName, String personalID, int age) {
            Passenger passengerCheck = getPassenger(personalID);
            if (passengerCheck == null) {
                Passenger newPassenger = new Passenger(firstName, lastName, personalID, age);
                passengerRepository.add(newPassenger);
                return newPassenger;
            } else {
                passengerCheck.setArchiveStatus(false);
                passengerCheck.setFirstName(firstName);
                passengerCheck.setLastName(lastName);
                passengerCheck.setAge(age);
                return passengerCheck;
            }
        }

        public void unregisterPassenger(Passenger passenger) {
            if (passenger == null) {
                throw new IllegalArgumentException("Invalid Passenger!");
            }
            passenger.setArchiveStatus(true);
        }

        public Passenger getPassenger(String personalID) {
            for (Passenger passenger : passengerRepository.getPassengers()) {
                if (passenger.getPesel().equals(personalID)) {
                    return passenger;
                }
            }
            return null;
        }

        public String getAllPassengersReport() {
            return passengerRepository.report();
        }

}

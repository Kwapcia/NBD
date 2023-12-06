package imp;

import model.Passenger;
import model.mgd.PassengerMgd;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PassengerManager {
    Passenger addPassenger (Passenger passenger);
    Passenger updatePassenger(Passenger passenger);
    void removePassenger(Passenger passenger);
    List<Passenger> findAllPassengers();
    Optional<Passenger> findPassengerById(UUID id);
}

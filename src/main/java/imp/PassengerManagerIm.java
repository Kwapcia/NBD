package imp;

import model.Passenger;
import model.mapper.PassengerMapper;
import model.mgd.PassengerMgd;
import repositories.PassengerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PassengerManagerIm implements PassengerManager {
    private PassengerRepository passengerRepository;

    public PassengerManagerIm() {
        this.passengerRepository = new PassengerRepository();
    }
    @Override
    public Passenger addPassenger (Passenger passenger){
        return PassengerMapper.toDomainModel(passengerRepository.add(PassengerMapper.toMongoDocument(passenger)));
    }
    @Override
    public Passenger updatePassenger(Passenger passenger){
        return PassengerMapper.toDomainModel(passengerRepository.update(PassengerMapper.toMongoDocument(passenger)));
    }
    @Override
    public void removePassenger(Passenger passenger){
        passengerRepository.remove(PassengerMapper.toMongoDocument(passenger));
    }
    @Override
    public List<Passenger> findAllPassengers(){
        List<Passenger> passengers = new ArrayList<>();
        passengerRepository.findAll().stream().forEach(passenger->passengers.add(PassengerMapper.toDomainModel(passenger)));
        return passengers;
    }

    @Override
    public Optional<Passenger> findPassengerById(UUID id) {
        return Optional.ofNullable(PassengerMapper.toDomainModel(passengerRepository.findById(id).get()));
    }
}

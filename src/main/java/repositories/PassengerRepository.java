package repositories;

import model.Passenger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PassengerRepository implements Repository<Passenger>{
    private List<Passenger> passengers = new ArrayList<>();

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public Passenger get(int id) {
        return null;
    }

    @Override
    public void add(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public Passenger find(Predicate<Passenger> predicate) {
        return null;
    }

    @Override
    public List<Passenger> findAll(Predicate<Passenger> predicate) {
        return null;
    }

    @Override
    public void remove(Passenger passenger) {
        passengers.remove(passenger);
    }

    @Override
    public String report() {
        StringBuilder report = new StringBuilder();
        for (Passenger passenger : passengers) {
            report.append(passenger.getInfo()).append("\n");
        }
        return report.toString();
    }

    @Override
    public int size() {
        return 0;
    }

}

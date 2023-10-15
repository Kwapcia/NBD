package model;
import java.util.ArrayList;
import java.util.List;
public class PassengerRepository {
    private List<Passenger> passengers = new ArrayList<>();

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void add(Passenger passenger) {
        passengers.add(passenger);
    }

    public void remove(Passenger passenger) {
        passengers.remove(passenger);
    }

    public String report() {
        StringBuilder report = new StringBuilder();
        for (Passenger passenger : passengers) {
            report.append(passenger.getInfo()).append("\n");
        }
        return report.toString();
    }
}

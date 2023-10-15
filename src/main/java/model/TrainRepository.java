package model;
import java.util.ArrayList;
import java.util.List;
public class TrainRepository {
    private List<Train> trains = new ArrayList<>();

    public List<Train> getTrains() {
        return trains;
    }

    public void add(Train train) {
        trains.add(train);
    }

    public void remove(Train train) {
        trains.remove(train);
    }

    public String report() {
        StringBuilder report = new StringBuilder();
        for (Train train : trains) {
            report.append(train.getInfo()).append("\n");
        }
        return report.toString();
    }
}
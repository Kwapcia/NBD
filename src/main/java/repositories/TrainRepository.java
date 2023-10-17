package repositories;

import model.Train;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TrainRepository implements Repository<Train>{
    private List<Train> trains = new ArrayList<>();

    public List<Train> getTrains() {
        return trains;
    }

    public void add(Train train) {
        trains.add(train);
    }

    @Override
    public Train find(Predicate<Train> predicate) {
        return null;
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

    @Override
    public Train get(int id) {
        return null;
    }

    @Override
    public List<Train> findAll(Predicate<Train> predicate) {
        return new ArrayList<>();
    }

    //nigdzie nie wykorzystane ale musi być bo krzyczy
    @Override
    public int size() {
        return trains.size();
    }
}
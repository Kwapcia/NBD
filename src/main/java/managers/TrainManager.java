package managers;
import model.Train;
import repositories.TrainRepository;

public class TrainManager {
        private TrainRepository trainRepository;

        public TrainManager(TrainRepository trainRepository) {
            if (trainRepository == null) {
                throw new IllegalArgumentException("Cannot create TrainManager without repository!");
            }
            this.trainRepository = trainRepository;
        }

        public Train registerTrain(int basePrice, int id, String seat, String startingLocation, String destination) {
            Train trainCheck = getTrain(id);
            if (trainCheck == null) {
                Train newTrain = new Train(basePrice, id, seat, startingLocation, destination);
                trainRepository.add(newTrain);
                return newTrain;
            } else {
                if (trainCheck.isArchive()) {
                    trainCheck.setArchiveStatus(false);
                }
                return trainCheck;
            }
        }

        public void unregisterTrain(Train train) {
            if (train == null) {
                throw new IllegalArgumentException("Invalid train!");
            }
            train.setArchiveStatus(true);
        }

        public Train getTrain(int id) {
            for (Train train : trainRepository.getTrains()) {
                if (train.getId() == id) {
                    return train;
                }
            }
            return null;
        }

        public Train getTrain(int basePrice, String seat, String startingLocation, String destination) {
            for (Train train : trainRepository.getTrains()) {
                if (train.getBasePrice() <= basePrice
                        && train.getSeat().equals(seat)
                        && train.getStartingLocation().equals(startingLocation)
                        && train.getDestination().equals(destination)) {
                    return train;
                }
            }
            return null;
        }

        public String getAllTrainsReport() {
            return trainRepository.report();
        }
}

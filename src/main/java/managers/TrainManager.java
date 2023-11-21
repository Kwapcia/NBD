//package managers;
//import model.Train;
//import repositories.TrainRepository;
//
//import java.io.Serializable;
//import java.util.UUID;
//
//public class TrainManager implements Serializable {
//        private TrainRepository trainRepository;
//
//        public TrainManager(TrainRepository trainRepository) {
//            if (trainRepository == null) {
//                throw new IllegalArgumentException("Cannot create TrainManager without repository!");
//            }
//            this.trainRepository = trainRepository;
//        }
//
//        public Train registerTrain(int basePrice, UUID id, String seat, String startingLocation, String destination) {
//            Train trainCheck = getTrain(id);
//            if (trainCheck == null) {
//                Train newTrain = new Train(basePrice, id, seat, startingLocation, destination);
//                trainRepository.add(newTrain);
//                return newTrain;
//            } else {
//                if (trainCheck.isArchive()) {
//                    trainCheck.setArchiveStatus(false);
//                }
//                return trainCheck;
//            }
//        }
//
//        public void unregisterTrain(Train train) {
//            if (train == null) {
//                throw new IllegalArgumentException("Invalid train!");
//            }
//            train.setArchiveStatus(true);
//        }
//
//        public Train getTrain(UUID id) {
//            for (Train train : trainRepository.getTrains()) {
//                if (train.getId() == id) {
//                    return train;
//                }
//            }
//            return null;
//        }
//
//        public Train getTrain(int basePrice, String seat, String startingLocation, String destination) {
//            for (Train train : trainRepository.getTrains()) {
//                if (train.getBasePrice() <= basePrice
//                        && train.getSeat().equals(seat)
//                        && train.getStartingLocation().equals(startingLocation)
//                        && train.getDestination().equals(destination)) {
//                    return train;
//                }
//            }
//            return null;
//        }
//}

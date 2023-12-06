package repositories;

import model.mgd.TrainMgd;

public class TrainRepository extends AbstractMongoRepository<TrainMgd> {
    public TrainRepository() {
        super(TrainMgd.class,"trains");
    }
}
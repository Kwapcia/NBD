package repositories;

import model.mgd.PassengerMgd;

public class PassengerRepository extends AbstractMongoRepository<PassengerMgd>{
    public PassengerRepository() {
        super(PassengerMgd.class,"passengers");
    }
}

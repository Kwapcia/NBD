package repositories;

import model.mgd.TicketMgd;

public class TicketRepository extends AbstractMongoRepository<TicketMgd> {
    public TicketRepository() {
        super(TicketMgd.class,"tickets");
    }
}
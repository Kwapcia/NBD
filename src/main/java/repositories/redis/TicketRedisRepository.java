package repositories.redis;

import model.mgd.TicketMgd;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TicketRedisRepository extends AbstractRedisRepository implements RedisRepository<TicketMgd> {
    private final PassengerRedisRepository passengerRedisRepository;
    private final TrainRedisRepository trainRedisRepository;
    public TicketRedisRepository(PassengerRedisRepository passengerRedisRepository,TrainRedisRepository trainRedisRepository){
        super();
        this.passengerRedisRepository=passengerRedisRepository;
        this.trainRedisRepository=trainRedisRepository;
    }
    @Override
    public TicketMgd findById(ObjectId id){
        String idString = id.toString();
        if(pool.exists(idString)){
            return TicketMgd.fromJson(pool.get(idString));
        }else{
            return null;
        }
    }
    @Override
    public List<TicketMgd> findAll(){
        List<TicketMgd> allTickets = new ArrayList<>();
        Set<String> keys = pool.keys("*");
        for(String key:keys){
            String ticketData = pool.get(key);
            TicketMgd ticketMgd = TicketMgd.fromJson(ticketData);
            allTickets.add(ticketMgd);
        }
        return allTickets;
    }
    public void add(TicketMgd obj )throws Exception{
        pool.set(obj.getId().toString(),obj.toJson());
        pool.expire(obj.getId().toString(),180);
    }
    @Override
    public TicketMgd read(TicketMgd obj)throws Exception{
        return findById(obj.getId());
    }
    @Override
    public void update(TicketMgd obj)throws Exception{
        String idString = obj.getId().toString();
        if(pool.exists(idString)){
            pool.set(idString,obj.toJson());
            pool.expire(idString,180);
        }else{
            throw new Exception("no update found for ticket id:"+idString);
        }
    }
    @Override
    public void remove(TicketMgd obj)throws Exception{
        String idString=obj.getId().toString();
        if(pool.exists(idString)){
            pool.del(idString);
        }else{
            throw new Exception("no ticket to delete id: "+toString());
        }
    }
    public void drop(){
        super.drop();
    }
}

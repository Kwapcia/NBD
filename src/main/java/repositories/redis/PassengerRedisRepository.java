package repositories.redis;

import model.mgd.PassengerMgd;
import org.bson.types.ObjectId;

import java.util.*;

public class PassengerRedisRepository extends AbstractRedisRepository implements RedisRepository<PassengerMgd> {
    public PassengerRedisRepository(){
        super();
    }
   @Override
    public PassengerMgd findById(ObjectId id) {
        String idString = id.toString();
        if(pool.exists(idString)){
            String passengerData = pool.get(idString);
            return PassengerMgd.fromJson(passengerData);
        }else{
            return null;
        }
    }
    @Override
    public List<PassengerMgd> findAll() {
        List<PassengerMgd> allPassengers = new ArrayList<>();
        Set<String> keys = pool.keys("*");
        for(String key:keys){
            String passengerData = pool.get(key);
            PassengerMgd passenger = PassengerMgd.fromJson(passengerData);
            allPassengers.add(passenger);
        }
        return allPassengers;
    }
    @Override
    public void add(PassengerMgd obj) throws Exception{
//        String idString = obj.getId().toString();
//        String passengerData = obj.toJson();
        pool.set(obj.getId().toString(),obj.toJson());
        pool.expire(obj.getId().toString(),36000);
    }
    @Override
    public PassengerMgd read(PassengerMgd obj)throws Exception{
        return findById(obj.getId());
    }
    @Override
    public void update(PassengerMgd obj)throws Exception{
        String idString = obj.getId().toString();
        if(pool.exists(idString)){
            pool.set(idString,obj.toJson());
            pool.expire(idString,180);
        }else{
            throw new Exception("No passenger found for update id:"+idString);
        }
    }
    @Override
    public void remove(PassengerMgd obj )throws Exception{
        String idString = obj.getId().toString();
        if(pool.exists(idString)){
            pool.del(idString);
        }else{
            throw new Exception("No passenger to delete id: "+idString);
        }
    }
    public void drop(){
        super.drop();
    }
}

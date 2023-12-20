package repositories.redis;

import model.Train;
import model.mgd.TrainMgd;
import org.bson.types.ObjectId;
import org.hibernate.persister.entity.mutation.AttributeAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TrainRedisRepository extends AbstractRedisRepository implements RedisRepository<TrainMgd>{
    public TrainRedisRepository(){
        super();
    }
    @Override
    public TrainMgd findById(ObjectId id){
        String idString = id.toString();
        if(pool.exists(idString)){
            return TrainMgd.fromJson(pool.get(idString));
        }else{
            return null;
        }
    }
    @Override
    public List<TrainMgd> findAll(){
        List<TrainMgd> allTrains = new ArrayList<>();
        Set<String> keys= pool.keys("*");
        for(String key:keys){
            String trainData = pool.get(key);
            TrainMgd trainMgd = TrainMgd.fromJson(trainData);
            allTrains.add(trainMgd);
        }
        return allTrains;
    }
    public void add(TrainMgd obj)throws Exception{
        pool.set(obj.getId().toString(),obj.toJson());
        pool.expire(obj.getId().toString(),180);
    }
    @Override
    public void update(TrainMgd obj)throws Exception{
        String idString = obj.getId().toString();
        if (pool.exists(idString)) {

            pool.set(idString,obj.toJson());
            pool.expire(idString,180);
        }else{
            throw new Exception("no update found for train id: "+idString);
        }
    }
    @Override
    public void remove(TrainMgd obj)throws Exception{
        String idString = obj.getId().toString();
        if(pool.exists(idString)){
            pool.del(idString);
        }else{
            throw new Exception("no train to delete id: "+idString);
        }
    }
    @Override
    public TrainMgd read(TrainMgd obj) throws Exception{
        return findById(obj.getId());
    }
    public void drop(){
        super.drop();
    }
}

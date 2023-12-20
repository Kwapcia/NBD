package repositories.redis;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.bson.types.ObjectId;
import repositories.Repository;

import java.util.List;
import java.util.UUID;

public class MongoRepositoryDecorator<T> implements RedisRepository<T> {
    private Repository<T> decoratedRepository;
    private RedisRepository<T> redisRepository;

    public MongoRepositoryDecorator(Repository<T> decoratedRepository,RedisRepository<T> redisRepository){
        this.decoratedRepository=decoratedRepository;
        try{
            this.redisRepository=redisRepository;
        }catch(Exception e){
            this.redisRepository=null;
        }
    }
    @Override
    public T findById(ObjectId id) throws Exception{
        try{
            T cachedData = redisRepository.findById(id);
            if(cachedData!=null){
                return cachedData;
            }
        }catch (Exception e){
            System.out.println("Error connecting to redis, going to mongo");
        }
        T mongoData = (T) decoratedRepository.findById(id);
        if(mongoData!=null){
            try{
                redisRepository.add(mongoData);
            }catch(Exception e){
                System.out.println("Error connecting to redis cache while caching mongo data");
            }
        }
        return mongoData;
    }
    @Override
    public void add(T obj)throws Exception{
        decoratedRepository.add(obj);
        try{
            redisRepository.add(obj);
        }catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
    @Override
    public T read(T obj) throws Exception{
        return null;
    }
    @Override
    public void update(T obj) throws Exception{
        decoratedRepository.update(obj);
        try{
            redisRepository.update(obj);
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }
    @Override
    public void remove(T obj)throws Exception{
        decoratedRepository.remove(obj);
        try{
            redisRepository.remove(obj);
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }
    public void drop(){
        decoratedRepository.drop();
        try{
            redisRepository.drop();
        }catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
    @Override
    public List<T> findAll() throws Exception{
        try{
            List<T> cachedData = redisRepository.findAll();
            if(cachedData!=null){
                return cachedData;
            }
        }catch(Exception e){
            System.out.println("Error connecting to redis, going to mongo");
        }
        List<T> monogData = decoratedRepository.findAll();
        if(monogData!=null){
            for(T item : monogData){
                try{
                    redisRepository.add(item);
                }catch(Exception e){
                    System.out.println("Error connecting to redis cache while caching mongo data");
                }
            }
        }
        return monogData;
    }
    @Override
    public void clearCache(){
        try{
            redisRepository.clearCache();
        }
        catch(Exception e){
    }}
}

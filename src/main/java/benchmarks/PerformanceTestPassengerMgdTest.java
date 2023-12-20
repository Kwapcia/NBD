package benchmarks;

import model.mgd.ChildrenMgd;
import model.mgd.PassengerMgd;
import org.bson.types.ObjectId;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import repositories.PassengerRepository;
import repositories.Repository;
import repositories.redis.MongoRepositoryDecorator;
import repositories.redis.PassengerRedisRepository;
import repositories.redis.RedisRepository;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2,time = 1)
@Measurement(iterations = 5,time = 1)
@Fork(value=2)

public class PerformanceTestPassengerMgdTest {
    private ChildrenMgd childrenMgd;
    private ChildrenMgd childrenRandomMgd;
    private ChildrenMgd childrenAddMgd;
    private Repository<PassengerMgd> mongoRepository;
    private RedisRepository<PassengerMgd> redisRepository;
    private MongoRepositoryDecorator<PassengerMgd> repositoryDecorator;

    @Setup
    public void setup() throws Exception{
        mongoRepository = new PassengerRepository();
        redisRepository = new PassengerRedisRepository();
        repositoryDecorator = new MongoRepositoryDecorator<>(mongoRepository,redisRepository);
        childrenMgd = new ChildrenMgd("Ola","kwa", new ObjectId(),15,false,"20%","1235");
        childrenRandomMgd = new ChildrenMgd("Ma","rc",new ObjectId(),12,false,"15%","744");
        redisRepository.add(childrenMgd);
        redisRepository.add(childrenMgd);
        repositoryDecorator.add(childrenRandomMgd);
    }
    @Benchmark
    public void testReadFromCache(Blackhole blackhole)throws Exception{
        try{
            Object result = redisRepository.findById(childrenMgd.getId());
            blackhole.consume(result);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Benchmark
    public void testReadFromDatabase(Blackhole blackhole) throws Exception{
        try{
            Object result = mongoRepository.findById(childrenMgd.getId());
            blackhole.consume(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Benchmark
    public void testReadFromDecoratorCached(Blackhole blackhole) throws Exception{
        try{
            Object result = repositoryDecorator.findById(childrenMgd.getId());
            blackhole.consume(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Benchmark
    public void testReadFromDecoratorCachedHundredTimes(Blackhole blackhole) throws Exception{
        for(int i = 0;i<100;i++){
            try{
                Object result = repositoryDecorator.findById(childrenMgd.getId());
                blackhole.consume(result);
            }catch(Exception e ){
                e.printStackTrace();
            }
        }
    }
    @Benchmark
    public void testHundredTimesReadFromDatabase(Blackhole blackhole) throws Exception{
        for(int i =0;i<100;i++){
            try{
                Object result = mongoRepository.findById(childrenMgd.getId());
                blackhole.consume(result);
            }catch( Exception e){
                e.printStackTrace();
            }
        }
    }
    @Benchmark
    public void testAddToCache(Blackhole blackhole) throws Exception{
        childrenAddMgd= new ChildrenMgd("a","b",new ObjectId(),11,false,"25%","562974");
        redisRepository.add(childrenAddMgd);
    }
    @Benchmark
    public void testHundredTimesReadFromCache(Blackhole blackhole) throws Exception{
        for(int i =0;i<100;i++){
            try{
                Object result = redisRepository.findById(childrenMgd.getId());
                blackhole.consume(result);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Benchmark
    public void testAddToDatabase(Blackhole blackhole) throws Exception{
        childrenAddMgd = new ChildrenMgd("c","d",new ObjectId(),9,false,"20%","123456");
        mongoRepository.add(childrenAddMgd);
    }
    @Benchmark
    public void testAddHundredTimesToCache(Blackhole blackhole) throws Exception{
        for(int i=0;i<100;i++){
            childrenAddMgd = new ChildrenMgd("a","g",new ObjectId(),6,false,"16%","192847");
            redisRepository.add(childrenAddMgd);
        }
    }
    @Benchmark
    public void testAddHundredTimesToDecorator(Blackhole blackhole) throws Exception{
        for(int i=0;i<100;i++){
            childrenAddMgd = new ChildrenMgd("a","g",new ObjectId(),6,false,"16%","192847");
            repositoryDecorator.add(childrenAddMgd);
        }
    }
    @Benchmark
    public void testAddHundredTimesToDatabase(Blackhole blackhole) throws Exception{
        for(int i =0;i<100;i++){
            childrenAddMgd = new ChildrenMgd("a","g",new ObjectId(),6,false,"16%","192847");
            mongoRepository.add(childrenAddMgd);
        }
    }
}

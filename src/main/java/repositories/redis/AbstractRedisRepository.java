package repositories.redis;

import java.util.Set;
import redis.clients.jedis.*;

public class AbstractRedisRepository {
    public static JedisPooled pool;
    public void initConnection() {
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
        String host = RedisConfig.getHost();
        int port = RedisConfig.getPort();
        pool = new JedisPooled(new HostAndPort(host,port),clientConfig);
    }
    public AbstractRedisRepository(){
        initConnection();
    }

    public void drop(){
        pool.flushAll();
    }

    public void clearCache(){
        Set<String> keys = pool.keys("*");
        for(String key:keys){
            pool.del(key);
        }
    }
}

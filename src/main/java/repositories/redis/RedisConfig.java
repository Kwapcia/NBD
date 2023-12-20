package repositories.redis;

import java.io.InputStream;
import java.util.Properties;

public class RedisConfig {
    private static final String CONFIG_FILE = "redis-config.properties";
    private static Properties properties;
    static {
        properties = new Properties();
        try(InputStream input = RedisConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)){
            if(input == null){
                System.err.println("no file"+CONFIG_FILE);
            }else {
                properties.load(input);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String getHost() {
        return properties.getProperty("redis.host");
    }
    public static int getPort() {
        return Integer.parseInt(properties.getProperty("redis.port"));
    }
}

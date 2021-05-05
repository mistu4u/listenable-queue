package ansync.cache;

public class CacheFactory {
    public static String CAFFEIN_CACHE = "caffeinCache";
    public static String GUAVA_CACHE = "guavaCache";

    public Cache useCache(String cacheName) {
        if (cacheName.equals(CAFFEIN_CACHE)){
            return new CaffeinCacheImpl();
        }
        if (cacheName.equals(GUAVA_CACHE)){
            return new GuavaCacheImpl();
        }
        return null;
    }
}

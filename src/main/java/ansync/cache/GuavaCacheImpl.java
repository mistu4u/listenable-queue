package ansync.cache;

import ansync.Analytics;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.TimeUnit;

public class GuavaCacheImpl implements Cache {
    LoadingCache<Analytics, Integer>
            featureCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(60, TimeUnit.SECONDS)
            .build(new CacheLoader<Analytics, Integer>() {
                @Override
                public Integer load(Analytics analytics) throws Exception {
                    return 0;
                }
            });

    @SneakyThrows
    @Override
    public @Nullable Integer get(Analytics a) {
        return featureCache.get(a);
    }

    @Override
    public void put(Analytics a, Integer i) {
        featureCache.put(a, i);
    }

    @Override
    public void resetCache() {
        featureCache.invalidateAll();
    }

    @Override
    public String printCache() {
        return toString();
    }

    @Override
    public String toString() {
        return featureCache.asMap().toString();
    }
}

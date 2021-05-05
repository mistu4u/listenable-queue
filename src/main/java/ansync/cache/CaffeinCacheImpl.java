package ansync.cache;

import ansync.Analytics;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CaffeinCacheImpl implements Cache {
    com.github.benmanes.caffeine.cache.Cache<Analytics, Integer>
            featureCache = Caffeine.newBuilder().maximumSize(10000).build();

    @Override
    public @Nullable Integer get(Analytics result) {
        return featureCache.getIfPresent(result);
    }

    @Override
    public void put(Analytics a, Integer i) {
        featureCache.put(a, i);
    }

    @Override
    public void resetCache() {
        featureCache.cleanUp();
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

package ansync.cache;

import ansync.Analytics;
import org.checkerframework.checker.nullness.qual.Nullable;


public interface Cache {
    public @Nullable Integer get(Analytics a);

    public void put(Analytics a, Integer i);
    public void resetCache();
    public String printCache();
}

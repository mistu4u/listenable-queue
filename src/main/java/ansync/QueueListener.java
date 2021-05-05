package ansync;

import ansync.cache.Cache;

public class QueueListener {

    ListenableQueue<Analytics> queue = AsyncDemo.q;
    Cache featureCache = AsyncDemo.featureCache;

    public void listen() {
        queue.registerListener(e -> {
            System.out.println("Invoked listener");
            System.out.println(featureCache.toString());
            Analytics result = queue.poll();
            Integer ifPresent = featureCache.get(result);
            if (ifPresent == null) {
                featureCache.put(result, 1);
            } else {
                featureCache.put(result, ifPresent + 1);
            }
        });
    }
}


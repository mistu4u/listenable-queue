package ansync;

import ansync.cache.Cache;
import ansync.cache.CacheFactory;

import java.util.LinkedList;

public class AsyncDemo {
  static ListenableQueue<Analytics> q = new ListenableQueue<>(new LinkedList<>());
  static CacheFactory cacheFactory = new CacheFactory();
  static Cache featureCache = cacheFactory.useCache(CacheFactory.GUAVA_CACHE);

  public static void main(String[] args) {


    // register a listener which polls a queue and prints an element
    invokeListener();

    Analytics a1 = new Analytics("ff1", "target1");
    Analytics a2 = new Analytics("ff1", "target1");
    Analytics a3 = new Analytics("ff1", "target2");
    Analytics a4 = new Analytics("ff1", "target3");
    // voila!
    System.out.println("put record: record1");
    q.add(a1);
    System.out.println("put record: record2");
    q.add(a2);
    System.out.println("put record: record3");
    q.add(a3);
    System.out.println("put record: record4");
    q.add(a4);
    System.out.println(featureCache.toString());

    featureCache.resetCache();
    q.clear();
  }

  private static void invokeListener() {
    q.registerListener(e -> {
      Analytics result = q.poll();
      Integer ifPresent = featureCache.get(result);
      if (ifPresent == null) {
        featureCache.put(result, 1);
      } else {
        featureCache.put(result, ifPresent + 1);
      }
    });
  }

}

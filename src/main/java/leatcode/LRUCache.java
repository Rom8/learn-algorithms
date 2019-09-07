package leatcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private final Map<Integer, Integer> cache;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity+1, 1f) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRUCache.this.capacity;
            }
        };
    }

    public int get(int key) {
        Integer result = cache.remove(key);
        if (result == null) {
            return -1;
        } else {
            cache.put(key, result);
        }
        return result;
    }

    public void put(int key, int value) {
        cache.remove(key);
        cache.put(key, value);
    }
}

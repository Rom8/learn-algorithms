package leatcode

import spock.lang.Specification

class LRUCacheTest extends Specification {

    LRUCache cache

    def "check capacity 2"() {
        given:
        cache = new LRUCache(2)

        expect:
        cache.put(1, 1);
        cache.put(2, 2);
        1 == cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        -1 == cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        -1 == cache.get(1);       // returns -1 (not found)
        3 == cache.get(3);       // returns 3
        4 == cache.get(4);       // returns 4
    }

    def "check capacity 3"() {
        given:
        cache = new LRUCache(2)

        expect:
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(1, 1);    //make it the newest
        cache.put(5, 5);    //evicts key 2
        -1 == cache.get(2)
    }
}

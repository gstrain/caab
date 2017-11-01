package org.habitatmclean.servlet;

import org.habitatmclean.servlet.TTLCache;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TTL implements TTLCache {

    private ConcurrentHashMap<String, Object[]> cache = new ConcurrentHashMap<String, Object[]>();

    public void put(String key, Object val, long timeToLive) {
        timeToLive = System.currentTimeMillis() + timeToLive;
        if(key == null) throw new RuntimeException("Key cannot be null!");
        cache.put(key, new Object[]{timeToLive, val});
    }

    public Object get(String key) {
        for(String s : cache.keySet()) {
            if(((Long) cache.get(s)[0]) - System.currentTimeMillis() < 0) {
                System.out.println("removed element " + s);
                remove(s);

            }
        }
        if (cache.containsKey(key)) {
            return cache.get(key)[1];
        }
        return null;
    }

    public boolean remove(Object key) {
        return removeAndGet(key) != null;
    }
    public Object removeAndGet(Object key){
        Object entry = cache.remove(key);
        if (entry != null) {
            return entry;
        }
        return null;
    }
}

package org.habitatmclean.servlet;

public interface TTLCache {

    /**
     * @param key - The key to associate with the cache
     * @param value - The actual value to store in the cache
     * @param timeToLiveSeconds - The time to live for this object in the cache
     */
    public void put(String key, Object value, long timeToLiveSeconds);

    /**
     * Returns the Object in the cache that is associated with passed key, or NULL if
     * no value is associated with the key
     * @param key - The key associated with the value to retrieve
     *
     */
    public Object get(String key);

}
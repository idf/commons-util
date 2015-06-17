package io.deepreader.java.commons.util.collections;

import java.util.HashMap;

/**
 * Created by Daniel on 17/06/15.
 */
public class DefaultHashMap<K,V> extends HashMap<K,V> {
    protected V defaultValue;
    public DefaultHashMap(V defaultValue) {
        this.defaultValue = defaultValue;
    }

    public V get(Object k) {
        if(!this.containsKey(k))
            this.put((K) k, defaultValue);
        return super.get(k);
    }
}

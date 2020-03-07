package com.jiaqi.torino.datastore.utils;

import java.util.Iterator;

public class IterableUtils {

    /**
     * Get first element of an iterable collection.
     * Null safe for both empty collection and null collection. Both case will return defaultValue.
     * @param <T>
     * @param iterable
     * @param defaultValue
     * @return the next element of {@code iterator} or the default value
     */
    public static <T> T getFirst(Iterable<? extends T> iterable, T defaultValue) {
        if (iterable == null) {
            return defaultValue;
        }
        Iterator<? extends T> iterator = iterable.iterator();
        if (iterator == null) {
            return defaultValue;
        }
        return iterator.hasNext() ? iterator.next() : defaultValue;
    }
}
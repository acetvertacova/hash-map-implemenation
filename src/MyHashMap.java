import java.util.*;

public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    int size;

    private Entry[] arrayOfBuckets;

    public MyHashMap() {
        arrayOfBuckets = new Entry[INITIAL_CAPACITY];
    }

    public MyHashMap(Integer newCapacity) {
        arrayOfBuckets = (Entry<K, V>[]) new Entry[newCapacity];
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }
    }

    final int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    private int getIndex(int hash, int length) {
        return hash & (length - 1);
    }

    private void resize() {
        Entry<K, V>[] oldArrayOfBuckets = arrayOfBuckets;
        arrayOfBuckets = (Entry<K, V>[]) new Entry[arrayOfBuckets.length * 2];
        size = 0;

        for (Entry<K, V> cell : oldArrayOfBuckets) {
            while (cell != null) {
                put(cell.key, cell.value);
                cell = cell.next;
            }
        }
    }

    public void put(K key, V value) {
        int hash = hash(key);
        int index = getIndex(hash, arrayOfBuckets.length);
        Entry<K, V> entry = arrayOfBuckets[index];

        if(arrayOfBuckets.length * LOAD_FACTOR < size){
            resize();
        }

        if (arrayOfBuckets[index] == null) {
            arrayOfBuckets[index] = new Entry<>(key, value);
            size++;
        } else {
            while (entry != null) {
                if (key.equals(entry.getKey())) {
                    entry.setValue(value);
                    return;
                }
                entry = entry.next;
            }

            size++;
        }

    }

    public V get(K key) {
        int hash = hash(key);
        int index = getIndex(hash, arrayOfBuckets.length);
        Entry<K, V> entry = arrayOfBuckets[index];

        while (entry != null) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public V remove(K key) {
        int hash = hash(key);
        int index = getIndex(hash, arrayOfBuckets.length);
        Entry<K, V> entry = arrayOfBuckets[index];
        Entry<K, V> previousEntry = null;

        while (entry != null) {
            if (key.equals(entry.getKey())) {
                if (previousEntry == null) {
                    arrayOfBuckets[index] = entry.next;
                } else {
                    previousEntry.setNext(entry.next);
                }
                size--;
                return entry.getValue();
            }
            previousEntry = entry;
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int hash = hash(key);
        int index = getIndex(hash, arrayOfBuckets.length);
        Entry<K, V> entry = arrayOfBuckets[index];

        while (entry != null) {
            if (key.equals(entry.getKey())) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (Entry<K, V> bucket : arrayOfBuckets) {
            Entry<K, V> entry = bucket;
            while (entry != null) {
                if (value.equals(entry.getValue())) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        if (size == 0) {
            return entrySet;
        }

        for (Entry<K, V> bucket : arrayOfBuckets) {
            Entry<K, V> entry = bucket;
            while (entry != null) {
                entrySet.add(entry);
                entry = entry.next;
            }
        }
        return entrySet;
    }

    public void clear() {
        arrayOfBuckets = (Entry<K, V>[]) new Entry[arrayOfBuckets.length];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}

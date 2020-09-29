import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
public class MyHashMap<K, V> implements Map61B<K, V>{
    private static final int INITIAL_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private int threshold;
    private Bucket<K, V>[] buckets;

    private class Bucket<K, V> {
        private K key;
        private V value;
        private Bucket<K, V> next;
        private int hashCode;

        public Bucket(int HashCode, K key, V value, Bucket<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = HashCode;
        }

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Bucket<K, V> getNext() {
            return next;
        }

        public void setNext(Bucket<K, V> next) {
            this.next = next;
        }
    }

    public MyHashMap() {
        this(INITIAL_SIZE, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 0)
            throw new IllegalArgumentException("Illegal Argument!");
        buckets = new Bucket[initialSize];
        threshold = (int) (initialSize * loadFactor);
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        buckets = new Bucket[buckets.length];
    }

    @Override
    public boolean containsKey(K key) {
        if (buckets == null) return false;
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (buckets == null) return null;
        if (key == null) throw new IllegalArgumentException();
        int hashCode = hash(key, buckets.length);
        Bucket<K, V> entity = get(hashCode, key);
        return entity == null ? null : entity.getValue();
    }

    private Bucket<K, V> get(int hashCode, K key) {
        Bucket<K, V> bucket = buckets[hashCode];
        while (bucket != null) {
            if (bucket.getHashCode() == hashCode && bucket.getKey().equals(key)) {
                return bucket;
            }
            bucket = bucket.getNext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private int hash(K key, int length) {
        if (key == null)
            throw new IllegalArgumentException();
        return (key.hashCode() & 0x7fffffff) % length;
    }

    @Override
    public void put(K key, V value) {
        int hashCode = hash(key, buckets.length);
        Bucket<K, V> bucket = buckets[hashCode];
        while (bucket != null) {
            if (bucket.getHashCode() == hashCode && bucket.getKey().equals(key)) {
                bucket.setValue(value);
                return;
            }
            bucket = bucket.getNext();
        }
        put(hashCode, key, value);
    }

    private void put(int hashCode, K key, V value) {
        Bucket<K, V> bucket = new Bucket<K, V>(hashCode, key, value, buckets[hashCode]);
        buckets[hashCode] = bucket;
        size += 1;
        if (size > threshold) {
            resize(buckets.length * 2);
        }
    }

    private void resize(int capacity) {
        Bucket<K, V>[] newBuckets = new Bucket[capacity];
        for (int i = 0; i < buckets.length; i++) {
            Bucket<K, V> bucket = buckets[i];
            while (bucket != null) {
                Bucket<K, V> oldNext = bucket.getNext();
                int newHashCode = hash(bucket.getKey(), newBuckets.length);
                bucket.setNext(newBuckets[newHashCode]);
                bucket.setHashCode(newHashCode);
                newBuckets[newHashCode] = bucket;
                bucket = oldNext;
            }
        }
        buckets = newBuckets;
        threshold = (int) (buckets.length * LOAD_FACTOR);

    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            Bucket<K, V> bucket = buckets[i];
            while (bucket != null) {
                keys.add(bucket.getKey());
                bucket = bucket.getNext();
            }
        }
        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException();
        int hashCode = hash(key, buckets.length);
        return remove(hashCode, key);
    }

    private V remove(int hashCode, K key) {
        Bucket<K, V> bucket = buckets[hashCode];
        Bucket<K, V> next = bucket.getNext();
        if (bucket.getKey().equals(key)) {
            V result = bucket.getValue();
            buckets[hashCode] = next;
            size--;
            return result;
        } else {
            while (!next.getKey().equals(key)) {
                bucket = bucket.getNext();
                next = next.getNext();
            }
            V result = next.getValue();
            bucket.setNext(next.getNext());
            size--;
            return result;
        }
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException();
        int hashCode = hash(key, buckets.length);
        return remove(key, value, hashCode);
    }

    private V remove(K key, V value, int hashCode) {
        Bucket<K, V> bucket = buckets[hashCode];
        Bucket<K, V> next = bucket.getNext();
        if (bucket.getKey().equals(key) && bucket.getValue().equals(value)) {
            V result = bucket.getValue();
            buckets[hashCode] = next;
            size--;
            return result;
        } else {
            while(!next.getKey().equals(key)) {
                bucket = bucket.getNext();
                next = next.getNext();
            }
            if (next.getValue().equals(value)) {
                V result = next.getValue();
                bucket.setNext(next.getNext());
                size--;
                return result;
            }
        }
        return null;
    }
}

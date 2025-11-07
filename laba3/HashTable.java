import java.util.LinkedList;
import java.util.Objects;
public class HashTable<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() { return key; }
        V getValue() { return value; }
        void setValue(V value) { this.value = value; }
    }

    private LinkedList<Entry<K,V>>[] table;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = 16;
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        this.capacity = Math.max(1, initialCapacity);
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        h = h & 0x7fffffff;
        return h % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (Objects.equals(entry.getKey(), key)) {
                entry.setValue(value);
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) return false;
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.getKey(), key)) {
                bucket.remove(entry);
                size--;
                if (bucket.isEmpty()) {
                    table[index] = null;
                }
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
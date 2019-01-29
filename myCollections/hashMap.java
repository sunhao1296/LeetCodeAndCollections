package myCollections;

import java.util.Objects;

public class hashMap<K,V>{
    private static final int SIZE = 16;//初始容量
    private static final int MAX_SIZE = 1<<30;
    private Entry[] table = new Entry[SIZE];
    private int capacity;//实际容量
    private int size;//大小
    private int threshold;//达到这个值的时候扩增
    private static final double LOADFACTOR = 0.75;
    //装填因子
    private int modCount;
    class Entry<K,V>{
        int hash;
        K key;
        V value;
        Entry<K,V> next;
        //java泛型简直就是坑
        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    int hashSeed = 0;
    public hashMap(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAX_SIZE)
           initialCapacity = MAX_SIZE;
        this.threshold = tableSizeFor(initialCapacity);
    }
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAX_SIZE) ? MAX_SIZE : n + 1;
    }
    static int indexFor(int hashCode, int length) {
        return hashCode & (length-1);//与table的length - 1按位与，就能保证返回结果在0-length-1内
    }
    public V put(K key, V value) {
        if (table.length == 0) {
            table = new Entry[SIZE];//table会被初始化为长度16，且hashSeed会被赋值；
        }

        int hash = hash(key);

        int i = indexFor(hash, table.length);

        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                // hashCode和equals都相等则表明：本次put是覆盖操作，下面return了被覆盖的老value
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        modCount++;
        if(table[i]==null){
            Entry<K,V> e = table[i];
            table[i] = new Entry<>(hash, key, value, e);// 在构造函数中，e代表next
            size++;
            if(size > threshold){

                if(capacity >= MAX_SIZE){
                    throw new SizeOutOfRangeException("SizeOutOfRange");
                }
                capacity <<= 1;
                capacity = Math.max(capacity,MAX_SIZE);
                threshold = (int)(capacity * LOADFACTOR);
            }
        }

        return null;//增加成功最后返回null
    }
    final int hash(Object k) {
        int h = hashSeed;

        h ^= k.hashCode();//将key的hashCode与h按位异或，最后赋值给h

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
}

# java.util
## HashMap

### Why does HashMap require that the initial capacity be a power of two?

The map has to work out which internal table index to use for any given key, mapping any int value (could be negative) to a value in the range [0, table.length). When table.length is a power of two, that can be done really cheaply.

Refer [Why does HashMap require that the initial capacity be a power of two?](https://stackoverflow.com/questions/8352378/why-does-hashmap-require-that-the-initial-capacity-be-a-power-of-two)


### Why is the maximum capacity of a HashMap 1<<30 and not 1<<31?

The range of int type in Java is 2^31 - 1 ~ -(2^31). The maximun positive number with int as type, is 2^31 - 1. Because the capacity should always be a power of 2, then 2^30 is the maxium capacity.

Refer [Why is the maximum capacity of a Java HashMap 1<<30 and not 1<<31?](https://stackoverflow.com/questions/21638080/why-is-the-maximum-capacity-of-a-java-hashmap-130-and-not-131)

### Resize
```java
void addEntry(int hash, K key, V value, int bucketIndex) {
Entry<K,V> e = table[bucketIndex];
    table[bucketIndex] = new Entry<K,V>(hash, key, value, e);
    if (size++ >= threshold)
        resize(2 * table.length);
}
```

```java
public void putAll(Map<? extends K, ? extends V> m)
...
    while (newCapacity < targetCapacity)
        newCapacity <<= 1;
    if (newCapacity > table.length)
        resize(newCapacity);
...
```

Resize to double. That means if initial capacity is power of 2, then the capacity of a HashMap will always be the power of 2. This is exactly what is wrote for private member **table**.

### indexFor
```java
static int indexFor(int h, int length) {
    return h & (length-1);
}
```
The length here is always be a power of 2, so *length - 1* is a number with lower bits all 1. For example, 2^4 = 16, 16 - 1 = 0x1111.
Using bit-wise **AND** on **h** using **length - 1**, returns only the low-order bits of **h**. Here use bit-wise instead of modulus to calculate the index of Entry, which is faster.

### hash
```java
/**
 * Applies a supplemental hash function to a given hashCode, which
 * defends against poor quality hash functions.  This is critical
 * because HashMap uses power-of-two length hash tables, that
 * otherwise encounter collisions for hashCodes that do not differ
 * in lower bits. Note: Null keys always map to hash 0, thus index 0.
 */
static int hash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```

### transfter
```java
/**
    * Transfers all entries from current table to newTable.
    */
void transfer(Entry[] newTable) {
    Entry[] src = table;
    int newCapacity = newTable.length;
    for (int j = 0; j < src.length; j++) {
        Entry<K,V> e = src[j];
        if (e != null) {
            src[j] = null;
            do {
                Entry<K,V> next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            } while (e != null);
        }
    }
}
```
Old elements might have new index in the new table. So every elemnt in the old table should re-calculate the index.

### Allow null as key
Entry with null as key, will always be put into index 0 of the underlying table.

### What happens if hash collision?
If two elements have same hash, they will be put into a list. The later one will be the list header.

## How does iteration of HashMap work?
Simply iterate over the underlying table(Entry[]).

### Max size of a HashMap is MAXIMUM_CAPACITY?
No. It is just the maximum size of the underlying table(Entry[]). As each bucket is a list, HashMap can contains infinite number of elements in theory.

### The implementation differences between 1.6 and 1.8
HashMap in Java 1.8 use Red-Black tree to store hash collision elements if possible.

---

## HashTable

1. The length of underlying data structure is not mandatory
2. Both Key and Value can not be null

---

## HashSet

```java
public HashSet() {
    map = new HashMap<E,Object>();
}
```

---

## LinkedHashMap

1. Override **removeEldestEntry** to implement cache mechanism
2. Easily to implement LRU cache if ordering mode is access-order

---

## WeakHashMap

## TreeMap

RBTree

[Online visualization](https://www.cs.usfca.edu/~galles/visualization/RedBlack.html)
[从2-3-4树谈到Red-Black Tree（红黑树）](http://blog.csdn.net/v_july_v/article/details/6531399)
[Left-Leaning Red-Black Trees](http://www.cs.princeton.edu/~rs/talks/LLRB/RedBlack.pdf)



What is Deep copy?
What is shallow copy?


## Disadvantage of synchronized collections

The synchronized collection classes include Vector and Hashtable, part of the orig- inal JDK, as well as their cousins added in JDK 1.2, the synchronized wrapper classes created by the Collections.synchronizedXxx factory methods.

1. Thread-safety is implemented by locking the whole collection, only one thread at a time can access the collection state
2. Compound actions, such as put-if-absent, needs additional client-side locking
3. ConcurrentModificationException is implemented using a not synchronized modification count. Refer <Java Concurrency in Practice> 5.1.2


## ConcurrentHashMap

TODO

## CopyOnWriteArrayList

TODO

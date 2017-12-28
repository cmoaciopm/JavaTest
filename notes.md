# java.util
## HashMap
### Why is the maximum capacity of a HashMap 1<<30 and not 1<<31?

https://stackoverflow.com/questions/21638080/why-is-the-maximum-capacity-of-a-java-hashmap-130-and-not-131

### Why does HashMap require that the initial capacity be a power of two?

https://stackoverflow.com/questions/8352378/why-does-hashmap-require-that-the-initial-capacity-be-a-power-of-two

### Max size of a HashMap?
MAXIMUM_CAPACITY

### Resize
#### void addEntry(int hash, K key, V value, int bucketIndex)
resize(2 * table.length)

#### public void putAll(Map<? extends K, ? extends V> m)
newCapacity <<= 1; resize(newCapacity);

Resize to double. That means if initial capacity is power of 2, then the capacity of a HashMap will always be the power of 2. This is exactly what is wrote for private member **table**.

## HashTable
### How to calculate index for element?
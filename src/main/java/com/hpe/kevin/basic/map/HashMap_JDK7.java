package com.hpe.kevin.basic.map;

import java.util.HashMap;

public class HashMap_JDK7 {
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    public V put(K key, V value) {
        // 如果Entry数组未初始化, 则初始化Entry数组
        if (table == EMPTY_TABLE) {
            // Entry数组的长度(length)为2的N次方
            inflateTable(threshold);
        }
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);
        // 根据hash值确定在Entry数组中的index
        int i = indexFor(hash, table.length);
        // 如果index对应的下标已经有Entry对象, 则遍历链表, 查找是否有相同的key存在
        // 如果有, 则修改value的值, 同时将oldValue返回
        // 如果没有, 什么也不做
        for (HashMap.Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        modCount++;
        // 在Entry数组的index对应位置, 采用头插法存入Entry对象
        addEntry(hash, key, value, i);
        return null;
    }

    /**
     * Adds a new entry with the specified key, value and hash code to
     * the specified bucket.  It is the responsibility of this
     * method to resize the table if appropriate.
     *
     * Subclass overrides this to alter the behavior of put method.
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        // 判断是否需要扩容
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            // 重新计算要put的Entry对象的index
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    /**
     * Rehashes the contents of this map into a new array with a
     * larger capacity.  This method is called automatically when the
     * number of keys in this map reaches its threshold.
     *
     * If current capacity is MAXIMUM_CAPACITY, this method does not
     * resize the map, but sets threshold to Integer.MAX_VALUE.
     * This has the effect of preventing future calls.
     *
     * @param newCapacity the new capacity, MUST be a power of two;
     *        must be greater than current capacity unless current
     *        capacity is MAXIMUM_CAPACITY (in which case value
     *        is irrelevant).
     */
    void resize(int newCapacity) {
        HashMap.Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        // 生成一个新的Entry数组, 长度(length)为原来的2倍
        HashMap.Entry[] newTable = new HashMap.Entry[newCapacity];
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

    /**
     * Transfers all entries from current table to newTable.
     */
    void transfer(HashMap.Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
        // 对原Entry数组进行循环
        for (HashMap.Entry<K,V> e : table) {
            // 对链表进行循环
            while(null != e) {
                HashMap.Entry<K,V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                // 重新计算e在新Entry数组中的下标位置index
                int i = indexFor(e.hash, newCapacity);
                // 将e的next指向新数组的i所对应的位置上的Entry对象(有可能为null)
                e.next = newTable[i];
                // 将e放到新数组对应的位置
                newTable[i] = e;
                e = next;
            }
        }
    }
}

package com.hpe.kevin.basic.list;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        // ArrayList 是线程不安全的
        ArrayList<String> arrayList = new ArrayList();
        // 如果有两个线程同时往 arrayList 中添加数据时, 有可能会发生数据被覆盖的场景
        // 线程1
        arrayList.add("111");
        // 线程2
        arrayList.add("222");

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("a");
        copyOnWriteArrayList.add("b");

        // 1. CopyOnWriteArrayList 内部也是通过数组实现的, 在向 CopyOnWriteArrayList 添加元素时,
        //    会先复制一个数组, 写操作是在新的数组上进行, 读操作在原数组上进行
        // 2. 并且, 写操作会加锁, 防止出现并发写入时, 发生数据丢失的问题
        // 3. 写操作结束后, 会把原数组指向新数组
        // 4. CopyOnWriteArrayList 允许在写操作时来读取数据, 大大提高了读的性能, 因此适合读多写少的场景
        //    但是由于 CopyOnWriteArrayList 会比较占内存,
        //    同时可能读取到的数据不是实时最新的数据, 所以不适合实时性要求很高的场景
    }
}

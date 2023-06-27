package com.hpe.kevin.basic.list;

import java.util.ArrayList;
import java.util.LinkedList;

public class L1 {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(); // 数组
        arrayList.add("1"); // 扩容
        arrayList.add(1, "2"); // 扩容, 移动

        LinkedList<String> linkedList = new LinkedList(); // 链表
        linkedList.add("1");
        linkedList.add(1, "2"); // 查找可能会比较耗时

        // 另外 LinkedList 还实现了 Deque 接口
        // 所以可以作为 队列(双端队列) 来使用
        linkedList.addFirst("0");
        linkedList.addLast("9");

        // [1, 2]
        System.out.println(arrayList);
        // [0, 1, 2, 9]
        System.out.println(linkedList);
    }
}

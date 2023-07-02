package com.hpe.kevin.basic.map;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>(2);
        hashMap.put("key", "value");

        // JDK1.7 HashMap 扩容流程
        // 1. 生成新的数组
        // 2. 遍历老数组中每个位置的链表上的元素
        // 3. 取每个元素的 key, 并基于新数组的长度, 计算出每个元素在新数组中的下标
        // 4. 将元素添加到新数组中
        // 5. 所有元素转移完后, 将新数组赋给 HashMap 对象的 table 属性

        // JDK1.8 HashMap 扩容流程
        // 1. 生成新的数组
        // 2. 遍历老数组中每个位置的链表或红黑树
        // 3. 如果是链表, 则直接将链表中的每个元素重新计算下标, 并添加到新数组中去
        // 4. 如果是红黑树, 则先遍历红黑树, 计算出红黑树中每个元素对应在新数组中的下标位置
        //    a. 统计每个下标位置的元素个数
        //    b. 如果该位置下的元素超过了8个, 则生成一个新的红黑树, 并将根节点添加到新数组的对应位置
        //    c. 如果该位置下的元素没有超过8个, 则生成一个链表, 并将链表的头节点添加到新数组的对应位置
        // 5. 所有元素转移完后, 将新数组赋给 HashMap 对象的 table 属性
    }
}

package com.hpe.kevin.basic.string;

import java.lang.reflect.Field;

/**
 * Java String对象的演进
 * Java6以及之前版本:
 *      String对象是对char数组进行了封装实现的对象, 其主要有4个成员成员变量, 分别是char数组, 偏移量offset、字符数量count和哈希值hash
 *      String对象是通过offset和count两个属性来定位char[]数组, 获取字符串。
 *      这样做可以高效、快速地共享数组对象, 同时节省内存空间, 但是这种方式却可能会导致内存泄漏的发生。
 *      子串的 value 仍然引用着 父串的 value[] 数组。如果 value[] 数组的值比较大, GC 无法及时回收会导致内存泄露
 * Java7、8版本:
 *      从Java7版本开始, Java对String类做了一些改变, 具体是String类不再有offset和count两个变量了, 这样做的好处是String对象占用的内存稍微少了点。
 *      同时String.substring()方法通过复制数组的方法, 也不再共享char[]了, 从而解决了使用该方法可能导致的内存泄漏问题。
 * Java9以及之后版本:
 *      从Java9版本开始, Java将char[]数组改为了byte[]数组。
 *      char是两个字节的，如果用来存一个字节的情况下就会造成内存空间的浪费。
 *      而为了节约这一个字节的空间，Java开发者就改成了一个使用一个字节的byte来存储字符串。
 *      另外，在Java9中, String对象维护了一个新的属性coder,
 *      这个属性是编码格式的标识，在计算字符串长度或者调用indexOf()方法的时候，会需要根据这个字段去判断如何计算字符串长度。
 *      coder属性默认有0和1两个值, 其中0代表Latin-1（单字节编码）,1则表示UTF-16编码。
 *
 * ★String对象的创建方式与在内存中的存放
 *      在Java中，对于基本数据类型的变量和对对象的引用，保存在栈内存的局部变量表中；
 *      而通过new关键字和Constructor创建的对象，则是保存在堆内存中。
 *      而String对象的创建方式一般为两种，一种是字面量（字符串常量）的方式，一种则是构造函数（String()）的方式，两种方式在内存中的存放有所不同。
 *
 *      字面量（字符串常量）的创建方式:
 *      使用字面量的方式创建字符串时，JVM会在字符串常量池中先检查是否存在该字面量，如果存在，则返回该字面量在内存中的引用地址；
 *      如果不存在，则在字符串常量池中创建该字面量并返回引用。
 *      使用这种方式创建的好处是避免了相同值的字符串在内存中被重复创建，节约了内存
 *
 *      字符串常量池
 *      常量池是JVM为了减少字符串对象的重复创建，特别维护了一个特殊的内存，这段内存被称为字符串常量池或者字符串字面量池。
 *      在JDK1.6以及之前的版本中，运行时常量池是在方法区中的。
 *      在JDK1.7以及之后版本的JVM，已经将运行时常量池从方法区中移了出来，在Java堆（Heap）中开辟了一块区域用来存放运行时常量池。
 *      从JDK1.8开始，JVM取消了Java方法区，取而代之的是位于直接内存的元空间（MetaSpace）。
 *      目前的字符串常量池在堆中
 *
 *      构造函数（String()）的创建方式:
 *      使用构造函数的方式创建字符串时，JVM同样会在字符串常量池中先检查是否存在该字面量，只是检查后的情况会和使用字面量创建的方式有所不同。
 *      如果存在，则会在堆中另外创建一个String对象，然后在这个String对象的内部引用该字面量, 最后返回该String对象在内存地址中的引用；
 *      如果不存在，则会先在字符串常量池中创建该字面量，然后再在堆中创建一个String对象，然后再在这个String对象的内部引用该字面量，最后返回该String对象的引用。
 *      ★返回的都是new出来的对象的内存地址，每次new出来的对象地址都不同
 *
 */
public class S1 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 在不改变s指针的前提下, 将s的值由"abc"->"abcd"
        String s = new String("abc");

        // 此处可以写N行代码
        Field value = s.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(s, "abcd".getBytes());

        System.out.println(s);
    }

}

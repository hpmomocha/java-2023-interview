package com.hpe.kevin.basic.string;

public class S2 {
    public static void main(String[] args) {
        // str1 指向字符串常量池的该字符串的内存地址
        String str1 = "abc";
        // str2 指向的是使用构造函数方式创建的String对象的内存地址
        String str2 = new String("abc"); // 生成2个对象: 字符串常量对象(常量池中) / String 对象(堆中)
        System.out.println(str1 == str2); // false
        System.out.println(str1.equals(str2)); // true

        /**
         * String对象中的intern()方法
         * 它的特殊性在于，这个方法在业务场景中几乎用不上，它的存在就是在为难程序员的，也可以说是为了帮助程序员了解JVM的内存结构而存在的
         */
        // intern 方法首先会检查字符串常量中是否存在 "abc", 如果存在则返回该字符串的引用
        // 如果不存在, 则把 "abc" 添加到字符串常量池中, 并返回该字符串的引用
        String str3 = str1.intern();
        System.out.println(str1 == str3);
    }
}

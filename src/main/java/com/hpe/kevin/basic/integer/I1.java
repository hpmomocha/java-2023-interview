package com.hpe.kevin.basic.integer;

/**
 * Integer 内部有一个 IntegerCache 静态内部类, 该类存在一个 Integer[] cache
 * 会在类加载的时候执行, 会将 -128 ~ 127 这些数字提前生成 Integer 对象, 并缓存在 cache 数组中
 * 当我们在定义 Integer 数字时, 会调用 Integer 的 valueOf　方法
 * valueOf　方法会判断所定义的数字是否在　-128 ~ 127 之间，如果是则直接从 cache 数组中获取 Integer 对象,
 * 如果超过, 则生成一个新的 Integer 对象
 */
public class I1 {
    public static void main(String[] args) {
        // 以这种形式声明的变量, 内部会调用 valueOf 方法,
        // 如果数值在　-128 ~ 127 之间的话, 直接返回 IntegerCache 内保存的数值所对应的 Integer 对象
        /**
         *     public static Integer valueOf(int i) {
         *         if (i >= IntegerCache.low && i <= IntegerCache.high)
         *             return IntegerCache.cache[i + (-IntegerCache.low)];
         *         return new Integer(i);
         *     }
         */
        Integer i1 = -128;
        Integer i2 = -128;
        System.out.println(i1 == i2);

        Integer i3 = 100;
        Integer i4 = 100;
        System.out.println(i3 == i4);

        Integer i5 = 128;
        Integer i6 = 128;
        System.out.println(i5 == i6);
    }
}

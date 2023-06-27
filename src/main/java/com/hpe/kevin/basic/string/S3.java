package com.hpe.kevin.basic.string;

/**
 * StringBuilder StringBuffer 的 append 方法实现上是不一样的
 * StringBuffer 的 append 方法加
 */
public class S3 {
    public static void main(String[] args) {
        String s = "abc";

        StringBuffer buffer = new StringBuffer(s);
        buffer.append("d");

        // 单线程中使用StringBuilder更合适
        StringBuilder builder = new StringBuilder(s);
        builder.append("d");
    }
}

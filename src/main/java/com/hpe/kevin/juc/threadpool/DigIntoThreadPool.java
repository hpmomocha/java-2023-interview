package com.hpe.kevin.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * JDK中的线程池, 添加线程流程:
 * 1. 添加线程, 直到核心工作线程数到达 corePoolSize
 * 2. 将任务添加到阻塞队列中, 直到阻塞队列满
 * 3. 继续添加非核心工作线程, 直到工作线程数到达 maximumPoolSize
 * 4. 继续有task到来, 则拒绝
 *
 *
 * 核心线程与非核心线程的误区:
 * 1. 对于线程池而言, 其实并没有核心线程与非核心线程, 所有的线程都是平等的, 并没有属性来标识某个线程是核心线程
 *    corePoolSize的意思只是需要在线程池中保留10个线程, 仅此而已
 *    另外, 线程池中的线程数, 其实是 线程完成任务自动消失 -> 创建新的线程 这种往复循环来保持线程池中有 corePoolSize 数的线程
 * 2. 非核心线程可以在超过 keepAliveTime 等待时间后自动消失,
 *    通过调用 ThreadPoolExecutor#allowCoreThreadTimeOut(true) 方法, 核心线程也可以在超过 keepAliveTime 等待时间后消失
 *
 */
public class DigIntoThreadPool {
    public static void main(String[] args) {
        // 线程池new出来后, 线程(核心线程)并不会马上被创建, 是懒加载(懒创建)
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                15,
                20, // 空闲线程最大等待时间
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),            // 阻塞队列
                Executors.defaultThreadFactory(),       // 线程工厂
                new ThreadPoolExecutor.AbortPolicy()    // 拒绝策略
        );

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("业务流程");
            }
        });

        threadPoolExecutor.shutdown();  // SHUTDOWN
        threadPoolExecutor.shutdownNow(); // STOP

        /**
         * ThreadPoolExecutor ctl 属性包含两部分内容:
         * 1. 线程池的状态(RUNNING, SHUTDOWN, STOP, TIDYING, TERMINATED) 处于 高3位
         * 2. 工作线程数 处于低29位
          */

        /**
         * 线程的保活: 采用阻塞
         */

        /**
         * 维持线程池核心线程数
         */
    }
}
